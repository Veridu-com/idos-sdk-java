package com.veridu.idos.exceptions;

/**
 * Class SDK Exception
 */
public class SDKException extends Exception {

    private static final long serialVersionUID = 1L;

    public SDKException() {
        super("SDK Exception");
    }

    /**
     * Throws SDK Exception with message
     *
     * @param msg
     *            String
     */

    public SDKException(String message) {
        super(message);
    }

    public SDKException(String message, String type, String link) {
        super("Message: " + message + "\n" + "Type: " + type + "\n" + "Link: " + link);
    }
}