package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class SSOSamples {
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
         * Lists all available providers for the SSO.
         */
        json = idOSAPIFactory.getSSO().listAll();

        /**
         * Prints api call response to SSO endpoint;
         */
        System.out.println(json.get("data").getAsJsonArray());

        /**
         * Retrieves data from one provider, passing the name of the provider.
         * In this example, twitter.
         */
        json = idOSAPIFactory.getSSO().getOne("twitter");

        /**
         * Prints api call response to SSO endpoint.
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Creates an OAuth2 type sso.
         *
         * Note: You should replace "accessToken" with a valid Facebook access
         * token.
         */
        json = idOSAPIFactory.getSSO().create("facebook", Config.credentialPublicKey, "accessToken");

        /**
         * Prints api response call to SSO endpoint.
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Creates an OAuth1 type SSO, passing the name of the provider, the
         * credential public key, the user access token and the token secret.
         *
         * Note: You should replace "accessToken" and "tokenSecret" with a valid
         * Twitter access token / token secret.
         */
        json = idOSAPIFactory.getSSO().create("twitter", Config.credentialPublicKey, "accessToken", "tokenSecret");

        /**
         * Prints api response call to SSO endpoint
         */
        System.out.println(json.get("data").getAsJsonObject());
    }
}
