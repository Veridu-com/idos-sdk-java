package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * Profile Attributes Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileAttributes extends AbstractEndpoint {
    /**
     * Class Constructor
     */
    public ProfileAttributes(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all profiles attributes
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/attributes");
    }

    /**
     * Lists all profiles attributes, with filtering
     * 
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/attributes", null, filter);
    }

    /**
     * Retrieves an attribute given its attribute name
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String attributeName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/attributes/" + attributeName);
    }

    /**
     * Creates a new attribute passing the name of the attribute
     * 
     * @param username
     * @param attributeName
     * @param attributeValue
     * @param attributeSupport
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String attributeName, String attributeValue, double attributeSupport)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", attributeName);
        data.addProperty("value", attributeValue);
        data.addProperty("support", attributeSupport);
        return this.fetch("POST", "profiles/" + username + "/attributes", data);
    }

    /**
     * Deletes an attribute given its attributeName
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String attributeName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/attributes/" + attributeName);
    }

    /**
     * Deletes all attributes related to the given username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/attributes");
    }

    /**
     * Deletes all attributes related to the given username, with filtering
     * 
     * @param username
     * @param
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/attributes", null, filter);
    }

}
