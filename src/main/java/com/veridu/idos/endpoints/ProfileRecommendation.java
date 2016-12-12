package com.veridu.idos.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static com.veridu.idos.utils.IdOSAuthType.*;

public class ProfileRecommendation extends AbstractEndpoint {
    /**
     * Class constructor
     */
    public ProfileRecommendation(HashMap<String, String> credentials, String baseURL, boolean doNotCheckSSL) {
        super(credentials, HANDLER, baseURL, doNotCheckSSL);
    }

    /**
     * Retrieves the profile Recommendation for the provided username
     *
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/recommendation");
    }

    /**
     * Updates or creates the profile recommendation for the provided username
     *
     * @param username
     * @param result
     * @param passed
     * @param failed
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject upsert(String username, String result, HashMap<String, String> passed, HashMap<String, String> failed)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();

        JsonObject jsonPassed = new JsonObject();
        for (String key : passed.keySet())
            jsonPassed.addProperty(key, passed.get(key));

        JsonObject jsonFailed = new JsonObject();
        for (String key : failed.keySet())
            jsonPassed.addProperty(key, failed.get(key));

        data.addProperty("result", result);
        data.add("passed", jsonPassed);
        data.add("failed", jsonFailed);
        return this.fetch("PUT", "profiles/" + username + "/recommendation", data);
    }
}
