package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

public class ProfileFeatures extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public ProfileFeatures(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Retrieve a complete list of all features that belong to the given user.
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/features");
    }

    /**
     * Retrieve a complete list of all features that belong to the given user,
     * with filtering.
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/features", null, filter);
    }

    /**
     * Retrieves all public information from a Feature for the given user
     *
     * @param username
     * @param featureId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int featureId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/features/" + featureId);
    }

    /**
     * Update or insert a new profile feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, int value)
            throws SDKException, UnsupportedEncodingException {
        return upsert(username, name, 0, value);
    }

    /**
     * Update or insert a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, int sourceId, int value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "integer");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        return this.fetch("PUT", "profiles/" + username + "/features", data);
    }

    /**
     * Update or insert a new profile feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, double value)
            throws SDKException, UnsupportedEncodingException {
        return upsert(username, name, 0, value);
    }

    /**
     * Update or insert a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, int sourceId, double value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "double");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        return this.fetch("PUT", "profiles/" + username + "/features", data);
    }

    /**
     * Update or insert a new profile feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, String value)
            throws SDKException, UnsupportedEncodingException {
        return upsert(username, name, 0, value);
    }

    /**
     * Update or insert a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, int sourceId, String value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "string");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        return this.fetch("PUT", "profiles/" + username + "/features", data);
    }

    /**
     * Update or insert a new profile feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, boolean value)
            throws SDKException, UnsupportedEncodingException {
        return upsert(username, name, 0, value);
    }

    /**
     * Update or insert a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String name, int sourceId, boolean value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "boolean");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        return this.fetch("PUT", "profiles/" + username + "/features", data);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, int value)
            throws SDKException, UnsupportedEncodingException {
        return create(username, name, 0, value);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param sourceId
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, int sourceId, int value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "integer");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        return this.fetch("POST", "profiles/" + username + "/features", data);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, int sourceId, double value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "double");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        return this.fetch("POST", "profiles/" + username + "/features", data);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, double value)
            throws SDKException, UnsupportedEncodingException {
        return this.create(username, name, 0, value);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, int sourceId, boolean value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        data.addProperty("type", "boolean");
        return this.fetch("POST", "profiles/" + username + "/features", data);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, boolean value)
            throws SDKException, UnsupportedEncodingException {
        return this.create(username, name, 0, value);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, int sourceId, String value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "string");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        return this.fetch("POST", "profiles/" + username + "/features", data);
    }

    /**
     * Create a new feature for the given user.
     *
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, String value)
            throws SDKException, UnsupportedEncodingException {
        return this.create(username, name, 0, value);
    }

    /**
     * Updates Feature's specific information.
     *
     * @param username
     * @param featureId
     * @param sourceId
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, int featureId, int sourceId, String value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("value", value);
        data.addProperty("type", "string");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);

        return this.fetch("PATCH", "profiles/" + username + "/features/" + featureId, data);
    }

    /**
     * Updates Feature's specific information.
     *
     * @param username
     * @param featureId
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, int featureId, int sourceId, double value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("value", value);
        data.addProperty("type", "double");
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);

        return this.fetch("PATCH", "profiles/" + username + "/features/" + featureId, data);
    }

    /**
     * Updates Feature's specific information.
     *
     * @param username
     * @param featureId
     * @param sourceId
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, int featureId, int sourceId, int value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        data.addProperty("value", value);
        data.addProperty("type", "integer");

        return this.fetch("PATCH", "profiles/" + username + "/features/" + featureId, data);
    }

    /**
     * Updates Feature's specific information.
     *
     * @param username
     * @param featureId
     * @param sourceId
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, int featureId, int sourceId, boolean value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        if (sourceId != 0)
            data.addProperty("sourceId", sourceId);
        data.addProperty("value", value);
        data.addProperty("type", "boolean");

        return this.fetch("PATCH", "profiles/" + username + "/features/" + featureId, data);
    }

    /**
     * Deletes a single Feature that belongs to the given user
     *
     * @param username
     * @param featureId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, int featureId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/features/" + featureId);
    }

    /**
     * Deletes all features that belongs to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/features");
    }

    /**
     * Deletes all features that belongs to the given user, with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/features", null, filter);
    }

}
