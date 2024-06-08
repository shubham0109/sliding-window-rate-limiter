package com.ratelimiter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter{


    Queue<Long> slidingWindowQueue;
    int timeWindowInSeconds;
    int bucketCapacity;

    public SlidingWindowRateLimiter(int timeWindowInSeconds, int bucketCapacity){
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
        this.slidingWindowQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean grantAccess(){
        long currentTime = System.currentTimeMillis();

        checkAndUpdateSlidingWindow(currentTime);
        if (slidingWindowQueue.size() < bucketCapacity){
            slidingWindowQueue.offer(currentTime);
            return true;
        }
        return false;
    }

    public void checkAndUpdateSlidingWindow(long currentTime){

        if (slidingWindowQueue.isEmpty() == true)return;

        long cutoffTime = currentTime - timeWindowInSeconds*1000;

        while (slidingWindowQueue.peek() < cutoffTime){
            slidingWindowQueue.poll();
            if (slidingWindowQueue.isEmpty() == true)break;
        }
    }

}
