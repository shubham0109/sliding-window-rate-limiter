package com.ratelimiter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "SLIDING WINDOW RATE LIMITER!!!!!" );

        UserBucketCreator bucket = new UserBucketCreator(1);

        for (int i = 0; i < 12; i++){
            bucket.accessApplication(1);
        }

    }
}
