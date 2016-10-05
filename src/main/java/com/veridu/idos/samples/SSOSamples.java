package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class SSOSamples {
    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         *
         * @see https://github.com/google/gson
         */
        JsonObject json = null;

        /**
         * IdOSAPIFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         *
         * @param companyPrivateKey
         *            The company public key that authorizes requests to the API
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        json = idOSAPIFactory.getSSO().listAll();

        /**
         * Prints the api response
         */
        System.out.println(json);

        /**
         * Get the response form the API geting one company
         */
        json = idOSAPIFactory.getSSO().getOne("facebook");

        /**
         * Prints the getOne response from the api
         */
        System.out.println(json.get("data").getAsBoolean());
    }
}
