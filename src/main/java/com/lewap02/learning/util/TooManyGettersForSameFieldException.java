package com.lewap02.learning.util;

public class TooManyGettersForSameFieldException extends Exception {
    public TooManyGettersForSameFieldException(String errMsg) {
        super(errMsg);
    }
}