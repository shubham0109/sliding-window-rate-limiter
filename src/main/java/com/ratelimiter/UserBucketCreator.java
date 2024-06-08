package com.ratelimiter;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {

    Map<Integer, SlidingWindowRateLimiter> mp = new HashMap<>();
    
    public UserBucketCreator(int userId){
        mp.put(userId, new SlidingWindowRateLimiter(1, 5));
    }

    public void accessApplication(int userId){
        SlidingWindowRateLimiter slidingWindowRateLimiter = mp.get(userId);

        if (slidingWindowRateLimiter.grantAccess() == true){
            System.out.println("Application accessed!!!");
        }else{
            System.out.println("TOO MANY REQUESTS!!!");
        }
    }

}
