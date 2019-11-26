package com.xiaowu.myself.app.network

import com.xiaowu.myself.main.bean.Login
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


interface ApiService {

    companion object{
        val BASE_URL : String
            get() = "http://192.168.2.222:8193"
    }

//    //登录
//    @POST("nothing/personal_agent" + NetWorkCodeInfo.LOGIN)
//    @FormUrlEncoded
//    fun login(@FieldMap map: Map<String, String>): Observable<BaseResponseEntity<Login>>

    //登录
    @POST("controlApp/appLogin")
    @FormUrlEncoded
    fun login(@FieldMap map: Map<String, String>): Observable<BaseResponseEntity<Login>>
}