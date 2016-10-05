package com.veridu.idos.exceptions;

/**
 * Class EmptyPrivateKey
 * 
 * @version 2.0
 */
public class EmptyPrivateKey extends SDKException {

    private static final long serialVersionUID = 1L;

    public EmptyPrivateKey() {
        super("Empty Private Key! Please, give a valid private key, passing trough the CredentialFactory constructor");
    }

    /**
     * Throws EmptyPrivateKey Exception with message
     *
     * @param msg
     *            String
     */
    public EmptyPrivateKey(String msg) {
        super(msg);
    }
}
