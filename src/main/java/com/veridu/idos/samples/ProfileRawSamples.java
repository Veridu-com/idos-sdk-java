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
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject json = null;
        /**
         * IdOSAPIFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an IdOSAPIFactory Class, and accessed through this
         * object
         * 
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * To create a new raw data, its necessary to provide a source id. To
         * create a new source, its necessary to create a new hashMap containing
         * all the tags the new source will have.
         */
        HashMap<String, String> tags = new HashMap<>();
        tags.put("tag-1", "value-1");
        tags.put("tag-2", "value-2");

        /**
         * Creates a new source to be used in the feature endpoint. To create a
         * new source, its necessary to pass as paremeter the userName, the
         * source name and the HashMap<String, String> tags, containing all the
         * tags related to the new source to be created.
         */
        json = idOSAPIFactory.getSource().create(Config.userName, "name-test", tags);

        /**
         * Stores the source id of the created source
         */
        int sourceId = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Creates a new raw data
         */
        HashMap<String, String> data = new HashMap<>();
        data.put("value", "test");
        json = idOSAPIFactory.getRaw().create(Config.userName, sourceId, "collection-test", data);

        /**
         * Checks if the raw data was created before calling other methods
         * related to the raw endpoint that requires an existing raw data.
         */
        if (json.get("status").getAsBoolean() == true) {
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
