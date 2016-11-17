package com.veridu.idos.endpoints.unit;

import com.veridu.idos.exceptions.InvalidToken;

import java.util.HashMap;

abstract class AbstractUnit {

    public HashMap<String, String> getCredentials() throws InvalidToken {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("credentialPrivateKey", "credentialPrivateKey");
        credentials.put("credentialPublicKey", "credentialPublicKey");
        credentials.put("servicePrivateKey", "servicePrivateKey");
        credentials.put("servicePublicKey", "servicePublicKey");

        return credentials;
    }
}
