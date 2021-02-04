package com.fhtagneast.allocator;

public interface RangeAllocator {

    int createRange(int minValue, int maxValue);

    int allocate(int rangeId);

    void release(int rangeId, int value);

}
