package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileScoreSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
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
         * Creates or updates a score. The upsert() method checks if the score
         * already exists on the database, if so, it updates it. Otherwise, it
         * creates a new score. To create or update a score is necessary to call
         * the method upsert() passing as parameter the userName, the attribute
         * name, the score name and the score value.
         */
        json = idOSAPIFactory.getScore().upsert(Config.userName, "lastName", "Doe", 0.6);

        /**
         * Checks if the score was created before calling other methods related
         * to the scores endpoint that requires an existing score.
         */
        if (json.get("status").getAsBoolean() == true) {

            /**
             * Lists all scores related to the provided userName.
             */
            json = idOSAPIFactory.getScore().listAll(Config.userName);

            /**
             * Prints the api call response to Scores endpoint.
             */
            for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
                System.out.println(json.get("data").getAsJsonArray().get(i).getAsJsonObject());
            }

            /**
             * Retrieves information of the score created/updated giving the
             * userName and the score's name as parameter to the getOne()
             * method.
             */
            json = idOSAPIFactory.getScore().getOne(Config.userName, "lastName");

            /**
             * Prints api call response to Scores endpoint
             */
            System.out.println(json.get("data").getAsJsonObject());

            /**
             * Deletes the score created passing as parameter the username and
             * the score name
             */
            json = idOSAPIFactory.getScore().delete(Config.userName, "lastName");

            /**
             * Prints the status of the api call response to Scores endpoint.
             */
            System.out.println(json.get("status").getAsBoolean());

        }

        /**
         * Deletes all attribute scores related to the username
         */
        json = idOSAPIFactory.getScore().deleteAll(Config.userName);

        /**
         * Prints the number of deleted scores, information given by the api
         * call response to Scores endpoint;
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
