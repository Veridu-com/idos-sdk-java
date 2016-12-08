package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * SSO Endpoint Class
 *
 * @version 2.0
 */
public class SSO extends AbstractEndpoint {

    /**
     * Constructor Class
     *
     * @throws InvalidToken
     */
    public SSO(String baseURL, boolean doNotCheckSSL) {
        super(null, IdOSAuthType.NONE, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all providers
     *
     * @return JsonObject
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "sso");
    }

    /**
     * Creates a new SSO provider(OAuth2)
     *
     * @param providerName
     * @param credentialPublicKey
     * @param accessToken
     * @return JsonObject
     * @throws SDKException
     */
    public JsonObject create(String providerName, String credentialPublicKey, String accessToken) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("provider", providerName);
        data.addProperty("credential", credentialPublicKey);
        data.addProperty("access_token", accessToken);

        return this.fetch("POST", "sso", data);
    }

    /**
     * Creates a new SSO provider(OAuth1)
     *
     * @param providerName
     * @param credentialPublicKey
     * @param accessToken
     * @param tokenSecret
     * @return JsonObject
     * @throws SDKException
     */
    public JsonObject create(String providerName, String credentialPublicKey, String accessToken, String tokenSecret)
            throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("provider", providerName);
        data.addProperty("credential", credentialPublicKey);
        data.addProperty("access_token", accessToken);
        data.addProperty("token_secret", tokenSecret);
        return this.fetch("POST", "sso", data);
    }

    /**
     * Retrieves the status of a sso provider given its provider name
     *
     * @param providerName
     * @return JsonObject
     * @throws SDKException
     */
    public JsonObject getOne(String providerName) throws SDKException {
        return this.fetch("GET", "sso/" + providerName);
    }
}
