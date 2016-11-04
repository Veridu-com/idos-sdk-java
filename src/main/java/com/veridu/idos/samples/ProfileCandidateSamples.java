package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileCandidateSamples {
    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
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

        /* Username necessary for all requests of this endpoint */
        String username = "f67b96dcf96b49d713a520ce9f54053c";

        /**
         * Creates a candidate to be listed in the attributes endpoint. To
         * create a new candidate, its necessary to call the function create()
         * passing as parameter the stored username, the attribute name, the
         * value of the attribute and the support value.
         */
        json = idOSAPIFactory.getCandidates().create(username, "email", "jhon@jhon.com", 0.9);

        /**
         * Checks if the candidates were created before calling other methods
         * related to the candidates that requires an existing candidate.
         */
        if (json.get("status").getAsBoolean() == true) {

            /**
             * Lists all candidates related to the username provided
             */
            json = idOSAPIFactory.getCandidates().listAll(username);

            /**
             * Prints api call response to Candidates endpoint
             */
            System.out.println(json);

            /**
             * Deletes all candidates related to the username provided.
             */
            json = idOSAPIFactory.getCandidates().deleteAll(username);

            /**
             * Prints the number of deleted candidates, information received
             * from the api call response to Candidates endpoint
             */
            System.out.println(json.get("deleted").getAsInt());
        }
    }
}
