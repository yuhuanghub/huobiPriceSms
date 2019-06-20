package com.yu.huobi.util;

/**
 * @author Yu
 * @create 2018/8/19
 * @since 1.0.0
 */
public class Result {

    private boolean success;

    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}