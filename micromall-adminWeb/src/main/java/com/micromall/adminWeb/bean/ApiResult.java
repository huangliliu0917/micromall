package com.micromall.adminWeb.bean;

/**
 * Created by Administrator on 2015/6/24.
 */
public class ApiResult<T> {
    private int resultCode;
    private String msg;
    private T responseData;

    public ApiResult(int resultCode, String msg, T responseData) {
        this.resultCode = resultCode;
        this.msg = msg;
        this.responseData = responseData;
    }

    public ApiResult() {
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }
}
