package edu.byohttp.response;

public enum ResponseStatus {
    OK(200,"OK"),
    NOT_IMPLEMENTED(501,"Not Implemented"),
    NOT_FOUND(404,"Not Found"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error");

    private final String message;
    private final int code;

    ResponseStatus(final int code,final String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.code + " " + this.message;
    }
}
