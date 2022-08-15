package com.wall.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ApiException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    protected String errCode;
    protected String errMsg;


    public ApiException( String errCode, String errMsg){
        this.errCode=errCode;
        this.errMsg = errMsg;
    }


    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }




    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
            .append("errCode", getErrCode())
            .append("errMsg", getErrMsg())
            .toString();
    }
}
