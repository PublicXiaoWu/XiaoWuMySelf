package com.xiaowu.commonclass.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.telephony.TelephonyManager;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @类描述:系统工具包
 */
@SuppressLint("SimpleDateFormat")
public final class SystemTool {
    private SystemTool() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param format
     * @return
     * @方法说明:
     * @方法名称:getDataTime
     * @返回值:String
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * @return
     * @方法说明:返回当前系统时间(格式以HH:mm形式)
     * @方法名称:getDataTime
     * @返回值:String
     */
    public static String getDataTime() {
        return getDataTime("HH:mm");
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss 格式转换成毫秒
     *
     * @param dateStr
     * @return
     */
    public static long formatDate(String dateStr) {
        java.sql.Date d = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d = (java.sql.Date) Date(sf.parse(dateStr));
        } catch (Exception ignored) {

        }
        return d.getTime();
    }

    private static Date Date(Date date) {
        Date dateTime;
        dateTime = new java.sql.Date(date.getTime());
        return dateTime;
    }

    /**
     * @param cxt
     * @return
     * @方法说明:获取手机IMEI码
     * @方法名称:getPhoneIMEI
     * @返回值:String
     */
    @SuppressLint("MissingPermission")
    public static String getPhoneIMEI(Context cxt) {
        TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * @return
     * @方法说明:获取手机系统SDK版本
     * @方法名称:getSDKVersion
     * @返回值:int如API 17 则返回 17
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * @return
     * @方法说明:获取系统版本
     * @方法名称:getSystemVersion
     * @返回值:String形如2.3.3
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * @param cxt
     * @param smsBody
     * @方法说明:调用系统发送短信
     * @方法名称:sendSMS
     * @返回值:void
     */
    public static void sendSMS(Context cxt, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        cxt.startActivity(intent);
    }

    /**
     * @param context
     * @return
     * @方法说明:判断当前应用程序是否后台运行
     * @方法名称:isBackground
     * @返回值:boolean
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if ((appProcess == null) || (context == null))
                return false;
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
            }
        }
        return false;
    }

    /**
     * @param context
     * @return
     * @方法说明:判断手机是否处理睡眠
     * @方法名称:isSleeping
     * @返回值:boolean
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        boolean isSleeping = kgMgr.inKeyguardRestrictedInputMode();
        return isSleeping;
    }

    /**
     * @param context
     * @param file
     * @方法说明:安装apk
     * @方法名称:installApk
     * @返回值:void
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @return
     * @方法说明:获取当前应用程序的版本号
     * @方法名称:getAppVersionName
     * @返回值:String
     */
    public static String getAppVersionName(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException ignored) {
            throw new RuntimeException(SystemTool.class.getName() + "the application not found");
        }
        return version;
    }

    /**
     * @param context
     * @return
     * @方法说明:获取当前应用程序的版本号
     * @方法名称:getAppVersionCode
     * @返回值:int
     */
    public static int getAppVersionCode(Context context) {
        int version;
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException ignored) {
            throw new RuntimeException(SystemTool.class.getName() + "the application not found");
        }
        return version;
    }

    /**
     * @param context
     * @方法说明:回到home，后台运行
     * @方法名称:goHome
     * @返回值:void
     */
    public static void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    /**
     * @param context
     * @param pkgName
     * @return
     * @方法说明:获取应用签名
     * @方法名称:getSign
     * @返回值:String
     */
    public static String getSign(Context context, String pkgName) {
        try {
            PackageInfo pis = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);
            return hexdigest(pis.signatures[0].toByteArray());
        } catch (NameNotFoundException ignored) {
            throw new RuntimeException(SystemTool.class.getName() + "the " + pkgName + "'s application not found");
        }
    }

    /**
     * @param paramArrayOfByte
     * @return
     * @方法说明:将签名字符串转换成需要的32位签名
     * @方法名称:hexdigest
     * @返回值:String
     */
    private static String hexdigest(byte[] paramArrayOfByte) {
        char[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            for (int i = 0, j = 0; ; i++, j++) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(0xF & (k >>> 4))];
                arrayOfChar[++j] = hexDigits[(k & 0xF)];
            }
        } catch (Exception ignored) {
        }
        return "";
    }

    /**
     * @return
     * @方法说明:获取设备的可用内存大小
     * @方法名称:getDeviceUsableMemory
     * @返回值:int当前内存大小
     */
    public static int getDeviceUsableMemory(Context cxt) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        // 返回当前系统的可用内存
        return (int) (mi.availMem / (1024 * 1024));
    }


    /**
     * @param context
     * @param packageName
     * @return
     * @方法说明:判断手机已安装某程序的方法：
     * @方法名称:isApkInstall
     * @返回值:boolean
     */
    public static boolean isApkInstall(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    /**
     * 判断手机是否拥有Root权限。
     *
     * @return 有root权限返回true，否则返回false。
     */
    public boolean isRoot() {
        boolean bool = false;
        try {
            bool = new File("/system/bin/su").exists() || new File("/system/xbin/su").exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }


    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language.endsWith("zh");
    }
}
