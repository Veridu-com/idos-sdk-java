package com.veridu.idos.endpoints;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.IdOSUtils;

public abstract class AbstractEndpoint implements Serializable {

    /**
     * IdOSAuthType (USER, HANDLER, MANAGEMENT)
     */
    protected IdOSAuthType authType = null;

    /**
     * Company's slug necessary to make most of requests to the API
     */
    protected String companySlug = null;

    /**
     * Last API response code
     */
    private int lastCode;
    /**
     * Token necessary to make requests to the API
     */
    private String currentToken = null;
    /**
     * Keys (public and private) necessary to generate UserToken,
     * CredentialToken, IdentityToken
     */
    private HashMap<String, String> credentials;

    /**
     * Class constructor
     */
    public AbstractEndpoint(HashMap<String, String> credentials, IdOSAuthType authType) throws InvalidToken {
        this.credentials = credentials;
        this.authType = authType;
    }

    /**
     * Fetches an API Resource
     *
     * @param method
     *            String
     * @param resource
     *            String
     * @throws SDKException
     *
     */
    protected JsonObject fetch(String method, String resource) throws SDKException {
        return fetch(method, resource, null, null);
    }

    /**
     * Fetches an API Resource
     *
     * @param method
     *            String
     * @param resource
     *            String
     * @param data
     *            JsonObject
     * @throws SDKException
     */
    protected JsonObject fetch(String method, String resource, JsonObject data) throws SDKException {
        return fetch(method, resource, data, null);
    }

    /**
     * Fetches an API Resource
     *
     * @param method
     *            String
     * @param resource
     *            String
     * @param data
     *            JsonObject
     *
     * @throws SDKException
     *
     */
    protected JsonObject fetch(String method, String resource, JsonObject data, Filter filter) throws SDKException {
        String url = this.transformURL(method, resource, filter);
        JsonObject response = request(method, url, data, filter);

        return response;
    }

    private String transformURL(String method, String resource, Filter filter) {
        String url = Config.BASE_URL;
        if (resource.charAt(0) != '/')
            url = url.concat("/");
        url = url.concat(resource);
        if (filter != null)
            if (filter.toString().isEmpty() == false)
                url += "?" + filter.toString();

        return url;
    }

    private String transformURL(String url, Filter filter) {
        if (filter != null)
            url += "?" + filter.toString();

        return url;
    }

    /**
     * Regenerate the token
     *
     * @throws InvalidToken
     */
    public void refreshToken() throws InvalidToken {
        this.generateAuthToken();
    }

    /**
     * Process request to API
     *
     * @param method
     *            String
     * @param url
     *            String
     * @param data
     *            String
     *
     * @return String response
     * @throws SDKException
     *             Exception
     */
    public JsonObject request(String method, String url, JsonObject data, Filter filter) throws SDKException {
        JsonObject json = new JsonObject();
        JsonArray array = new JsonArray();
        if (this.currentToken == null)
            this.generateAuthToken();
        if ((filter != null) && (filter.getAllPagesTrue())) {
            String newUrl = "";
            do {
                if (newUrl.length() != 0)
                    json = this.sendRequest(method, newUrl, data);
                else
                    json = this.sendRequest(method, url, data);
                array.addAll(json.get("data").getAsJsonArray());
                int page = json.get("pagination").getAsJsonObject().get("current_page").getAsInt() + 1;
                filter.addFilterByKeyName("page", String.valueOf(page));
                newUrl = this.transformURL(url, filter);
            } while (json.get("pagination").getAsJsonObject().get("current_page").getAsInt() < json.get("pagination")
                    .getAsJsonObject().get("total").getAsInt());
            json.add("data", array);
            return json;
        }

        return this.sendRequest(method, url, data);

    }

    /**
     * Checks api response status
     * 
     * @param response
     * @return boolean status
     */
    private boolean isAPIResponseStatusTrue(JsonObject response) {
        return response.get("status").getAsBoolean();
    }

    /**
     * Retrieves api response error
     * 
     * @param apiResponse
     * @return JsonObject api error response
     */
    private JsonObject getAPIJSONError(JsonObject apiResponse) {
        return apiResponse.get("error").getAsJsonObject();
    }

    /**
     * Returns api call response if status true or throws SDKException
     * 
     * @param apiResponse
     * @return JsonObject response
     * 
     * @throws SDKException
     */
    private JsonObject handleAPIresponse(JsonObject apiResponse) throws SDKException {
        if (isAPIResponseStatusTrue(apiResponse))
            return apiResponse;
        else {
            JsonObject apiError = getAPIJSONError(apiResponse);
            throw new SDKException(apiError.get("message").getAsString(), apiError.get("type").getAsString(),
                    apiError.get("link").getAsString());
        }
    }

    /**
     * Sends the request
     *
     * @param method
     * @param url
     * @param data
     * @return
     * @throws SDKException
     */
    protected JsonObject sendRequest(String method, String url, JsonObject data) throws SDKException {
        final String authHeader = "Authorization";
        String credential = null;

        switch (this.authType) {
        case HANDLER:
            credential = "CredentialToken " + this.currentToken;
            break;
        case USER:
            credential = "UserToken " + this.currentToken;
            break;
        case IDENTITY:
            credential = "IdentityToken " + this.currentToken;
            break;
        case NONE:
            break;
        default:
            throw new SDKException("Invalid credentials.");
        }

        try {
            Content ct = null;

            switch (method) {
            case "POST":
                if (this.authType != this.authType.NONE) {
                    ct = Request.Post(url).setHeader(authHeader, credential)
                            .bodyByteArray(data.toString().getBytes(), ContentType.APPLICATION_JSON).execute()
                            .returnContent();
                } else {
                    ct = Request.Post(url).bodyByteArray(data.toString().getBytes(), ContentType.APPLICATION_JSON)
                            .execute().returnContent();
                }
                break;
            case "GET":
                if (this.authType != this.authType.NONE) {
                    ct = Request.Get(url).setHeader(authHeader, credential).execute().returnContent();
                } else {
                    ct = Request.Get(url).execute().returnContent();
                }

                break;
            case "DELETE":
                ct = Request.Delete(url).setHeader(authHeader, credential).execute().returnContent();
                break;
            case "PUT":
                ct = Request.Put(url).setHeader(authHeader, credential)
                        .bodyByteArray(data.toString().getBytes(), ContentType.APPLICATION_JSON).execute()
                        .returnContent();
                break;
            case "PATCH":
                ct = Request.Patch(url).setHeader(authHeader, credential)
                        .bodyByteArray(data.toString().getBytes(), ContentType.APPLICATION_JSON).execute()
                        .returnContent();
                break;

            }

            return handleAPIresponse(this.convertToJson(ct.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Converts string response to response
     *
     * @param apiResponse
     * @return JsonObject response
     */

    protected JsonObject convertToJson(String apiResponse) {
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(apiResponse);
        JsonObject json = response.getAsJsonObject();

        return json;
    }

    /**
     * Setter
     *
     * @param authType
     */
    public void setAuthType(IdOSAuthType authType) throws InvalidToken {
        if ((this.authType == null) || (this.currentToken == null) || (authType != this.authType)) {
            this.authType = authType;
            this.generateAuthToken();
        }
    }

    /**
     * Generates an authorization token
     *
     * @throws InvalidToken
     */
    private String generateAuthToken() throws InvalidToken {
        switch (this.authType) {
        case USER:
            this.currentToken = IdOSUtils.generateUserToken(this.credentials.get("credentialPrivateKey"),
                    this.credentials.get("credentialPublicKey"), this.credentials.get("username"));
            break;
        case IDENTITY:
            this.currentToken = IdOSUtils.generateManagementToken(this.credentials.get("companyPrivateKey"),
                    this.credentials.get("companyPublicKey"));
            break;
        case HANDLER:
            this.currentToken = IdOSUtils.generateHandlerToken(this.credentials.get("servicePrivateKey"),
                    this.credentials.get("servicePublicKey"), this.credentials.get("credentialPublicKey"));
            break;
        case NONE:
            this.currentToken = "none";
            break;
        default:
            throw new InvalidToken();
        }

        return this.currentToken;
    }

    /**
     * Sets Credentials (public and private keys)
     *
     * @param credentials
     *            HashMap<String, String>
     */
    public void setCredentials(HashMap<String, String> credentials) {
        this.credentials = credentials;
    }

    /**
     * Gets the Auth type
     * 
     * @return authType
     */
    public IdOSAuthType getAuthType() {
        return authType;
    }

}
