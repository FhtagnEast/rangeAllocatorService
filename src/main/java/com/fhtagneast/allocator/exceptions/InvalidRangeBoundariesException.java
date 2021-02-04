package com.fhtagneast.allocator.exceptions;

public class InvalidRangeBoundariesException extends RuntimeException {

    public InvalidRangeBoundariesException(int minValue, int maxValue) {
        super("Invalid range boundaries: min '" + minValue + "', max '" + maxValue);
    }
}
