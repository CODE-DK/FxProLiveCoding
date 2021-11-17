package com.solution.main;

public interface RingBuffer<T> {

    void put(T o) throws Exception;

    T get() throws Exception;
}
