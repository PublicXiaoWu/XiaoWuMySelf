package com.xiaowu.myself.app.network


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

}