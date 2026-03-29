package com.example;

import redis.clients.jedis.Jedis;

public class RedisService {


    Jedis jedisobject = new Jedis("localhost", 6379);

    public String storeToRedis(String key, String value){
        System.out.println("======STORED VALUE IN QUEUE=======");
        return jedisobject.set(key, value);
    }

    public String getToRedis(String key){
        return jedisobject.get(key);
    }
}
