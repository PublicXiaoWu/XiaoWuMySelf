package com.xiaowu.myself.app.network;

public class BaseResponseEntity<T>  {

    // "new_date": "2017-08-07",  //最新有数据的 日期
    public String msg;
    public Boolean ok;
    public T data;
    public int status;
}
