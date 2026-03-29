package com.example;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.RedisClient;

import java.util.*;

public class App {

    static{

    }

    //take input from the user.

    static List<String> taskIds = new ArrayList<>();

    public static void main(String[] args){

        App inputobject = new App();

        RedisService redisObject = new RedisService();

        ThreadStartClass threadstartvalue = new ThreadStartClass();


        while(true){

            //System.out.println("user typed" + inputobject.userInput());
            String finaluserobject = inputobject.userInput();
            if(!inputobject.validateUserInput(finaluserobject))
            {
                System.out.println("WRONG INPUT FORMAT: --> EXPECTED : [ACTION FILEPATH CONTENT]");
            }
            else{
                //take value to redis.
                String id = UUID.randomUUID().toString();
                redisObject.storeToRedis(id, finaluserobject);
                taskIds.add(id);
            }
            for(String xx : taskIds){
                threadstartvalue.threadstartfunction(xx);
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




