package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * ProfileSources Endpoint class
 * 
 * @version 2.0
 *
 */
public class ProfileSources extends AbstractEndpoint {

    /**
     * Constructor class
     * 
     * @param token
     */
    public ProfileSources(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.USER);
    }

    /**
     * Lists all sources given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources");
    }

    /**
     * Lists all sources given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources", null, filter);
    }

    /**
     * Retrieves a source given its source name
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
     * Creates a source passing the source name
     * 
     * @param username
     * @param sourceId
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
     * Deletes a source given the source name
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
     * Deletes all sources associated with a username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources");
    }
}
