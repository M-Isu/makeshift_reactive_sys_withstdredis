package com.example;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadcreationfile {


    public void threadcreation(Runnable action){

    //this should be started as soon as the application starters, to make threads ready for tasks

        ExecutorService exe = Executors.newFixedThreadPool(3);

        //take a look at the redis instance ,and find id of type command.
        //take a look at the operation check to see whether the operation is writing to a file
        //if that is so, take the contents from redis,

        //make thread non-blocking / basically after making a syscall to write value to a file,
        //it should go back to queue to look for more events....
        //the important part about this is to make the THREADS NON BLOCKING...I will be using the
        //java nio version 2 for async sys calls... im tired right now will continue tomorrow
}

public void threadcreation(){

        Runnable runnable = () -> {};
        ExecutorService exe = Executors.newFixedThreadPool(3);
        exe.submit(runnable);


    }
}
