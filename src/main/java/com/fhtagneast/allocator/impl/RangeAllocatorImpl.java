package com.fhtagneast.allocator.impl;

import com.fhtagneast.allocator.RangeAllocator;
import com.fhtagneast.allocator.exceptions.InvalidRangeBoundariesException;
import com.fhtagneast.allocator.exceptions.ThereIsNoRangeException;
import com.fhtagneast.allocator.utils.Range;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RangeAllocatorImpl implements RangeAllocator {

    private final AtomicInteger lastId = new AtomicInteger(0);

    private final Map<Integer, Range> rangeMapping = new ConcurrentHashMap<>();

    @Override
    public int createRange(int minValue, int maxValue) {
        if (maxValue < minValue) {
            throw new InvalidRangeBoundariesException(minValue, maxValue);
        }

        int rangeId = lastId.getAndIncrement();

        Range range = createRangeFromBoundaries(minValue, maxValue);

        rangeMapping.put(rangeId, range);

        return rangeId;
    }

    @Override
    public int allocate(int rangeId) {
        Range range = rangeMapping.get(rangeId);
        if (range != null) {
            return range.allocate();
        } else {
            throw new ThereIsNoRangeException(rangeId);
        }
    }

    @Override
    public void release(int rangeId, int value) {
        Range range = rangeMapping.get(rangeId);
        if (range != null) {
            range.release(value);
        } else {
            throw new ThereIsNoRangeException(rangeId);
        }
    }

    private static Range createRangeFromBoundaries(int minValue, int maxValue) {
        return new Range(new boolean[maxValue - minValue + 1], minValue);
    }
}
