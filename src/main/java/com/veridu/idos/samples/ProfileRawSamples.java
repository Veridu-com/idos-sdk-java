package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileRawSamples {

    public static void main(String[] args) throws UnsupportedEncodingException, SDKException {
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
         * To create new raw data, it is necessary to provide a source id. To
         * create a new source, it is necessary to create a new hashMap
         * containing all the tags the new source will have.
         */
        HashMap<String, String> tags = new HashMap<>();
        tags.put("tag-1", "value-1");
        tags.put("tag-2", "value-2");

        /**
         * Creates a new source to be used in the feature endpoint. To create a
         * new source, it is necessary to pass the userName, the source name and
         * the HashMap<String, String> tags, as a parameter containing all the
         * tags related to the new source to be created.
         */
        json = idOSAPIFactory.getSource().create(Config.userName, "name-test", tags);

        /**
         * Stores the source id of the created source
         */
        int sourceId = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Creates a new raw data. To create a new raw data, it is necessary to
         * call the create() method passing the userName, the stored sourceId,
         * the collection name and the HashMap containing all the tags as a
         * parameter.
         */
        HashMap<String, String> data = new HashMap<>();
        data.put("value", "test");
        json = idOSAPIFactory.getRaw().create(Config.userName, sourceId, "collection-test", data);

        /**
         * Checks if the raw data was created before calling other methods
         * related to the raw endpoint (requires an existing raw data).
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Lists all raw related to the provided userName
             */
            json = idOSAPIFactory.getRaw().listAll(Config.userName);

            /**
             * Prints the api response to Raw Endpoint
             */
            for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
                System.out.println(json.get("data").getAsJsonArray().get(i).getAsJsonObject());
            }
        }
    }
}
