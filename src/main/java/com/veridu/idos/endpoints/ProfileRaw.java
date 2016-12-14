package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

import java.util.HashMap;

/**
 * Profile Raw Endpoint Class
 *
 * @version 2.0
 *
 */
public class ProfileRaw extends AbstractEndpoint {
    /**
     * Class Constructor
     *
     * @param credentials
     * @param baseURL
     * @param doNotCheckSSL
     */
    public ProfileRaw(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all raw data related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/raw");
    }

    /**
     * Lists all raw data related to the given user with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/raw", null, filter);
    }

    /**
     * Creates a new raw data for the given user
     *
     * @param username
     * @param sourceId
     * @param collection
     * @param collectionData
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, int sourceId, String collection, HashMap<String, String> collectionData)
            throws SDKException {
        JsonObject array = new JsonObject();
        for (String key : collectionData.keySet()) {
            array.addProperty(key, collectionData.get(key));
        }
        JsonObject data = new JsonObject();
        data.addProperty("collection", collection);
        data.add("data", array);
        data.addProperty("source_id", sourceId);

        return this.fetch("POST", "profiles/" + username + "/raw", data);
    }

    /**
     * Creates or updates a raw data for the given user
     *
     * @param username
     * @param sourceId
     * @param collection
     * @param collectionData
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject upsert(String username, int sourceId, String collection, HashMap<String, String> collectionData)
            throws SDKException {
        JsonObject array = new JsonObject();
        for (String key : collectionData.keySet()) {
            array.addProperty(key, collectionData.get(key));
        }
        JsonObject data = new JsonObject();
        data.addProperty("collection", collection);
        data.add("data", array);
        data.addProperty("source_id", sourceId);

        return this.fetch("PUT", "profiles/" + username + "/raw", data);
    }

    /**
     * Deletes all raw data related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/raw");
    }
}
