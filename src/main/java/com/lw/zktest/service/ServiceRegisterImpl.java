package com.lw.zktest.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;

/** dubbo核心 简单实现
 * Created by WYluo on 2018/11/12.
 */
public class ServiceRegisterImpl implements ServiceRegistry {

    private CuratorFramework curator=null;//zk api

    private static final String REGISTER_ROOT="/register";//namespace

    {
        curator= CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,0)).build();
        curator.start();
    }


    @Override
    public void register(String serviceName, String seviceAdd) {
        String servicePath=REGISTER_ROOT+"/"+serviceName;

        try {
            if(curator.checkExists().forPath(servicePath)==null){
                curator.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath,"0".getBytes());
            }
            //创建协议地址
            String addressPath=servicePath+"/"+seviceAdd;
            curator.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath);
            System.out.println("节点注册成功："+addressPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServiceRegistry serviceRegistry=new ServiceRegisterImpl();
        serviceRegistry.register("product-service","192.168.1.104:20880");

        System.in.read();//进程阻塞
    }

}
