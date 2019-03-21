package com.team.test;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 12:51 2019-03-03
 */
public class CountDownLatchTest {

    public static int threadNum = 100;

    public static CountDownLatch cdl = new CountDownLatch(threadNum);

    public static Vector<Thread> vector = new Vector<>();

    public static void main(String[] args) throws InterruptedException {

        long start = System.nanoTime();
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());

                }
            });

            vector.add(thread);
            thread.start();
            cdl.countDown();


        }


        for (Thread thread : vector) {
            thread.join();
        }
        System.out.println((System.nanoTime()-start)/1000000);
    }

}
