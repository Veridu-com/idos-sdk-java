package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

/**
 * Profile References Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileReferences extends AbstractEndpoint {

    /**
     * Constructor class
     */
    public ProfileReferences(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all references given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/references");
    }

    /**
     * Lists all references given an username
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
     * Creates a reference passing the attribute name
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
     * Deletes all references associated with a username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/references");
    }

    /**
     * Deletes all references associated with a username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/references", null, filter);
    }
}
