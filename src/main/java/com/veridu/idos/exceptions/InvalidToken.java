package com.veridu.idos.exceptions;

public class InvalidToken extends SDKException {

    private static final long serialVersionUID = 1L;

    public InvalidToken() {
        super("InvalidToken! Please give valid credentials keys passing them to the constructor");
    }

    /**
     * Throws EmptyToken Exception with message
     *
     * @param msg
     *            String
     */
    public InvalidToken(String msg) {
        super(msg);
    }
}
