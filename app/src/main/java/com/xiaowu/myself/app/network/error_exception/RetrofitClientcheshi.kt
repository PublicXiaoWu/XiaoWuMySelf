package com.xiaowu.myself.app.network.error_exception

import android.content.Context
import android.util.Log
import com.xiaowu.myself.app.network.ApiService
import com.xiaowu.myself.app.network.CacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 作者：Administrator on 2017/8/28 10:40
 * 邮箱：zhanghuaiha@gmail.com
 */
class RetrofitClientcheshi private constructor(context: Context, baseUrl: String) {
    var httpCacheDirectory: File? = null
    val mContext: Context = context
    var cache: Cache? = null
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    val DEFAULT_TIMEOUT: Long = 60
    val url = baseUrl
    var mApi: ApiService? = null

    init {
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }

        mApi = provideHotApi()

    }

    fun provideHotApi(): ApiService? {
        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(CacheInterceptor(mContext))
                .addNetworkInterceptor(CacheInterceptor(mContext))
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //retrofit创建了
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build()
        return retrofit?.create(ApiService::class.java)
    }

    companion object {
        var instance: RetrofitClientcheshi? = null
        fun getInstance(context: Context, baseUrl: String): RetrofitClientcheshi {
            if (instance == null) {
                synchronized(RetrofitClientcheshi::class) {
                    if (instance == null) {
                        instance = RetrofitClientcheshi(context, baseUrl)
                    }
                }
            }
            return instance!!
        }
    }

}