package com.navjot.Concurrency.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;


@Component("ReentrantLockCounter")
public class ReentrantLockCounter implements Counter{
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment(){
        lock.lock();
        try{
          count++;
        }finally {
            lock.unlock();
        }

    }


    public int getCount(){
        return count;
    }


}
