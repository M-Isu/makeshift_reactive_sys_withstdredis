package com.example;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.RedisClient;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App{

    RedisClient jedis = new RedisClient.Builder()
            .fromURI("redis:://someurl goes here")
            .build();

public static void main(String[] args) throws Exception{

    while(true){
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in i/o operation:  Format (operation_content),(operation_destination),(operation): " );
        String input = scan.nextLine();
        List<String> list_instructions = Arrays.asList(input.split(","));

        String first = list_instructions.get(0);
        String second = list_instructions.get(1);
        String third = list_instructions.get(2);

        //check for null values in user inputted values.



        App myapp = new App();
        myapp.inputTake(first, second, third);

    }
}

public void inputTake(String x, String file, String operation) throws Exception{
    //this would support file read and maybe network calls --> basically these are going to be the i/o task
    if(operation.equals("network")){
        System.out.println("network calls not available yet");
    }
    else if(operation.equals("file")){
        String result = jedis.set("COMMANDIDX123", x + "," + file + "," + operation);
        System.out.println(result);
    }
	else{
        throw new Exception("operation not found within system");
    }
}
}



