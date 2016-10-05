package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileProcessSamples {
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
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         */

        /* Username necessary for all requests of this endpoint */
        String username = "f67b96dcf96b49d713a520ce9f54053c";

        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Gets the response from the API listing all processes
         */
        JsonObject json = idOSAPIFactory.getProcess().listAll(username);

        /**
         * Prints the response
         */
        System.out.println(json.get("data").getAsJsonArray());

        int id = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();

        /**
         * Get the response form the API getting one process
         */
        json = idOSAPIFactory.getProcess().getOne(username, id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());
    }
}
