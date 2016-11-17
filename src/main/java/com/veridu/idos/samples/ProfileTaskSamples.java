package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileTaskSamples {
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
         * To start making requests to the Tasks endpoint, it is necessary to
         * provide the process id, and the tasks is going to be related related
         * to this process id. Therefore, in this sample, it lists all processes
         * related to the provided userName.
         */
        json = idOSAPIFactory.getProcess().listAll(Config.userName);

        /**
         * Stores the process id of the first index of the api call response.
         */
        int processId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();

        /**
         * Creates a new task. To create a new task, it is necessary to call the
         * create() method passing the userName, the task name, the task event,
         * the boolean value for running status, the boolean value for the
         * success status and the message as a parameter. Note: The message
         * parameter and the boolean value for the success status are optional.
         */
        json = idOSAPIFactory.getTask().create(Config.userName, processId, "Testing", "testing", true, false,
                "message");

        /**
         * Checks if the task was created before calling other methods related
         * to the tasks endpoint (requires an existing task).
         */
        if (json.get("status").getAsBoolean() == true) {
            /**
             * Stores the task id of the task created.
             */
            int taskId = json.get("data").getAsJsonObject().get("id").getAsInt();

            /**
             * Updates the task created passing the stored taskId, task name,
             * the task event, the boolean value for running status, the boolean
             * value for the success status and the message(optional) as a
             * parameter.
             */
            json = idOSAPIFactory.getTask().update(Config.userName, processId, taskId, true, true);

            /**
             * Prints api call response to Tasks endpoint.
             */
            System.out.println(json.get("data").getAsJsonObject());

            /**
             * Retrieves information of the task updated related to the the
             * stored taskId.
             */
            json = idOSAPIFactory.getTask().getOne(Config.userName, processId, taskId);

            /**
             * Prints api call response to Tasks endpoint.
             */
            System.out.println(json.get("data").getAsJsonObject());

            /**
             * Lists all tasks related to the provided userName and processId.
             */
            json = idOSAPIFactory.getTask().listAll(Config.userName, processId);

            /**
             * Prints api call response to Tasks endpoint.
             */
            for (int i = 0; i < json.get("data").getAsJsonArray().size(); i++) {
                System.out.println(json.get("data").getAsJsonArray().get(i).getAsJsonObject());
            }
        }
    }
}
