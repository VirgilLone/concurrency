package com.lw.zktest.service;

/**
 * Created by WYluo on 2018/11/12.
 */
public interface ServiceRegistry {

    /**
     * 把服务注册到zk上
     * @param serviceName
     * @param seviceAdd
     */
    void register(String serviceName, String seviceAdd);

}
