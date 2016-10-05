package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileTaskSamples {
    public static void main(String[] args) throws SDKException {
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
        IdOSAPIFactory credentialFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all tasks
         */
        JsonObject json = credentialFactory.getTask().listAll(username, 1321189817);

        /**
         * Prints the response
         */
        System.out.println(json);

        /**
         * Creates a new task
         */
        json = credentialFactory.getTask().create(username, 1321189817, "Testing", "testing", true);

        /**
         * Prints the api response
         */
        System.out.println(json);

        /**
         * Get the response form the API getting one task
         */
        json = credentialFactory.getTask().getOne(username, 1321189817,
                json.get("data").getAsJsonObject().get("id").getAsInt());

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

    }

}
