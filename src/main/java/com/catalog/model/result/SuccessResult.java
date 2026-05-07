package com.catalog.model.result;

public class SuccessResult extends Result {
    public SuccessResult(String message) {
        super(true, message);
    }
}
