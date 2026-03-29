package com.example;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadcreationfile{


    ExecutorService taskthreads = Executors.newFixedThreadPool(2);


    public void assignTask(){
        //get access to values in redis.

        RedisService myredisservice = new RedisService();


    }

}