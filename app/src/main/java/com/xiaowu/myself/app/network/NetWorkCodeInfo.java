package com.xiaowu.myself.app.network;

/**
 * 作者：Administrator on 2017/6/26 14:24
 * 邮箱：zhanghuaiha@gmail.com
 */

public interface NetWorkCodeInfo {
    String CODE = "/app/v1/sms/send"; //获取验证码
    String VERSION = "/app/v1/version/release"; //获取新版本
    String LOGIN = "/app/v1/sms/check_code_login"; //登录
    String APPLY_PAGE_V2 = "/app/v2/apply_paper/page"; //申请纸巾分页信息 V2
}
