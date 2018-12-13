package com.xiaowu.myself.app.network

import com.xiaowu.myself.main.bean.Login
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


interface ApiService {

    companion object{
        val BASE_URL : String
            get() = "https://www.wacdd.com"
    }

    //登录
    @POST("nothing/personal_agent" + NetWorkCodeInfo.LOGIN)
    @FormUrlEncoded
    fun login(@FieldMap map: Map<String, String>): Observable<BaseResponseEntity<Login>>
}