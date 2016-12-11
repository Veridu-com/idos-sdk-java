package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

import java.util.HashMap;

public class ProfileProcesses extends AbstractEndpoint {
    /**
     * Class Constructor
     *
     * @param credentials
     * @param baseURL
     * @param doNotCheckSSL
     */
    public ProfileProcesses(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, IdOSAuthType.HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Lists all profiles processes related to the given user
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/processes");
    }

    /**
     * Retrieves an process given its process id
     *
     * @param username
     * @param processId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int processId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/processes/" + processId);
    }
}
