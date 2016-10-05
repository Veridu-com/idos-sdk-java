package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileReferenceSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
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
         * Gets the response from the API listing all references
         */
        JsonObject json = idOSAPIFactory.getReference().listAll(username);

        /**
         * Prints the response
         */
        System.out.println(json.get("data").getAsJsonArray());

        /**
         * Gets the response from the API trying to create a new reference
         */
        json = idOSAPIFactory.getReference().create(username, "attributeName", "attributeValue");

        /**
         * Get the response form the API getting one reference
         */
        json = idOSAPIFactory.getReference().getOne(username, "attributeName");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the reference created giving the reference name
         */
        json = idOSAPIFactory.getReference().delete(username, "attributeName");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile references related to the username
         */
        json = idOSAPIFactory.getReference().deleteAll(username);

        /**
         * Prints the number of deleted references
         */
        System.out.println(json.get("deleted").getAsInt());

    }

}
