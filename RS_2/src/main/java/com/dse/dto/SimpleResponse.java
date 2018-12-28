package com.dse.dto;

public class SimpleResponse {

    private Object obj;

    public SimpleResponse(Object obj) {
        this.obj = obj;
    }

    public SimpleResponse() {
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
