package com.lw.concurrency.designpattern.responsibility_chain;


public class ChainServer {

    private PrintProcessor firstProcessor;

    private void setupRequestProcessor(){
        //构建一个链路
        //Print->Save->Final
        FinalRequestProcessor finalProcessor=new FinalRequestProcessor();
        SaveProcessor saveProcessor=new SaveProcessor(finalProcessor);
        firstProcessor=new PrintProcessor(saveProcessor);
        //分别启动三个线程
        finalProcessor.start();
        saveProcessor.start();
        firstProcessor.start();
    }
    private void startup(){
        setupRequestProcessor();
    }
    public void shutdown(){
        firstProcessor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        ChainServer chainServer=new ChainServer();
        chainServer.startup();

        Request request=new Request("请求xx");// 模拟请求
        chainServer.firstProcessor.processRequest(request);

        Thread.sleep(5000);

        chainServer.shutdown();
    }
}
