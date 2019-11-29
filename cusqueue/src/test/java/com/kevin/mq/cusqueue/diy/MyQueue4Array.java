package com.kevin.mq.cusqueue.diy;

/**
 * @Author kevin @Description @Date Created on 2019/11/28 17:53
 */
public class MyQueue4Array {

    // 数组中能存放数据的最大容量
    private static final int MAX_CAPACITY = 1 << 30;
    /**
     * https://msd.misuland.com/pd/9786112860861905 队列管道，
     *
     * <p>当管道中存放的数据大于队列的长度时将不会再offer数据，直至从队列中poll数据后
     */
    private Object[] queue;

    // 队列的头部，获取数据时总是从头部获取
    private int head;
    // 队列尾部，push数据时总是从尾部添加
    private int tail;
    // 队列长度

    private int size;
    // 数组长度
    private int capacity;
    // 最大下标
    private int maxIndex;

    public MyQueue4Array(int initialCapacity) {
        if (initialCapacity > MAX_CAPACITY) throw new OutOfMemoryError("initialCapacity too large");
        if (initialCapacity <= 0)
            throw new IndexOutOfBoundsException("initialCapacity must be more than zero");
        queue = new Object[initialCapacity];
        capacity = initialCapacity;
        maxIndex = initialCapacity - 1;
        head = tail = -1;
        size = 0;
    }

    public MyQueue4Array() {
        queue = new Object[16];
        capacity = 16;
        head = tail = -1;
        size = 0;
        maxIndex = 15;
    }

    public static void main(String[] args) {
        MyQueue4Array myQueue4Array = new MyQueue4Array(16);
        myQueue4Array.offer("kevin1");
        myQueue4Array.offer("kevin2");

        System.out.println(myQueue4Array.size());
        //System.out.println("查看第一个数据 1 ->" + myQueue4Array.peek() + "");
        System.out.println("返回队列的第一个数据->" + myQueue4Array.poll() + "");
        //System.out.println("查看第一个数据 2 ->" + myQueue4Array.peek() + "");
    }

    /**
     * 往队列尾部添加数据
     *
     * @param object 数据
     */
    public void offer(Object object) {
        if (size >= capacity) {
            System.out.println("queue's size more than or equal to array's capacity");
            return;
        }
        if (++tail > maxIndex) {
            tail = 0;
        }
        queue[tail] = object;
        size++;
    }

    /**
     * 从队列头部拉出数据
     *
     * @return 返回队列的第一个数据
     */
    public Object poll() {
        if (size <= 0) {
            System.out.println("the queue is null");
            return null;
        }
        if (++head > maxIndex) {
            head = 0;
        }
        size--;
        Object old = queue[head];
        queue[head] = null;
        return old;
    }

    /**
     * 查看第一个数据
     *
     * @return
     */
    public Object peek() {
        return queue[head];
    }

    /**
     * 队列中存储的数据量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空队列
     */
    public void clear() {
        for (int i = 0; i < queue.length; i++) {
            queue[i] = null;
        }
        tail = head = -1;
        size = 0;
    }

    @Override
    public String toString() {
        if (size <= 0) return "{}";
        StringBuilder builder = new StringBuilder(size + 8);
        builder.append("{");
        int h = head;
        int count = 0;
        while (count < size) {
            if (++h > maxIndex) h = 0;
            builder.append(queue[h]);
            builder.append(", ");
            count++;
        }
        return builder.substring(0, builder.length() - 2) + "}";
    }
}
