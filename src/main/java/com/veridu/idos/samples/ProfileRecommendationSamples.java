package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class ProfileRecommendationSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to store the api call response
         *
         * @see https://github.com/google/gson
         */
        JsonObject json = null;

        /**
         * To instantiate the idOSAPIFactory object, which is responsible for
         * calling the endpoints, it iss necessary to pass throughout the
         * constructor a HashMap containing all credentials related to the type
         * of authorization required by the desired endpoint. The method
         * getCredentials() from the IdOSSamplesHelper Class, gets the
         * credentials from the settings.Config class and returns the HashMap
         * containing the credentials.
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Creates or updates a recommendation. To create a new recommendation it is necessary to
         * call the upsert() method passing the userName, the result, and both array containing passed
         * and failed values as parameters.
         */
        HashMap<String, String> passed = new HashMap<>();
        HashMap<String, String> failed = new HashMap<>();
        json = idOSAPIFactory.getRecommendation().upsert(Config.userName, "result", passed, failed);

        /**
         * Prints the json response
         */
        System.out.println(json.get("data").getAsJsonObject());
    }
}
