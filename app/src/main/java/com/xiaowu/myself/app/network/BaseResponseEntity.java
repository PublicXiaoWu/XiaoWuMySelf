package com.xiaowu.myself.app.network;

public class BaseResponseEntity<T>  {

    // "new_date": "2017-08-07",  //最新有数据的 日期
    public String message;
    public String total;
    public int code;
    public T data;
    public String new_date;
    public String last_update_time;
    public int is_calculation;
}
