package com.lw.zktest.service;

import java.io.IOException;

/**
 * Created by WYluo on 2018/11/12.
 */
public class register2 {

    public static void main(String[] args) throws IOException {
        ServiceRegistry serviceRegistry=new ServiceRegisterImpl();
        serviceRegistry.register("product-service","192.168.1.105:20880");

        System.in.read();//进程阻塞
    }

}
