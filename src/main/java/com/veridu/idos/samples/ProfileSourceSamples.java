package com.veridu.idos.samples;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileSourceSamples {

    public static void main(String[] args) throws SDKException {
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
         * Creates a HashMap putting the necessary tags to create a new source.
         */
        HashMap<String, String> tags = new HashMap<>();
        tags.put("sms-test", "sms");

        /**
         * Creates or updates a score. The upsertOne method checks if the score
         * already exists on the database, if so, it updates it. Otherwise, it
         * creates a new score. To create or update a score it is necessary to
         * call the method upsertOne() passing the attribute name, the score
         * name and the score value as a parameter.
         */
        json = idOSAPIFactory.getSource().create(Config.userName, "sms", tags);

        /**
         * Checks if at least one source was created before calling other
         * methods related to the sources endpoint(requires an existing source).
         */
        if (json.get("status").getAsBoolean() == true) {

            /**
             * Lists all sources related to the provided userName.
             */
            json = idOSAPIFactory.getSource().listAll(Config.userName);

            /**
             * Prints the api call response to Sources endpoint.
             */
            for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
                System.out.println(json.get("data").getAsJsonArray());
            }

            /**
             * Stores the source id of the source created.
             */
            int sourceId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();

            /**
             * Creates a HashMap putting the necessary tags to update a source.
             */
            HashMap<String, String> newTags = new HashMap<>();
            newTags.put("email-test", "email");

            /**
             * Updates the source created passing the stored sourceId and the
             * tags array containing the tags and its values as a parameter.
             */
            json = idOSAPIFactory.getSource().update(Config.userName, sourceId, newTags);

            /**
             * Prints the api call response to Sources endpoint.
             */
            System.out.println(json.get("data").getAsJsonObject());

            /**
             * Deletes the source created passing the stored sourceId as a
             * parameter.
             */
            json = idOSAPIFactory.getSource().delete(Config.userName, sourceId);

            /**
             * Prints the status of api call response to Sources endpoint.
             */
            System.out.println(json.get("status").getAsBoolean());
        }

        /**
         * Deletes all profile sources related to the provided userName.
         */
        json = idOSAPIFactory.getSource().deleteAll(Config.userName);

        /**
         * Prints the number of deleted sources, information from the api call
         * response to Sources endpoint.
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
