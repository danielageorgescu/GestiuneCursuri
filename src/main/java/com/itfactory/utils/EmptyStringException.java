package com.itfactory.utils;

public class EmptyStringException extends Exception {
    public EmptyStringException(String lineSplit) {
        super(lineSplit);
    }
}
