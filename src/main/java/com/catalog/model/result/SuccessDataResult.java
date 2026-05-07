package com.catalog.model.result;

public class SuccessDataResult<T> extends DataResult<T> {
    public SuccessDataResult(T data, String message) {
        super(data, true, message);
    }
}
