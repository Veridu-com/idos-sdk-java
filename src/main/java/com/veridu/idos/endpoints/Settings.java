package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Settings Class
 *
 * @version 2.0
 */
public class Settings extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public Settings(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) throws InvalidToken {
        super(credentials, IdOSAuthType.IDENTITY, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all Settings for the given credential companyToken
     *
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "management/settings");
    }

    /**
     * Lists all Settings for the given credential companyToken
     *
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(Filter filter) throws SDKException {
        return this.fetch("GET", "management/settings", null, filter);
    }

    /**
     * Gets the setting for the given public key
     *
     * @param id
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(int id) throws SDKException {
        return this.fetch("GET", "management/settings/" + id);
    }

    /**
     * Creates a new setting given the section, property and value
     *
     * @param section
     * @param property
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String section, String property, String value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("section", section);
        data.addProperty("property", property);
        data.addProperty("value", value);

        return this.fetch("POST", "management/settings", data);
    }

    /**
     * Updates an existing setting given the value and the public key of the
     * setting
     *
     * @param value
     * @param id
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String value, int id) throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("value", value);
        return this.fetch("PATCH", "management/settings/" + id, data);
    }

    /**
     * Deletes an existing setting given the public key of the setting
     *
     * @param id
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(int id) throws SDKException {
        return this.fetch("DELETE", "management/settings/" + id);
    }

    /**
     * Deletes all settings associated with the credential companyToken
     *
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll() throws SDKException {
        return this.fetch("DELETE", "management/settings");
    }
}
