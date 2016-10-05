package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

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
        IdOSAPIFactory factory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Lists all sources to get the id of the first one
         */
        json = factory.getSource().listAll(username);

        int sourceId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
        System.out.println(sourceId);

        /**
         * Prints the response
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new raw
         */

        HashMap<String, String> data = new HashMap<>();
        data.put("value", "test");

        json = factory.getRaw().create(username, sourceId, "collection-test", data);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Gets the response from the API listing all raw
         */
        json = factory.getRaw().listAll(username);

        /**
         * Prints the api response
         */
        System.out.println(json);

    }

}
