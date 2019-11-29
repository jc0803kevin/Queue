package com.kevin.mq.cusqueue.diy;

/**
 * @Author kevin
 * @Description
 *  <p>LinkedList</p>
 *      <p>存数据,生产者往该集合存数据,存数据的时候上锁,存完释放锁</p>
 *      <p>取数据,消费都拿到锁以后,循环取出LinkedList所有数据,取完数据以后,等待新数据</p>
 * @Date Created on 2019/11/28 17:24
 */
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Started {

    public final static Object obj = new Object();
    public final static LinkedList<Object> list = new LinkedList<>();

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String nextLine = scanner.nextLine();
                System.out.println("生产者发送消息->"+ nextLine);
                synchronized (obj) {
                    list.add(nextLine);
                    obj.notify();
                }
            }
        }).start();

        new Thread(new CustomerTask()).start();

    }
}

class CustomerTask implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Object removeFirst = Started.list.removeFirst();
                System.err.println("消费消息:" + removeFirst);
            } catch (NoSuchElementException e) {
                synchronized (Started.obj) {
                    try {
                        Started.obj.wait();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}

