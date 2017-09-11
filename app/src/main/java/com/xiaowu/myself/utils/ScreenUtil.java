package com.xiaowu.myself.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * @类描述:屏幕相关工具类
 */
public class ScreenUtil {

    private ScreenUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param context
     * @return
     * @方法说明:
     * @方法名称:getDisPlayMetrics
     * @返回 DisplayMetrics
     */
    public static DisplayMetrics getDisPlayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        if (null != context) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        }
        return metric;
    }

    /**
     * @param context
     * @return
     * @方法说明:获取屏幕的宽度（像素）
     * @方法名称:getScreenWidth
     * @返回值:int
     */
    public static int getScreenWidth(Context context) {
        int width = getDisPlayMetrics(context).widthPixels;
        return width;
    }

    /**
     * @param context
     * @return
     * @方法说明:获取屏幕的高度
     * @方法名称:getScreenHeight
     * @返回值:int
     */
    public static int getScreenHeight(Context context) {
        int height = getDisPlayMetrics(context).heightPixels;
        return height;
    }

    /**
     * @param context
     * @return
     * @方法说明:屏幕密度(0.75 / 1.0 / 1.5)
     * @方法名称:getDensity
     * @返回 float
     */
    public static float getDensity(Context context) {
        float density = getDisPlayMetrics(context).density;
        return density;
    }

    /**
     * @param context
     * @return
     * @方法说明:屏幕密度DPI(120 / 160 / 240)
     * @方法名称:getDensityDpi
     * @返回 int
     */
    public static int getDensityDpi(Context context) {
        int densityDpi = getDisPlayMetrics(context).densityDpi;
        return densityDpi;
    }

    // 导航栏高度
    public static int getNavigationHeight(Context context) {
        int resourceId = context.getResources().getIdentifier(
                "navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            int rid = context.getResources().getIdentifier(
                    "config_showNavigationBar", "bool", "android");
            boolean isTrue = context.getResources().getBoolean(rid);
            if (isTrue) {
                return context.getResources().getDimensionPixelSize(
                        resourceId);
            }
        }
        return 0;
    }

    // 状态栏高度
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass
                        .getField("status_bar_height").get(localObject)
                        .toString());
                statusHeight = activity.getResources()
                        .getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    // 屏幕X轴方向上的长度（dp）
    public static double getXDp(Context context) {
        return getScreenWidth(context) / getDensity(context);
    }

    // 屏幕Y轴方向上长度（sp）
    public static double getYDp(Context context) {
        return (getScreenHeight(context) + getNavigationHeight(context)) / getDensity(context);
    }

    /**
     * @param context
     * @param dpValue
     * @return
     * @方法说明:根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @方法名称:dip2px
     * @返回值:int
     */
    @SuppressWarnings("UnnecessaryFinalOnLocalVariableOrParameter")
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    /**
     * @param context
     * @param pxValue
     * @return
     * @方法说明:根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @方法名称:px2dip
     * @返回值:int
     */
    @SuppressWarnings("UnnecessaryFinalOnLocalVariableOrParameter")
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((pxValue / scale) + 0.5f);
    }

    /**
     * @param context
     * @return
     * @方法说明:获取状态栏的高度
     * @方法名称:getStatusBarHeight
     * @返回值:int
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * @param context
     * @return
     * @方法说明:获取状态栏的高度（反射）
     * @方法名称:getStatusHeight
     * @返回值:int
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static int getMinWidthOrHeight(Context context) {
        int height = getScreenHeight(context);// 屏幕的高度
        int width = getScreenWidth(context);// 屏幕的宽度
        return Math.min(width, height);// 屏幕最小的值
    }

    public static int getMaxWidthOrHeight(Context context) {
        int height = getScreenHeight(context);// 屏幕的高度
        int width = getScreenWidth(context);// 屏幕的宽度
        return Math.max(width, height);// 屏幕最大的值
    }

    /**
     * @return
     * @方法说明:得到cardView横屏和竖屏情况下，每个cardView宽度一致，并且默认每个cardView的最小左右margin为10dp
     * @方法名称:viewWidth
     * @返回值:int
     */
    public static int viewWidth(Context context) {
        int height = getScreenHeight(context);// 屏幕的高度
        int width = getScreenWidth(context);// 屏幕的宽度
        int maxSize = Math.max(width, height);// 屏幕最大的值
        int minSize = Math.min(width, height);// 屏幕最小的值
        int marginSize = dip2px(context, 5) * 2;// cardView最小两边边距和
        int size = (maxSize / 2) - marginSize;// 每个卡片理想对应的大小宽度
        if (size > (minSize - marginSize)) {// 如果每个卡片的理想对应宽度大于了（屏幕实际宽度 -
            // 左右两边边距的宽度）
            size = minSize - marginSize;// 对应的理想宽度 = 屏幕实际宽度 - 左右两边边距的宽度
        }
        return size;
    }

    /**
     * 获取卡片的宽度
     *
     * @param activity
     * @return
     */
    public static int viewCardWidth(Activity activity) {
        int marginSize = dip2px(activity, 5) * 2;// cardView最小两边边距和
        int size = 0;
        if (isLandOrientScreen(activity)) {//横屏
            size = (getMaxWidthOrHeight(activity) / 2) - marginSize;
        } else {//竖屏
            size = getMinWidthOrHeight(activity) - marginSize;
        }
        return size;
    }

    /**
     * @param activity
     * @return
     * @方法说明:判断当前屏幕是否是横屏，是为true,竖屏false
     * @方法名称:isLandScreen
     * @返回值:boolean
     */
    public static boolean isLandOrientScreen(Activity activity) {
        int orient = activity.getResources().getConfiguration().orientation;
        if ((orient != Configuration.ORIENTATION_LANDSCAPE) && (orient != Configuration.ORIENTATION_PORTRAIT)) {
            int screenWidth = getScreenWidth(activity);
            int screenHeight = getScreenHeight(activity);
            orient = (screenWidth < screenHeight) ? Configuration.ORIENTATION_PORTRAIT
                    : Configuration.ORIENTATION_LANDSCAPE;
        }
        return (orient == Configuration.ORIENTATION_LANDSCAPE);
    }

    public static boolean isPad(Context context) {
//        double x = Math.pow(getScreenWidth(context) / getDisPlayMetrics(context).xdpi, 2);
//        double y = Math.pow(getScreenHeight(context) / getDisPlayMetrics(context).ydpi, 2);
//        // 屏幕尺寸
//        double screenInches = Math.sqrt(x + y);
//        // 大于6尺寸则为Pad
//        if (screenInches >= 6.0) {
//            return true;
//        }
//        return false;
        return getScreenDimension(context) >= 5;
    }

    /**
     * screenIn >= 5,就是平板了 应该可以了
     * 华为mediapad     x=800  y=1216 dpi=213   a=3  b=5 screenIn=5.8
     * 小米1s           x=480  y=850   dpi=240   a=2  b=3 screenIn=3.6
     * 小米2            x=720  y=1280   dpi=320   a=2  b=4 screenIn=4.47
     * 摩托罗拉MZ606  x=800 y=1232  dpi=160   a=5  b=7 screenIn=8.6
     *
     * @param context
     * @return
     */
    public static boolean isPhone(Context context) {
        return getScreenDimension(context) < 5;
    }

    /**
     * 判断是否平板设备(官方给出的)
     *
     * @param context
     * @return true:平板,false:手机
     */
    public static boolean isPadDevices(Context context) {

        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获取屏幕的尺寸（英寸）
     *
     * @param context
     * @return
     */
    public static double getScreenDimension(Context context) {
        // 得到屏幕的宽(像素)
        int screenX = getScreenWidth(context);
        // 得到屏幕的高(像素)
        int screenY = getScreenHeight(context);
        // 每英寸的像素点
        int dpi = getDensityDpi(context);
        // 得到屏幕的宽(英寸)
        float a = screenX / dpi;
        // 得到屏幕的高(英寸)
        float b = screenY / dpi;
        // 勾股定理
        double screenIn = Math.sqrt((a * a) + (b * b));
        return screenIn;
    }
}
