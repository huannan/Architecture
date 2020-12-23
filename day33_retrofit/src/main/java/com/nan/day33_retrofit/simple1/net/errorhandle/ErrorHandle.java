package com.nan.day33_retrofit.simple1.net.errorhandle;

public class ErrorHandle {

    public static class ServiceError extends RuntimeException{
        String errorCode;
        public ServiceError(String errorCode, String errorMsg) {
            super(errorMsg);
            this.errorCode = errorCode;
        }
    }
}