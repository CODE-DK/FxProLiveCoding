package com.solution.test;

import com.solution.main.RingBuffer;
import com.solution.main.ThreadSaveRingBufferImpl;
import org.junit.Assert;
import org.junit.Test;

public class ThreadSaveRingBufferImplTest {

    @Test
    public void whenPutElement_ThenExpectTheSameElement() throws Exception {
        Object element = new Object();

        RingBuffer<Object> buffer = new ThreadSaveRingBufferImpl<>(5);
        buffer.put(element);

        Assert.assertEquals(element, buffer.get());
    }
}