package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileAttributeSamples {
    public static void main(String[] args) throws UnsupportedEncodingException, InvalidToken, SDKException {
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
         * Creates a candidate to be listed in the attributes endpoint. To
         * create a new candidate, it is necessary to call the function create()
         * passing the stored username, the attribute name, the value of the
         * attribute and the support value as parameter.
         */
        json = idOSAPIFactory.getCandidate().create(Config.userName, "email", "jhon@jhon.com", 0.9);

        /**
         * Checks if the candidate was created before calling other methods
         * related to the candidates (requires an existing candidate).
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Lists all attributes related to the userName provided.
             */
            json = idOSAPIFactory.getAttribute().listAll(Config.userName);

            /**
             * Prints api call response to Candidates endpoint.
             */
            for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
                System.out.println(json.get("data").getAsJsonArray().get(i));
            }
        }
    }
}
