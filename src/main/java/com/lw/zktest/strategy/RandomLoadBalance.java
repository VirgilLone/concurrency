package com.lw.zktest.strategy;

import java.util.List;
import java.util.Random;

/**
 * Created by WYluo on 2018/11/12.
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> serviceRepos) {
        int len=serviceRepos.size();
        Random random=new Random();
        return serviceRepos.get(random.nextInt(len));
    }
}
