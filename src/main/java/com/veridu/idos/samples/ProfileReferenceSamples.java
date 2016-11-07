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
         * To instantiate the idOSAPIFactory object, responsible to call the
         * endpoints, its necessary to pass throughout the constructor a HashMap
         * containing all credentials related to the type of authorization
         * required by the endpoint desired. The method getCredentials() from
         * the IdOSSamplesHelper Class, gets the credentials from the
         * settings.Config class and returns the HashMap containing the
         * credentials.
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Creates a new reference. To create a new reference is necessary to
         * call the createNew() method passing as parameter: the userName, the
         * reference name and the reference value.
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
             * Updates the created reference passing as parameter the
             * reference's name and the new reference value.
             */
            json = idOSAPIFactory.getReference().update(Config.userName, "reference", "new-value");

            /**
             * Retrieves information about one reference, if status of the last
             * request is true.
             */
            if (json.get("status").getAsBoolean() == true) {
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
