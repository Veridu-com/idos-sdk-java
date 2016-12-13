package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * ProfileSources Endpoint class
 */
public class ProfileSources extends AbstractEndpoint {
    /**
     * Constructor Class
     *
     * @param credentials
     * @param baseURL
     * @param doNotCheckSSL
     */
    public ProfileSources(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.USER, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all sources related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources");
    }

    /**
     * Lists all sources related to the given user with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources", null, filter);
    }

    /**
     * Retrieves a source given its source id
     *
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int sourceId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId);
    }

    /**
     * Creates a source for the given username
     *
     * @param username
     * @param name
     * @param tags
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String name, HashMap<String, String> tags) throws SDKException {
        JsonObject jsonTags = new JsonObject();
        for (String key : tags.keySet())
            jsonTags.addProperty(key, tags.get(key));
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.add("tags", jsonTags);
        return this.fetch("POST", "profiles/" + username + "/sources", data);
    }

    /**
     * Updates a source giving its sourceId
     *
     * @param username
     * @param tags
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(String username, int sourceId, HashMap<String, String> tags) throws SDKException {
        JsonObject jsonTags = new JsonObject();
        for (String key : tags.keySet())
            jsonTags.addProperty(key, tags.get(key));
        JsonObject data = new JsonObject();
        data.add("tags", jsonTags);
        return this.fetch("PATCH", "profiles/" + username + "/sources/" + sourceId, data);
    }

    /**
     * Deletes a source given its source id
     *
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, int sourceId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId);
    }

    /**
     * Deletes all sources related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources");
    }
}
