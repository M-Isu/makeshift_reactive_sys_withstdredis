package com.example;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.RedisClient;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class App {

    //take input from the user.

    public static void main(String[] args){

        App inputobject = new App();

        RedisService redisObject = new RedisService();

        while(true){

            //System.out.println("user typed" + inputobject.userInput());
            String finaluserobject = inputobject.userInput();
            if(!inputobject.validateUserInput(finaluserobject))
            {
                System.out.println("WRONG INPUT FORMAT: --> EXPECTED : [ACTION FILEPATH CONTENT]");
            }
            else{
                //take value to redis.
                redisObject.storeToRedis(UUID.randomUUID().toString(), finaluserobject);
            }

        }
    }

    //take user input and return
    public String userInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Boolean validateUserInput(String input){

        //validate input from input stream.
        int mistake_counter = 0;
        String allowedKeywords  = "write read";

        //[action, filepath , content]
        //for read the content parameter will  be ignored.
        //eg [read, c://myfile.txt]
        //eg [write, c://anotherfile.txt]

        List<String> stringlist = Arrays.asList(input.split(" "));
        if(stringlist.size() > 3){
            mistake_counter++;
        }

        if(!allowedKeywords.contains(stringlist.get(0))){
            mistake_counter++;
        }


        if(mistake_counter == 0){
            return true;
        }
        else{
            return false;
        }




    }

}




