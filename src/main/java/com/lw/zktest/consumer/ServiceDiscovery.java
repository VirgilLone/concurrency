package com.lw.zktest.consumer;

import com.google.common.collect.Lists;
import com.lw.zktest.strategy.LoadBalance;
import com.lw.zktest.strategy.RandomLoadBalance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * Created by WYluo on 2018/11/12.
 */
public class ServiceDiscovery {

    List<String> serviceRepos= Lists.newArrayList();

    private CuratorFramework curator=null;//zk api

    private static final String REGISTER_ROOT="/register";//namespace

    LoadBalance loadBalance;

    {
        curator= CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,0)).build();
        curator.start();

        loadBalance=new RandomLoadBalance();
    }

    public void init(String serviceName) throws Exception {
        String path=REGISTER_ROOT+"/"+serviceName;
        try {
            serviceRepos = curator.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        registerWatcher(path);
    }

    /**
     * 指定节点的监听 订阅服务节点的通知
     * @param path
     */
    private void registerWatcher(String path) throws Exception {
        PathChildrenCache pathChildrenCache=new PathChildrenCache(curator,path,true);
        PathChildrenCacheListener pathChildrenCacheListener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                //此时收到一个事件 说明服务地址发生变化
                serviceRepos=curator.getChildren().forPath(path);
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }



    public static void main(String[] args) throws Exception {
        ServiceDiscovery serviceDiscovery=new ServiceDiscovery();
        serviceDiscovery.init("product-service");

        for(int i=0;i<20;i++){
            System.out.println(serviceDiscovery.getServiceEndPoint());
            Thread.sleep(4000);
        }

    }

    public String getServiceEndPoint(){
        return loadBalance.selectHost(serviceRepos);
    }

}
