package com.veridu.idos.test.functional;

import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.settings.Config;

public class MainTestSetup {

    protected static IdOSAPIFactory factory;
    protected static HashMap<String, String> credentials = new HashMap<>();
    protected static String userName;
    protected JsonObject response = new JsonObject();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        credentials.put("credentialPrivateKey", Config.credentialPrivateKey);
        credentials.put("credentialPublicKey", Config.credentialPublicKey);
        credentials.put("servicePrivateKey", Config.servicePrivateKey);
        credentials.put("servicePublicKey", Config.servicePublicKey);
        credentials.put("username", Config.userName);

        factory = new IdOSAPIFactory(getCredentials());
        userName = getCredentials().get("username");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    protected static boolean isResponseOk(JsonObject response) {
        return response.get("status").getAsBoolean();
    }

    protected JsonObject getResponseData(JsonObject response) {
        return response.get("data").getAsJsonObject();
    }

    protected static HashMap<String, String> getCredentials() {
        return credentials;
    }
}
