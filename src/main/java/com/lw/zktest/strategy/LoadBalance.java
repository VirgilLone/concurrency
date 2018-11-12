package com.lw.zktest.strategy;

import java.util.List;

/**
 * Created by WYluo on 2018/11/12.
 */
public interface LoadBalance {

    String selectHost(List<String> serviceRepos);
}
