package com.solution.test;

import com.solution.main.RingBufferImpl;
import org.junit.Assert;
import org.junit.Test;

public class RingBufferImplTest {

    @Test(expected = IllegalStateException.class)
    public void whenBufferIsEmpty() {
        RingBufferImpl<Object> buffer = new RingBufferImpl<>(10);
        buffer.get();

        //Then ex:
    }

    @Test(expected = IllegalStateException.class)
    public void whenBufferFull() {
        RingBufferImpl<Object> buffer = new RingBufferImpl<>(0);
        buffer.put(new Object());

        //Then ex:
    }

    @Test
    public void whenPutElement_ThenExpectTheSameElement() {
        Object element = new Object();

        RingBufferImpl<Object> buffer = new RingBufferImpl<>(5);
        buffer.put(element);

        Assert.assertEquals(element, buffer.get());
    }

    @Test
    public void whenPutSeveralElements_ThenReturnWithSameOrdering() {
        Object first = new Object();
        Object second = new Object();

        RingBufferImpl<Object> buffer = new RingBufferImpl<>(3);
        buffer.put(first);
        buffer.put(second);

        Assert.assertEquals(first, buffer.get());
        Assert.assertEquals(second, buffer.get());
    }

    @Test
    public void whenOverflowHasReached_AndGetElement_ThenHasFreeSpace() {
        Object first = new Object();
        Object second = new Object();
        Object third = new Object();
        Object fourth = new Object();

        RingBufferImpl<Object> buffer = new RingBufferImpl<>(3);
        buffer.put(first);
        buffer.put(second);
        buffer.put(third);

        Assert.assertEquals(first, buffer.get());

        buffer.put(fourth);

        Assert.assertEquals(second, buffer.get());
        Assert.assertEquals(third, buffer.get());
        Assert.assertEquals(fourth, buffer.get());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void whenPutNullElement() {
        Object first = null;
        Object second = new Object();
        Object third = null;

        RingBufferImpl<Object> buffer = new RingBufferImpl<>(3);
        buffer.put(first);
        buffer.put(second);
        buffer.put(third);

        Assert.assertEquals(first, buffer.get());
        Assert.assertEquals(second, buffer.get());
        Assert.assertEquals(third, buffer.get());
    }
}