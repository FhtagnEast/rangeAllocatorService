package com.yandex.interview;

import com.fhtagneast.allocator.RangeAllocator;
import com.fhtagneast.allocator.impl.RangeAllocatorImpl;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RangeTest {

    @Test
    public void rangeTest() {
        RangeAllocator allocator = new RangeAllocatorImpl();
        int minValue = 0;
        int maxValue = 2;
        int rangeId = allocator.createRange(minValue, maxValue);
        int id = allocator.allocate(rangeId);
        Assert.assertEquals(id, minValue);
        allocator.allocate(rangeId);
        allocator.allocate(rangeId);
        Assert.expectThrows(RuntimeException.class, () -> allocator.allocate(rangeId));
        allocator.release(rangeId, 1);
        id = allocator.allocate(rangeId);
        Assert.assertEquals(id, 1);
        Assert.expectThrows(RuntimeException.class, () -> allocator.allocate(rangeId + 1));
    }
}
