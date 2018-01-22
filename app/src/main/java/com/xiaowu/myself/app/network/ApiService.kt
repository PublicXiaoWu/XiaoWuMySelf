package com.xiaowu.myself.app.network

import com.xiaowu.myself.main.bean.ApplyPage
import com.xiaowu.myself.main.bean.NewVersion
import com.xiaowu.myself.main.bean.Login
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


/**
 * 作者：Administrator on 2017/8/28 14:18
 * 邮箱：zhanghuaiha@gmail.com
 */
interface ApiService {
    //http://api.douban.com/v2/movie/top250

    companion object{
        val BASE_URL : String
            get() = "https://www.wacdd.com"
    }


    //获取最新版本
    @POST("nothing/agent" + NetWorkCodeInfo.VERSION)
    @FormUrlEncoded
    fun getVersion(@FieldMap map : Map<String,String>):Observable<BaseResponseEntity<NewVersion>>

    //申请纸巾分页信息V2
    @POST("nothing/agent" + NetWorkCodeInfo.APPLY_PAGE_V2)
    @FormUrlEncoded
    fun getApplyPageV2(@FieldMap map : Map<String,String>): Observable<BaseResponseEntity<List<ApplyPage>>>


    //登录
    @POST("nothing/personal_agent" + NetWorkCodeInfo.LOGIN)
    @FormUrlEncoded
    fun login(@FieldMap map: Map<String, String>): Observable<BaseResponseEntity<Login>>

    //获取验证码
    @POST("nothing/personal_agent" + NetWorkCodeInfo.CODE)
    @FormUrlEncoded
    fun sendSms(@FieldMap map: Map<String, String>): Observable<BaseResponseEntity<String>>
}