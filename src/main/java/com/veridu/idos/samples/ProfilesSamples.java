package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfilesSamples {
    public static void main(String[] args) throws SDKException {
        /**
         * JsonObject used to store the api call response
         *
         * @see https://github.com/google/gson
         */
        JsonObject json = null;

        /**
         * To instantiate the idOSAPIFactory object, responsible to call the
         * endpoints, its necessary to pass throughout the constructor a HashMap
         * containing all credentials related to the type of authorization
         * required by the endpoint desired. The method getCredentials() from
         * the IdOSSamplesHelper Class, gets the credentials from the
         * settings.Config class and returns the HashMap containing the
         * credentials.
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Lists all profiles
         */
        json = idOSAPIFactory.getProfile().listAll();

        /**
         * Prints each item of the list of profiles, information coming from the
         * api call response to Profiles Endpoint.
         */
        for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
            System.out.println(json.get("data").getAsJsonArray().get(i).getAsJsonObject());
        }
    }
}
