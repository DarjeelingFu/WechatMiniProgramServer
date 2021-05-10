package org.oucgroup.wxprogram.utils;

import org.oucgroup.wxprogram.utils.RespEnum;

import java.io.Serializable;

public class RespData<T> implements Serializable {

    private String code;

    private String msg;

    private T data;


    public RespData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RespData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespData(RespEnum RespEnum) {
        this.code = RespEnum.getCode();
        this.msg = RespEnum.getMsg();
    }

    public RespData(RespEnum RespEnum, T data) {
        this.code = RespEnum.getCode();
        this.msg = RespEnum.getMsg();
        this.data = data;
    }

    public RespData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
