package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

public class ProfileProcesses extends AbstractEndpoint {
    /**
     * Class Constructor
     */
    public ProfileProcesses(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all profiles processes
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/processes");
    }

    /**
     * Retrieves an process given its process name
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
