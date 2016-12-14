package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileGateSamples {

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
         * Creates or updates a gate. The upsertOne method checks if the gate
         * already exists on the database, if so, it updates it. Otherwise, it
         * creates a new gate. To create or update a gate it is necessary to
         * call the method upsert() passing the userName, the gate's name and
         * the boolean pass value as a parameter.
         */
        json = idOSAPIFactory.getGate().upsert(Config.userName, "18+", "medium");

        /**
         * Checks if the gate was created before calling other methods related
         * to the gates (requires an existing gate).
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Stores the gate slug of the slug created
             */
            String gateSlug = json.get("data").getAsJsonObject().get("slug").getAsString();

            /**
             * Lists all gates
             */
            json = idOSAPIFactory.getGate().listAll(Config.userName);

            /**
             * Prints api call response to Gates Endpoint
             */
            for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
                System.out.println(json.get("data").getAsJsonArray().get(i).getAsJsonObject());
            }

            /**
             * Deletes the gate created/updated passing the userName and the
             * gate slug as a parameter.
             */
            json = idOSAPIFactory.getGate().delete(Config.userName, gateSlug);

            /**
             * Prints status of the api call response to Gates Endpoint
             */
            System.out.println(json.get("status").getAsBoolean());
        }

        /**
         * Creates a new gate. To create a gate is necessary to call the method
         * create() passing the userName, the gate's name and the boolean pass
         * value as a parameter.
         * 
         */
        json = idOSAPIFactory.getGate().create(Config.userName, "Gate Name", "high");

        /**
         * Checks if the gate was created before calling other methods related
         * to the gates (requires an existing gate).
         */
        if (json.get("status").getAsBoolean()) {

            /**
             * Retrieves information on the gate related to the slug that came
             * on api call response to Gates Endpoint.
             */
            json = idOSAPIFactory.getGate().getOne(Config.userName,
                    json.get("data").getAsJsonObject().get("slug").getAsString());
            /**
             * Prints the api call response to gates Endpoint
             */
            System.out.println(json.get("data"));
        }

        /**
         * Deletes all profile gates related to the given userName
         */
        json = idOSAPIFactory.getGate().deleteAll(Config.userName);

        /**
         * Prints the number of deleted gates, information received from the api
         * call response to Gates endpoint
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
