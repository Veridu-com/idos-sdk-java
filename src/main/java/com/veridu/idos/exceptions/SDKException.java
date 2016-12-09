package com.veridu.idos.exceptions;

/**
 * Class SDK Exception
 */
public class SDKException extends Exception {

    private static final long serialVersionUID = 1L;

    private String apiMessage;

    private int code;

    private String errorType;

    private String link;

    public SDKException() {
        super("SDK Exception");
    }

    /**
     * Throws SDK Exception with message
     *
     * @param message message
     *            String
     */

    public SDKException(String message) {
        super(message);
    }

    /**
     * Throw SDK exception with all data
     *
     * @param apiMessage error message
     * @param errorType error errorType
     * @param link link to documentation
     * @param code HTTP status code
     */
    public SDKException(String apiMessage, String errorType, String link, int code) {
        super("Message: " + apiMessage + "\n" + "Type: " + errorType + "\n" + "Link: " + link + "\n" + "Status code: "
                + code);
        this.apiMessage = apiMessage;
        this.errorType = errorType;
        this.link = link;
        this.code = code;
    }

    public String getApiMessage() {
        return apiMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getLink() {
        return link;
    }

}