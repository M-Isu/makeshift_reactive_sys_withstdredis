package com.example;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.file.*;

public class threadcreationfile{



    public void assignTask(String id){
        //get access to values in redis.

        RedisService myredisservice = new RedisService();

        //get action based on id.
        String actionItem = myredisservice.getToRedis(id);

        boolean final_value = checkNonworkedOnRequest(actionItem);
        System.out.println("THIS IS THE STATUS OF THE TEXT IN REDIS " + final_value);
        if (!final_value) {
            List<String> anotherListValue = Arrays.asList(actionItem.split(" "));

            List<String> outputoperation = anotherListValue.get(0).equals("read") ? readfileJava(anotherListValue.get(1), anotherListValue.get(2))
                    : writefileJava(anotherListValue.get(1), anotherListValue.get(2));

            for(String xx : outputoperation){
                System.out.println(xx);
            }

            if(outputoperation.get(1).equals("read")){
                if(outputoperation.get(0).equals("2")){
                    //action failed
                    String failed_action = myredisservice.getToRedis(id);

                    String new_failed_action = failed_action + " FAILED";
                    myredisservice.storeToRedis(id, new_failed_action);
                    System.out.println("TASK COMPLETED FAILED");
                }
                else if(outputoperation.get(0).equals("1")){
                    String failed_action = myredisservice.getToRedis(id);

                    String new_failed_action = failed_action + " SUCCESS";
                    myredisservice.storeToRedis(id, new_failed_action);
                    System.out.println("TASK COMPLETED SUCCESSFULLY");
                }
            }
            else if(outputoperation.get(1).equals("write")){
                if(outputoperation.get(0).equals("2")){
                    //action failed
                    String failed_action = myredisservice.getToRedis(id);

                    String new_failed_action = failed_action + " FAILED";
                    myredisservice.storeToRedis(id, new_failed_action);
                    System.out.println("TASK COMPLETED FAILED");
                }
                else if(outputoperation.get(0).equals("1")){
                    String failed_action = myredisservice.getToRedis(id);

                    String new_failed_action = failed_action + " SUCCESS";
                    myredisservice.storeToRedis(id, new_failed_action);
                    System.out.println("TASK COMPLETED SUCCESSFULLY");
                }

            }

        } else {
            System.out.println("TASK ALREADY COMPLETED SUCCESSFULLY OR FAILED");
        }
    }





    public boolean checkNonworkedOnRequest(String action){

        List<String> finalaction = Arrays.asList(action.split(" "));

        for(String xx: finalaction){
            System.out.println(xx);
        }
        return true;
    }

    static List<String> readfileJava(String filepath, String content){

        Path filePath = Paths.get(filepath);


        if (!Files.exists(filePath)) {
            System.err.println("Error: File does not exist -> " + filePath.toAbsolutePath());
            return Arrays.asList("2", "read");

        }

        try {
            String contentRead = Files.readString(filePath, StandardCharsets.UTF_8);
            System.out.println("\n=== File Content (readString) ===");
            System.out.println(contentRead);
            return Arrays.asList("1", "read");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return Arrays.asList("2", "read");
        }




    }

    static List<String> writefileJava(String filepath, String content){

        Path path = Path.of(filepath);

        try{
            if(path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
                Files.write(
                        path,
                        content.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING
                );

                System.out.println("Content successfully written to: " + path.toAbsolutePath());
                return Arrays.asList("1", "write");

            }catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                e.printStackTrace();

                return Arrays.asList("2", "write");
            }

    }

        static void checkFilePath(String filepath) throws Exception{
                    Paths.get(filepath);
        }

}