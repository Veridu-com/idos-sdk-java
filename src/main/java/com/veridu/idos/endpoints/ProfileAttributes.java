package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * Profile Candidates Endpoint Class
 *
 * @version 2.0
 *
 */
public class ProfileAttributes extends AbstractEndpoint {
    /**
     * Class Constructor
     */
    public ProfileAttributes(HashMap<String, String> credentials) {
        super(credentials, IdOSAuthType.USER);
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
}
