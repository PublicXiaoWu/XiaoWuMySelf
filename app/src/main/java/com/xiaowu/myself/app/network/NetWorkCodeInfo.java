package com.xiaowu.myself.app.network;

/**
 * 作者：Administrator on 2017/6/26 14:24
 * 邮箱：zhanghuaiha@gmail.com
 */

public interface NetWorkCodeInfo {
    String CODE = "/app/v1/sms/send"; //获取验证码
    String GET = "/app/v1/device/get"; //获取设备详情
    String NEWGET = "/app/v1/device/get1"; //获取设备详情
    String UPDATE_DEVICE = "/app/v1/device/update"; //更改设备门店
    String SUPPLEMENT = "/app/v1/device/change_inventory"; //补充纸巾
    String SAVE = "/app/v1/bank/save"; //新增或修改银行卡
    String VERSION = "/app/v1/version/release"; //获取新版本
    String LOGIN = "/app/v1/sms/check_code_login"; //登录
    String TEL = "/app/v1/agent/check_identity"; //判断该手机号是否可用
    String PARSE = "/app/v1/qrcode/parse"; //扫一扫数据解析
    String STORE = "/app/v1/store/page"; //获取商家信息
    String SIMPLE_PAGE = "/app/v1/store/simple_page"; //获取商家信息(简单)
    String INSERT = "/app/v1/store/insert"; //添加门店
    String UPDATE = "/app/v1/store/update"; //更新门店
    String DELETE = "/app/v1/store/delete"; //删除门店
    String UPLOAD_IMAGE = "/app/v1/file/upload"; //上传图片
    String SHOP_PAPER = "/app/v1/store/device_page"; //门店下的纸巾机列表
    String STATISTICS = "/app/v1/agent/index_statistics"; //首页数据
    String RECORD = "/app/v2/withdrawals/page"; //提现列表
    String ACTIVATION = "/app/v1/device/activation"; //激活
    String EARNINGS = "/app/v1/agent/history_statistics"; //历史收益
    String HISTORY_STATISTICS = "/app/v1/device/history_statistics"; //单台纸巾机历史收益
    String OUT_PAPER = "/app/v1/device/out_papaer_page"; //缺纸设备数据列表
    String PAPER_NUMBER = "/app/v1/device/page"; //设备总数
    String QRCODE = "/app/v1/device/out_paper"; //扫码测试
    String UPDATE_WX = "/app/v1/agent/update_wx"; //更新微信信息
    String KTXZJ = "/app/v1/agent/withdrawals_desc"; //可提现资金，收益等
    String APPLY = "/app/v2/withdrawals/apply"; //申请提现
    String APPLY_PAGE = "/app/v1/apply_paper/page"; //申请纸巾分页信息
    String APPLY_PAGE_V2 = "/app/v2/apply_paper/page"; //申请纸巾分页信息 V2
    String APPLY_ADD = "/app/v1/apply_paper/insert"; //申请纸巾
    String ADDRESS_GET = "/app/v1/agent/address_get"; //查询收货地址
    String ADDRESS_UPDATE = "/app/v1/agent/address_update"; //更新收货地址
}
