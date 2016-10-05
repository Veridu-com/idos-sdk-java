package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;

public class ProfileFeatureSamples {
    public static void main(String[] args) throws InvalidToken, SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
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

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all features
         */
        Filter filter = Filter.createFilter();
        filter.setAllPagesTrue();
        JsonObject json = idOSAPIFactory.getFeature().listAll(username, filter);

        /**
         * Prints the response
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new feature
         */
        json = idOSAPIFactory.getFeature().create(username, "Testing", "testing");

        /**
         * get the featureId
         */
        int id = json.get("id").getAsInt();

        /**
         * Get the response form the API getting one feature
         */
        json = idOSAPIFactory.getFeature().getOne(username, id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the feature giving the feature-slug
         */
        json = idOSAPIFactory.getFeature().update(username, id, 2, "new value");

        /**
         * Prints the response response
         */
        System.out.println(json);

        /**
         * Deletes the credential feature giving the feature name
         */
        json = idOSAPIFactory.getFeature().delete(username, id);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());
    }
}
