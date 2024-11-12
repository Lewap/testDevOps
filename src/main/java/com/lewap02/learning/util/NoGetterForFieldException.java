package com.lewap02.learning.util;

public class NoGetterForFieldException extends Exception {
    public NoGetterForFieldException (String errMsg) {
        super(errMsg);
    }
}