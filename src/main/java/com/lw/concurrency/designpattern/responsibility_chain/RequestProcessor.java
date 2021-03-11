package com.lw.concurrency.designpattern.responsibility_chain;


public interface RequestProcessor {

    void processRequest(Request request);

    void shutdown();
}
