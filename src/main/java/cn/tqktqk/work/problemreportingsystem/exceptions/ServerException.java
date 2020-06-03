package cn.tqktqk.work.problemreportingsystem.exceptions;


import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;

public class ServerException extends RuntimeException{

    private ResponseEnum responseEnum;

    private String msg;

    public ServerException(ResponseEnum responseEnum,String msg) {
        this.msg = msg;
        this.responseEnum = responseEnum;
    }

    public ServerException(ResponseEnum responseEnum) {
        this.msg = responseEnum.getMsg();
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public String getMsg() {
        return msg;
    }
}
