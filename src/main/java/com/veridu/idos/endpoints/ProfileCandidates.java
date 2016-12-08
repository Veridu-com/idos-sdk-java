package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Profile Candidates Endpoint Class
 *
 * @version 2.0
 *
 */
public class ProfileCandidates extends AbstractEndpoint {
    /**
     * Class Constructor
     */
    public ProfileCandidates(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all profiles candidates
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/candidates");
    }

    /**
     * Lists all profiles candidates, with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/candidates", null, filter);
    }

    /**
     * Creates a new candidate passing the name of the attribute
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
        data.addProperty("attribute", attributeName);
        data.addProperty("value", attributeValue);
        data.addProperty("support", attributeSupport);
        return this.fetch("POST", "profiles/" + username + "/candidates", data);
    }

    /**
     * Deletes all candidates related to the given username
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/candidates");
    }

    /**
     * Deletes all candidates related to the given username, with filtering
     *
     * @param username
     * @param
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/candidates", null, filter);
    }

}
