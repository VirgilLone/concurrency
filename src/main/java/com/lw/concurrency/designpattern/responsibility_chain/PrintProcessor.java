package com.lw.concurrency.designpattern.responsibility_chain;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


public class PrintProcessor extends Thread implements RequestProcessor{

    private RequestProcessor nextProcessor;

    //存储请求数据
    private BlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    private volatile boolean finished=false;

    PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while(!finished||!Thread.currentThread().isInterrupted()){
            try {
                Request request=requests.take(); // 阻塞式的获取请求

                nextProcessor.processRequest(request); // 传递给下一个处理器

                System.out.println("拿到请求： "+request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request); // 请求丢到队列，生产消息
    }

    @Override
    public void shutdown() {
        System.out.println("PrintProcessor begin shutdown");
        finished=true;
        requests.clear();
        nextProcessor.shutdown();
    }
}
