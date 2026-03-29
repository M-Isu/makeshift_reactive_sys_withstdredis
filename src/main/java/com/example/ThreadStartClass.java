package com.example;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadStartClass {

    ExecutorService taskthreads = Executors.newFixedThreadPool(2);

    public void threadstartfunction(String id){

        Callable<String> calltask = () -> {

            threadcreationfile mythreadcreationfile = new threadcreationfile();
            mythreadcreationfile.assignTask(id);
          return "task done";
        };

        taskthreads.submit(calltask);

    }
}
