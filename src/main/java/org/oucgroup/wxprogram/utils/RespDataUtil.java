package org.oucgroup.wxprogram.utils;

public class RespDataUtil {
    /**
     * 带实体的统一返回
     *
     * @param data 实体
     * @param <T>  实体类型
     * @return
     */
    public static <T> RespData buildSuccess(T data) {
        return new RespData<T>(RespEnum.SUCCESS, data);
    }

    public static RespData buildSuccess() {
        return new RespData(RespEnum.SUCCESS);
    }

    public static RespData buildSuccess(String msg) {
        return new RespData(RespEnum.SUCCESS.getCode(), msg);
    }

    public static RespData buildSuccess(String code, String msg) {
        return new RespData(code, msg);
    }

    public static <T> RespData buildSuccess(String code, String msg, T data) {
        return new RespData<T>(code, msg, data);
    }

    public static RespData buildSuccess(RespEnum RespEnum) {
        return new RespData(RespEnum);
    }

    public static <T> RespData buildError(T data) {
        return new RespData<T>(RespEnum.ERROR, data);
    }

    public static RespData buildError() {
        return new RespData(RespEnum.ERROR);
    }

    public static RespData buildError(String msg) {
        return new RespData(RespEnum.ERROR.getCode(), msg);
    }

    public static RespData buildError(String code, String msg) {
        return new RespData(code, msg);
    }

    public static <T> RespData buildError(String code, String msg, T data) {
        return new RespData<T>(code, msg, data);
    }

    public static RespData buildError(RespEnum RespEnum) {
        return new RespData(RespEnum);
    }
}
