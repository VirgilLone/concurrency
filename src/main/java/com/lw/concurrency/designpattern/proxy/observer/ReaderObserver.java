package com.lw.concurrency.designpattern.proxy.observer;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者：读者粉丝
 */
@RequiredArgsConstructor
public class ReaderObserver implements Observer {
    @NonNull
    private String name;

    private String article;


    @Override
    public void update(Observable o, Object arg) {
        updateArticle(o);
    }

    private void updateArticle(Observable o) {
        JavaStackObservable javaStackObservable = (JavaStackObservable) o;
        this.article = javaStackObservable.getArticle();
        System.out.printf("我是读者%s，文章已更新为：《%s》\n", this.name, this.article);
    }
}
