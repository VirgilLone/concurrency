package com.lw.concurrency.AopTest;

import lombok.Data;

/**
 * Created by WYluo on 2018/11/17.
 */
@Data
public class ServerResponse<T> {
    private String errorCode;

    private String message;

    private T data;

    public ServerResponse(String errorCode, String message, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public static <T> ServerResponse<T> success(String message, T data){
        return new ServerResponse<T>("1",message,data);
    }
    public static <T> ServerResponse<T> fail(String code,String message){
        return new ServerResponse<T>(code,message,null);
    }



}
