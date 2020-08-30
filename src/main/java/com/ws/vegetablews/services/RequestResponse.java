package com.ws.vegetablews.services;

/**
 * @author dcclxxvii
 */
public class RequestResponse {

    private int code;
    private String message;
    private Object metadata;

    public RequestResponse(){};
    public RequestResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    public RequestResponse(int code, Object metadata) {
        this.code = code;
        this.metadata = metadata;
    }

    public RequestResponse(int code, String message, Object metadata){
        this.code = code;
        this.message = message;
        this.metadata = metadata;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "RequestResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
