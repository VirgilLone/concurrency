package com.lw.concurrency.designpattern.responsibility_chain;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


public class SaveProcessor extends Thread implements RequestProcessor {
    private RequestProcessor nextProcessor;

    //存储请求数据
    private BlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    private volatile boolean finished=false;

    SaveProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
         while(!finished){
             try {
                 Request request=requests.take();

                 nextProcessor.processRequest(request);

                 System.out.println("保存请求："+request);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }
    @Override
    public void processRequest(Request request) {
        requests.add(request); //添加到阻塞队列
    }

    @Override
    public void shutdown() {
        System.out.println("SaveProcessor begin shutdown");
        finished=true;
        requests.clear();
        nextProcessor.shutdown();
    }
}
