package com.solution.main;

public class RingBufferImpl<T> implements RingBuffer<T> {

    private final Object[] data;

    private int read = 0;
    private int write = 0;
    private int capacity = 0;

    public RingBufferImpl(int maxBufferSize) {
        data = new Object[maxBufferSize];
    }

    @Override
    public void put(T o) throws IllegalStateException {
        if (capacity == data.length) {
            throw new IllegalStateException("Buffer full");
        } else {
            data[write] = o;
            capacity++;
            write++;

            if (write == data.length) write = 0;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() throws IllegalStateException{
        if (capacity == 0) {
            throw new IllegalStateException("Empty buffer");
        } else {
            Object result = data[read];
            capacity--;
            read++;

            if (read == data.length) read = 0;
            return (T) result;
        }
    }
}