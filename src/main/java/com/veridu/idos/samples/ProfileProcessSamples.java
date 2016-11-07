package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileProcessSamples {
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
         * Lists all processes related to the provided userName.
         */
        json = idOSAPIFactory.getProcess().listAll(Config.userName);

        /**
         * Prints api call response to Processes endpoint
         */
        System.out.println("List All: ");
        for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
            System.out.println(json.get("data").getAsJsonArray().get(i));
        }

        /**
         * Stores the process id of the first index of the api call response.
         */
        int processId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();

        /**
         * Retrieves information about the process related to the stored
         * processId.
         */
        json = idOSAPIFactory.getProcess().getOne(Config.userName, processId);

        /**
         * Prints api call response to Processes endpoint
         */
        System.out.println("Get One: ");
        System.out.println(json.get("data").getAsJsonObject());
    }
}
