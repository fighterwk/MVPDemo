package com.kyle.mvp.bean;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/2
 */
public class ResponseBean<T> {
    private String ResultMessage;
    private int ResultCode;
    private T Data;

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        ResultMessage = resultMessage;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
