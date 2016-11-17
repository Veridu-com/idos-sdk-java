package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

public class Profiles extends AbstractEndpoint {
    /**
     * Class constructor
     */
    public Profiles(HashMap<String, String> credentials) {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Retrieve a complete list of all profiles
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "profiles");
    }
}
