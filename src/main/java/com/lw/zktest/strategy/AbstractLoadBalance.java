package com.lw.zktest.strategy;

import java.util.List;

/**
 * Created by WYluo on 2018/11/12.
 */
public abstract class AbstractLoadBalance implements LoadBalance{

    @Override
    public String selectHost(List<String> serviceRepos) {
        if(serviceRepos==null||serviceRepos.size()==0){
            return null;
        }
        if(serviceRepos.size()==1){
            return serviceRepos.get(0);
        }
        return doSelect(serviceRepos);
    }

    protected abstract String doSelect(List<String> serviceRepos);

}
