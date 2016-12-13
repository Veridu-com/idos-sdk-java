package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

public class Profiles extends AbstractEndpoint {
    /**
     * Class constructor
     */
    public Profiles(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.USER, baseURL, doNotCheckSSL);
    }

    /**
     * Retrieve user details given the username
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username);
    }
}
