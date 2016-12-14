package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Profile References Endpoint Class
 *
 * @version 2.0
 *
 */
public class ProfileReferences extends AbstractEndpoint {
    /**
     * Class Constructor
     *
     * @param credentials
     * @param baseURL
     * @param doNotCheckSSL
     */
    public ProfileReferences(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all references for the given username
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/references");
    }

    /**
     * Lists all references for the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/references", null, filter);
    }

    /**
     * Retrieves a reference given its attribute name
     *
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String attributeName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/references/" + attributeName);
    }

    /**
     * Creates a reference for the given user
     *
     * @param username
     * @param attributeName
     * @param attributeValue
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String attributeName, String attributeValue)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", attributeName);
        data.addProperty("value", attributeValue);
        return this.fetch("POST", "profiles/" + username + "/references", data);
    }

    /**
     * Updates a reference given the attribute name
     *
     * @param username
     * @param attributeName
     * @param attributeValue
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(String username, String attributeName, String attributeValue) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("value", attributeValue);
        return this.fetch("PATCH", "profiles/" + username + "/references/" + attributeName, data);
    }

    /**
     * Deletes a reference given the attribute name
     *
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String attributeName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/references/" + attributeName);
    }

    /**
     * Deletes all references related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/references");
    }

    /**
     * Deletes all references related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/references", null, filter);
    }
}
