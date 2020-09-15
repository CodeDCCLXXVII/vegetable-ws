package com.ws.vegetablews.services;

/**
 * @author dcclxxvii
 */
public class RequestResponse {

    private String code;
    private String message;
    private Object metadata;

    public RequestResponse(){}
    public RequestResponse(String code, String message){
        this.code = code;
        this.message = message;
    }

    public RequestResponse(String code, Object metadata) {
        this.code = code;
        this.metadata = metadata;
    }

    public RequestResponse(String code, String message, Object metadata){
        this.code = code;
        this.message = message;
        this.metadata = metadata;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
