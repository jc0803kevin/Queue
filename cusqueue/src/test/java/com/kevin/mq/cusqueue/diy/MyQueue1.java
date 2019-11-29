package com.kevin.mq.cusqueue.diy;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/11/28 17:41
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue1 {
    //创建一个装队列的容器
    private LinkedList<Object> list = new LinkedList<>();
    //创建一个计数器
    private AtomicInteger count = new AtomicInteger(0);
    //创建容器的最大值和最小值
    private final int minSize = 0;

    private final int maxSize;

    public MyQueue1(int size) {
        this.maxSize = size;
    }

    private final Object lock = new Object();

    public void put(Object obj) {
        synchronized (lock) {
            if (count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //把对象加入容器
            list.add(obj);
            //计算容器的数值
            count.incrementAndGet();
            //唤醒等待的线程
            lock.notify();
            System.out.println("新加入的元素为:" + obj);
        }
    }

    public Object take() {
        Object ret = null;
        synchronized (lock) {
            if (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ret = list.removeFirst();
            count.decrementAndGet();
            lock.notify();
        }

        return ret;
    }


    public int Size() {
        return this.count.get();
    }

    public static void main(String[] args) {
        final MyQueue1 mq = new MyQueue1(5);
        mq.put(1);
        mq.put(2);
        mq.put(3);
        mq.put(4);
        mq.put(5);

        System.out.println("当前容器的长度:" + mq.Size());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mq.put(6);
                mq.put(7);
            }
        }, "t1");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = mq.take();
                System.out.println("移除的元素为:" + o1);
                Object o2 = mq.take();
                System.out.println("移除的元素为:" + o2);
            }
        }, "t2");
        t2.start();

    }
}
