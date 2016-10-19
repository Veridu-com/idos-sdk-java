package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

import java.io.UnsupportedEncodingException;

public class ProfileCandidateSamples {
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
        String username = "f67b96dcf96b49d713a520ce9f54053c";

        /**
         * Gets the response from the API listing all attributes
         */
        JsonObject json = idOSAPIFactory.getCandidates().listAll(username);

        /**
         * Prints the response
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new attribute
         */
        json = idOSAPIFactory.getCandidates().create(username, "attributeName", "attributeValue", 0.5);

        /**
         * Get the response form the API getting one attribute
         */
        json = idOSAPIFactory.getCandidates().getOne(username, "attributeName");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the attribute created giving the attribute name
         */
        json = idOSAPIFactory.getCandidates().delete(username, "attributeName");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile attributes related to the username
         */
        json = idOSAPIFactory.getCandidates().deleteAll(username);

        /**
         * Prints the number of deleted attributes
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
