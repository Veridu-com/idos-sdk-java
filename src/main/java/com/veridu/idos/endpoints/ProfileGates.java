package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Profile Gates Endpoint Class
 *
 * @version 2.0
 *
 */
public class ProfileGates extends AbstractEndpoint {
    /**
     * Class Constructor
     *
     * @param credentials
     * @param baseURL
     * @param doNotCheckSSL
     */
    public ProfileGates(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all gates related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/gates");
    }

    /**
     * Lists all gates related to the given user with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/gates", null, filter);
    }

    /**
     * Retrieves a gate given its gate name
     *
     * @param username
     * @param gateName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String gateName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/gates/" + gateName);
    }

    /**
     * Creates a new gate for the given user
     *
     * @param username
     * @param gateName
     * @param confidenceLevel
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String gateName, String confidenceLevel)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", gateName);
        data.addProperty("confidence_level", confidenceLevel);
        return this.fetch("POST", "profiles/" + username + "/gates", data);
    }

    /**
     * Updates or creates a gate for the given user
     *
     * @param username
     * @param gateName
     * @param confidenceLevel
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String gateName, String confidenceLevel)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", gateName);
        data.addProperty("confidence_level", confidenceLevel);
        return this.fetch("PUT", "profiles/" + username + "/gates", data);
    }

    /**
     * Updates a gate for the given gate name
     *
     * @param username
     * @param gateName
     * @param confidenceLevel
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, String gateName, String confidenceLevel)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("confidence_level", confidenceLevel);
        return this.fetch("PATCH", "profiles/" + username + "/gates/" + gateName, data);
    }

    /**
     * Deletes a gate related to the given gate name
     *
     * @param username
     * @param gateName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String gateName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/gates/" + gateName);
    }

    /**
     * Deletes all gates related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/gates");
    }

    /**
     * Deletes all gates related to the given user with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/gates", null, filter);
    }
}
