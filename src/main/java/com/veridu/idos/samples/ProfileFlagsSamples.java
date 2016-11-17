package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileFlagsSamples {

    public static void main(String[] args) throws SDKException {

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
         * Creates a new flag. To create a new flag is necessary to call the
         * create() method passing the userName, the flag's name and the
         * attribute's name as a parameter.
         */
        json = idOSAPIFactory.getFlag().create(Config.userName, "middle-name-mismatch", "middle-name");

        if (json.get("status").getAsBoolean()) {

            /**
             * Stores the flag slug of the 'middle-name-mismatch' flag created.
             */
            String flagSlug = json.get("data").getAsJsonObject().get("slug").getAsString();

            /**
             * Lists all flags for the given userName.
             */
            json = idOSAPIFactory.getFlag().listAll(Config.userName);

            /**
             * Prints api call response to Flags endpoint.
             */
            System.out.println(json.get("data").getAsJsonArray());

            /**
             * Retrieves information about the flag created passing the stored
             * flagSlug as a parameter.
             */
            json = idOSAPIFactory.getFlag().getOne(Config.userName, flagSlug);

            /**
             * Prints api call response to Flags endpoint.
             */
            System.out.println(json.get("data").getAsJsonObject());

            /**
             * Deletes the flag retrieved passing the stored flagSlug as a
             * parameter.
             */
            json = idOSAPIFactory.getFlag().delete(Config.userName, "middle-name-mismatch");

            /**
             * Prints the status of the call response to Flags endpoint.
             */
            System.out.println(json.get("status").getAsBoolean());

        }

        /**
         * To avoid the number of deleted flags equalling 0, firstly creating a
         * new flag, calling the createNew() method passing the name of the flag
         * and the attribute name as a parameter. To create a new flag is
         * necessary to call the create() method passing the userName, the
         * flag's name and the attribute's name as a parameter.
         */
        json = idOSAPIFactory.getFlag().create(Config.userName, "middle-name-mismatch", "middle-name");

        /**
         * Checks if the flag was created before calling other methods related
         * to the flags endpoint (requires an existing flag).
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Deletes all flags related to the userName provided.
             */
            json = idOSAPIFactory.getFlag().deleteAll(Config.userName);

            /**
             * Prints the number of deleted flags retrieved from the call
             * response to Flags endpoint.
             */
            System.out.println(json.get("deleted").getAsInt());

        }
    }
}
