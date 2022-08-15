package com.wall.exception;



public class UserPasswordNotMatchException extends ApiException {

    public UserPasswordNotMatchException(String errMsg)
    {
        super("400",errMsg);
    }

}
