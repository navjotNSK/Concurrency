package com.navjot.Concurrency.util;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier
@Component("SynchronisedCounter")
public class SynchronisedCounter implements Counter{

    private int count = 0;

    public void increment(){
        synchronized (SynchronisedCounter.class){
            count++;
        }

    }

   public int getCount(){
        return count;
   }




}
