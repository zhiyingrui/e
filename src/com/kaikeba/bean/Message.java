package com.kaikeba.bean;

public class Message{
    //状态吗 0-表示成功 -1表示失败
    private int status;
    //消息内容
    private String result;
    //消息所携带的一种数据
    private Object data;

    public Message(int status) {
        this.status = status;
    }

    public Message(String result) {
        this.result = result;
    }

    public Message(int status, String result) {
        this.status = status;
        this.result = result;
    }

    public Message() {
    }

    public Message(int status, String result, Object data) {
        this.status = status;
        this.result = result;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
