package com.xiaowu.myself.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xiaowu.myself.app.base.MyApplication;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @类名:StringUtils
 * @类描述:字符串操作工具包
 */
public class StringUtils {
    private StringUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static Pattern phone = Pattern
            .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * @param input
     * @return
     * @方法说明:判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     * @方法名称:isEmpty
     * @返回值:boolean
     */
    public static boolean isEmpty(CharSequence input) {
        if ((input == null) || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((c != ' ') && (c != '\t') && (c != '\r') && (c != '\n')) {
                return false;
            }
        }
        return true;
    }


    /**
     * @param email
     * @return
     * @方法说明:判断是不是一个合法的电子邮件地址
     * @方法名称:isEmail
     * @返回值:boolean
     */
    public static boolean isEmail(CharSequence email) {
        return !isEmpty(email) && emailer.matcher(email).matches();
    }

    public static String array2Json(List<?> data) {
        try {
            Gson gson = new Gson();
            return gson.toJson(data);
        } catch (JsonIOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Integer> json2Array(String json) {
        List<Integer> data = null;
        try {
            Gson gson = new Gson();
            Type typeToken = new TypeToken<List<Integer>>() {
            }.getType();
            data = gson.fromJson(json, typeToken);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @param phoneNum
     * @return
     * @方法说明:判断是不是一个合法的手机号码
     * @方法名称:isPhone
     * @返回值:boolean
     */
    public static boolean isPhone(CharSequence phoneNum) {
        return !isEmpty(phoneNum) && phone.matcher(phoneNum).matches();
    }

    /**
     * @param format
     * @return
     * @方法说明:返回当前系统时间
     * @方法名称:getDataTime
     * @返回值:String
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * @param str
     * @param defValue
     * @return
     * @方法说明:字符串转整数
     * @方法名称:toInt
     * @返回值:int转换异常返回 0
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ignored) {
        }
        return defValue;
    }

    /**
     * @param obj
     * @return
     * @方法说明:对象转整
     * @方法名称:toInt
     * @返回值:int转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * @param obj
     * @return
     * @方法说明:String转long
     * @方法名称:toLong
     * @返回值:long转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception ignored) {
        }
        return 0;
    }

    /**
     * @param obj
     * @return
     * @方法说明:String转double
     * @方法名称:toDouble
     * @返回值:double
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception ignored) {
        }
        return 0D;
    }

    /**
     * @param b
     * @return
     * @方法说明:字符串转布尔
     * @方法名称:toBool
     * @返回值:boolean转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * @param str
     * @return
     * @方法说明:判断一个字符串是不是数字
     * @方法名称:isNumber
     * @返回值:boolean
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    /**
     * @param
     * @return
     * @方法说明:byte[]数组转换为16进制的字符串。
     * @方法名称:byteArrayToHexString
     * @返回值:String转换后的结果。
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * @param
     * @return
     * @方法说明:16进制表示的字符串转换为字节数组。
     * @方法名称:hexStringToByteArray
     * @返回值:byte[]字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * @param sdate
     * @return
     * @方法说明:以友好的方式显示时间
     * @方法名称:friendlyTime
     * @返回值:String
     */
    public static String friendlyTime(String sdate) {
        Date time;

        if (isInEasternEightZones()) {
            time = toDate(sdate);
        } else {
            time = transformTime(toDate(sdate), TimeZone.getTimeZone("GMT+08"),
                    TimeZone.getDefault());
        }

        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天 ";
        } else if ((days > 2) && (days < 31)) {
            ftime = days + "天前";
        } else if ((days >= 31) && (days <= (2 * 31))) {
            ftime = "一个月前";
        } else if ((days > (2 * 31)) && (days <= (3 * 31))) {
            ftime = "2个月前";
        } else if ((days > (3 * 31)) && (days <= (4 * 31))) {
            ftime = "3个月前";
        } else {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * @param sdate
     * @return
     * @方法说明:将字符串转位日期类型
     * @方法名称:toDate
     * @返回值:Date
     */
    public static Date toDate(String sdate) {
        return toDate(sdate, dateFormater.get());
    }

    public static Date toDate(String sdate, SimpleDateFormat dateFormater) {
        try {
            return dateFormater.parse(sdate);
        } catch (ParseException ignored) {
            return null;
        }
    }

    /**
     * 自1970年至今将秒数转化为日期
     *
     * @param time
     * @return
     */
    public static String MstoDate(String time) {
        String str = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!TextUtils.isEmpty(time)) {
            str = dateFormat.format(Long.parseLong(time) * 1000);
        }
        return str;
    }

    /**
     * @return
     * @方法说明:判断用户的设备时区是否为东八区（中国） 2014年7月31日
     * @方法名称:isInEasternEightZones
     * @返回值:boolean
     */
    public static boolean isInEasternEightZones() {
        boolean defaultVaule = true;
        defaultVaule = TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08");
        return defaultVaule;
    }

    /**
     * @param date
     * @param oldZone
     * @param newZone
     * @return
     * @方法说明:根据不同时区，转换时间
     * @方法名称:transformTime
     * @返回值:Date
     */
    public static Date transformTime(Date date, TimeZone oldZone,
                                     TimeZone newZone) {
        Date finalDate = null;
        if (date != null) {
            int timeOffset = oldZone.getOffset(date.getTime())
                    - newZone.getOffset(date.getTime());
            finalDate = new Date(date.getTime() - timeOffset);
        }
        return finalDate;
    }

    public static String getStringfromResource(int ResourceId) {
        Context context = MyApplication.getAppContext();
        return context.getResources().getString(ResourceId);
    }


    //5.5-->5.5  5.0-->5
    public static String doubleToString(double num) {
        if (num % 1 == 0) {
            return String.valueOf(num).split("\\.")[0];
        } else {
            return String.valueOf(num);
        }
    }

    //将double转成小数点后只有一位
    public static String doubleToTwoString(double num) {
        BigDecimal b = new BigDecimal(num);
        double str = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(str);
    }

    //将double转成一个正整数，小于零就直接等于0
    public static double toPositiveInteger(double v) {
        if (v < 0)
            return 0;
        else
            return v;
    }
}

