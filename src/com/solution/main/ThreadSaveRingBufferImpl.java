package com.solution.main;

public class ThreadSaveRingBufferImpl<T> implements RingBuffer<T> {

    private final Object monitor = new Object();
    private final Object[] data;

    private int read = 0;
    private int write = 0;
    private int capacity = 0;

    public ThreadSaveRingBufferImpl(int maxBufferSize) {
        data = new Object[maxBufferSize];
    }

    @Override
    public void put(T o) throws InterruptedException {
        synchronized (monitor) {
            while (capacity == data.length) monitor.wait();

            data[write] = o;
            capacity++;
            write++;

            if (write == data.length) write = 0;
            monitor.notifyAll();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() throws InterruptedException {
        synchronized (monitor) {
            while (capacity == 0) monitor.wait();

            Object result = data[read];
            capacity--;
            read++;

            if (read == data.length) read = 0;
            monitor.notifyAll();

            return (T) result;
        }
    }
}
