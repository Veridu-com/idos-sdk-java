package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileReferenceSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
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
         * Creates a new reference. To create a new reference it is necessary to
         * call the createNew() method passing the userName, the reference name
         * and the reference value as a parameter.
         */
        json = idOSAPIFactory.getReference().create(Config.userName, "reference", "value");

        if (json.get("status").getAsBoolean() == true) {
            /**
             * Lists all references related to the provided userName.
             */
            json = idOSAPIFactory.getReference().listAll(Config.userName);

            /**
             * Prints the api call response to References Endpoint
             */
            for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
                System.out.println(json.get("data").getAsJsonArray().get(i).getAsJsonObject());
            }

            /**
             * Updates the created reference passing the reference's name and
             * the new reference value as a parameter.
             */
            json = idOSAPIFactory.getReference().update(Config.userName, "reference", "new-value");

            /**
             * Retrieves information about one reference, if the status of the
             * last request is true.
             */
            if (json.get("status").getAsBoolean()) {
                json = idOSAPIFactory.getReference().getOne(Config.userName, "reference");

                /**
                 * Prints the api call response to References Endpoint
                 */
                System.out.println(json.get("data").getAsJsonObject());

                /**
                 * Deletes the reference created giving the reference name
                 */
                json = idOSAPIFactory.getReference().delete(Config.userName, "reference");

                /**
                 * Prints the status of the api call response
                 */
                System.out.println(json.get("status").getAsBoolean());
            }
            /**
             * Deletes all profile references related to the provided username
             */
            json = idOSAPIFactory.getReference().deleteAll(Config.userName);

            /**
             * Prints the number of deleted references, information from the api
             * call to References Endpoint.
             */
            System.out.println(json.get("deleted").getAsInt());
        }
    }
}
