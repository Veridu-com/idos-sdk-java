package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileFeatureSamples {
    public static void main(String[] args) throws InvalidToken, SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to store the api call response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject json = null;
        /**
         * To instantiate the featureIdOSAPIFactory object, responsible to call
         * the endpoints, its necessary to pass throughout the constructor a
         * HashMap containing all credentials related to the type of
         * authorization required by the endpoint desired. The method
         * getCredentials() from the IdOSSamplesHelper Class, gets the
         * credentials from the settings.Config class and returns a HashMap
         * containing the credentials.
         */
        IdOSAPIFactory featureIdOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * To create a new source, it is necessary to create a new hashMap
         * containing all the tags the new source will have.
         */
        HashMap<String, String> tags = new HashMap<>();
        tags.put("tag-1", "value-1");
        tags.put("tag-2", "value-2");

        /**
         * Creates a new source to be used in the feature endpoint. To create a
         * new source, it is necessary to pass the userName, the source name and
         * the HashMap<String, String> tags, containing all the tags related to
         * the new source to be created, all as parameters.
         */
        json = featureIdOSAPIFactory.getSource().create(Config.userName, "name-test", tags);

        /**
         * Stores the source id of the created source.
         */
        int sourceId = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Deletes all features related to the userName provided to avoid
         * creating a repeated feature.
         */
        json = featureIdOSAPIFactory.getFeature().deleteAll(Config.userName);

        /**
         * Creates a new feature. To create a new feature, it is necessary to
         * call the create() method passing the userName, the source id, the
         * feature name, the feature value and the type of the feature value as
         * a parameter.
         */
        json = featureIdOSAPIFactory.getFeature().create(Config.userName, "Testing", "testing");

        /**
         * Checks if the feature was created before calling other methods
         * related to the features endpoint (requires an existing feature).
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Lists all features related to the Config.userName provided
             */
            json = featureIdOSAPIFactory.getFeature().listAll(Config.userName);

            /**
             * Prints api call response to Features endpoint
             */
            System.out.println(json.get("data"));

            /**
             * Stores the feature id of the feature created
             */
            int featureId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();

            /**
             * Retrieves information of the feature created passing the stored
             * feature id
             */
            json = featureIdOSAPIFactory.getFeature().getOne(Config.userName, featureId);

            /**
             * Prints api call response to Features endpoint
             */
            System.out.println(json);

            /**
             * Updates the feature created passing the userName, feature id, the
             * new feature's value and the type of the new value as a parameter.
             */
            json = featureIdOSAPIFactory.getFeature().update(Config.userName, featureId, 2, "new value");

            /**
             * Prints api call response to Features endpoint
             */
            System.out.println(json.get("data"));

            /**
             * Deletes the updated feature passing the feature id stored
             */
            json = featureIdOSAPIFactory.getFeature().delete(Config.userName, featureId);

            /**
             * Prints the status of the api call response.
             */
            System.out.println(json.get("status").getAsBoolean());
        }
    }
}
