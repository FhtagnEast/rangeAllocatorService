package com.fhtagneast.allocator.exceptions;

public class ThereIsNoRangeException extends RuntimeException {

    public ThereIsNoRangeException(int rangeId) {
        super("Range for id '" + rangeId + "' not found!");
    }

}
