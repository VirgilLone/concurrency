package com.lw.concurrency.designpattern.responsibility_chain;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


public class FinalRequestProcessor extends Thread implements RequestProcessor{
    //存储请求数据
    private BlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    private volatile boolean finished=false;


    @Override
    public void run() {
        while(!finished||!Thread.currentThread().isInterrupted()){
            try {
                Request request=requests.take();
                System.out.println("Final Processor:"+request);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }

    @Override
    public void shutdown() {
        System.out.println("Finish Shutdown ");
        finished=true;
        requests.clear();
    }
}
