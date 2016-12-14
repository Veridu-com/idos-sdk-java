package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * ProfileFlagsTest Endpoint class
 *
 * @version 2.0
 */
public class ProfileFlags extends AbstractEndpoint {
    /**
     * Class Constructor
     *
     * @param credentials
     * @param baseURL
     * @param doNotCheckSSL
     */
    public ProfileFlags(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all flags for the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/flags");
    }

    /**
     * Lists all flags for the given user with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/flags", null, filter);
    }

    /**
     * Retrieves flags related to the given flag slug
     *
     * @param username
     * @param flagSlug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String flagSlug) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/flags/" + flagSlug);
    }

    /**
     * Creates a new flag for the given user
     *
     * @param username
     * @param slug
     * @param attribute
     *            name of the attribute to which the flag refers to
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String slug, String attribute) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("slug", slug);
        data.addProperty("attribute", attribute);

        return this.fetch("POST", "profiles/" + username + "/flags", data);
    }

    /**
     * Deletes a flag given its flag slug
     *
     * @param username
     * @param flagSlug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String flagSlug) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/flags/" + flagSlug);
    }

    /**
     * Deletes all flags related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/flags");
    }

    /**
     * Deletes all flags related to the given user with filtering
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/flags", null, filter);
    }
}
