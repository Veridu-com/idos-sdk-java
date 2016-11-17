package com.veridu.idos.test.unit;

import java.util.HashMap;

public abstract class AbstractUnit {

    public HashMap<String, String> getCredentials() {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("credentialPrivateKey", "credentialPrivateKey");
        credentials.put("credentialPublicKey", "credentialPublicKey");
        credentials.put("servicePrivateKey", "servicePrivateKey");
        credentials.put("servicePublicKey", "servicePublicKey");

        return credentials;
    }
}
