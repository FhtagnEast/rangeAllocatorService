package com.fhtagneast.allocator.utils;

public class Range {

    private final boolean[] range;

    private final int minValue;

    private int pointer = 0;

    public Range(boolean[] range, int minValue) {
        this.range = range;
        this.minValue = minValue;
    }

    public int allocate() {
        boolean retry = false;
        int value = -1;
        boolean isFree;
        do {
            isFree = !range[pointer];
            value = pointer;
            range[pointer] = true;
            ++pointer;
            if (pointer == range.length) {
                pointer = 0;
                retry = true;
            }
        } while (!isFree && !(retry && pointer == range.length - 1));

        if (!isFree) {
            throw new RuntimeException("There is no free ID's in range");
        }

        return value + minValue;
    }

    public synchronized void release(int value) {
        range[value - minValue] = false;
    }

}
