/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.Serializable;

/**
 * Header of request
 * 
 * context: context of message (ex: if encrypted)
 * requestId: id of request
 * responseExpected: true or false
 * objectKey: id of object
 * operation: method to be invoked
 * @author Demis 
 */
public class RequestHeader implements Serializable{
    private String context;
    private int requestId;
    private boolean responseExpected;
    private int objectKey;
    private String operation;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public boolean isResponseExpected() {
        return responseExpected;
    }

    public void setResponseExpected(boolean responseExpected) {
        this.responseExpected = responseExpected;
    }

    public int getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(int objectKey) {
        this.objectKey = objectKey;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public RequestHeader(String context, int requestId, boolean responseExpected, int objectKey, String operation) {
        this.context = context;
        this.requestId = requestId;
        this.responseExpected = responseExpected;
        this.objectKey = objectKey;
        this.operation = operation;
    }
    
    
}
