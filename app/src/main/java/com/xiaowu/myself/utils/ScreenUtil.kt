package com.xiaowu.myself.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Rect
import android.util.DisplayMetrics

/**
 * @类描述:屏幕相关工具类
 */
class ScreenUtil private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * @param context
         * @return
         * @方法说明:
         * @方法名称:getDisPlayMetrics
         * @返回 DisplayMetrics
         */
        fun getDisPlayMetrics(context: Context?): DisplayMetrics {
            val metric = DisplayMetrics()
            if (null != context) {
                (context as Activity).windowManager.defaultDisplay.getMetrics(metric)
            }
            return metric
        }

        /**
         * @param context
         * @return
         * @方法说明:获取屏幕的宽度（像素）
         * @方法名称:getScreenWidth
         * @返回值:int
         */
        fun getScreenWidth(context: Context): Int {
            return getDisPlayMetrics(context).widthPixels
        }

        /**
         * @param context
         * @return
         * @方法说明:获取屏幕的高度
         * @方法名称:getScreenHeight
         * @返回值:int
         */
        fun getScreenHeight(context: Context): Int {
            return getDisPlayMetrics(context).heightPixels
        }

        /**
         * @param context
         * @return
         * @方法说明:屏幕密度(0.75 / 1.0 / 1.5)
         * @方法名称:getDensity
         * @返回 float
         */
        fun getDensity(context: Context): Float {
            return getDisPlayMetrics(context).density
        }

        /**
         * @param context
         * @return
         * @方法说明:屏幕密度DPI(120 / 160 / 240)
         * @方法名称:getDensityDpi
         * @返回 int
         */
        fun getDensityDpi(context: Context): Int {
            return getDisPlayMetrics(context).densityDpi
        }

        // 导航栏高度
        fun getNavigationHeight(context: Context): Int {
            val resourceId = context.resources.getIdentifier(
                    "navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                val rid = context.resources.getIdentifier(
                        "config_showNavigationBar", "bool", "android")
                val isTrue = context.resources.getBoolean(rid)
                if (isTrue) {
                    return context.resources.getDimensionPixelSize(
                            resourceId)
                }
            }
            return 0
        }

        // 状态栏高度
        fun getStatusHeight(activity: Activity): Int {
            var statusHeight = 0
            val localRect = Rect()
            activity.window.decorView
                    .getWindowVisibleDisplayFrame(localRect)
            statusHeight = localRect.top
            if (0 == statusHeight) {
                val localClass: Class<*>
                try {
                    localClass = Class.forName("com.android.internal.R\$dimen")
                    val localObject = localClass.newInstance()
                    val i5 = Integer.parseInt(localClass
                            .getField("status_bar_height").get(localObject)
                            .toString())
                    statusHeight = activity.resources
                            .getDimensionPixelSize(i5)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return statusHeight
        }

        // 屏幕X轴方向上的长度（dp）
        fun getXDp(context: Context): Double {
            return (getScreenWidth(context) / getDensity(context)).toDouble()
        }

        // 屏幕Y轴方向上长度（sp）
        fun getYDp(context: Context): Double {
            return ((getScreenHeight(context) + getNavigationHeight(context)) / getDensity(context)).toDouble()
        }

        /**
         * @param context
         * @param dpValue
         * @return
         * @方法说明:根据手机的分辨率从 dp 的单位 转成为 px(像素)
         * @方法名称:dip2px
         * @返回值:int
         */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        /**
         * @param context
         * @param pxValue
         * @return
         * @方法说明:根据手机的分辨率从 px(像素) 的单位 转成为 dp
         * @方法名称:px2dip
         * @返回值:int
         */
        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        /**
         * @param context
         * @return
         * @方法说明:获取状态栏的高度
         * @方法名称:getStatusBarHeight
         * @返回值:int
         */
        fun getStatusBarHeight(context: Context): Int {
            var result = 0
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }

        /**
         * @param context
         * @return
         * @方法说明:获取状态栏的高度（反射）
         * @方法名称:getStatusHeight
         * @返回值:int
         */
        fun getStatusHeight(context: Context): Int {
            var statusHeight = -1
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val `object` = clazz.newInstance()
                val height = Integer.parseInt(clazz.getField("status_bar_height").get(`object`).toString())
                statusHeight = context.resources.getDimensionPixelSize(height)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return statusHeight
        }

        fun getMinWidthOrHeight(context: Context): Int {
            val height = getScreenHeight(context)// 屏幕的高度
            val width = getScreenWidth(context)// 屏幕的宽度
            return Math.min(width, height)// 屏幕最小的值
        }

        fun getMaxWidthOrHeight(context: Context): Int {
            val height = getScreenHeight(context)// 屏幕的高度
            val width = getScreenWidth(context)// 屏幕的宽度
            return Math.max(width, height)// 屏幕最大的值
        }

        /**
         * @return
         * @方法说明:得到cardView横屏和竖屏情况下，每个cardView宽度一致，并且默认每个cardView的最小左右margin为10dp
         * @方法名称:viewWidth
         * @返回值:int
         */
        fun viewWidth(context: Context): Int {
            val height = getScreenHeight(context)// 屏幕的高度
            val width = getScreenWidth(context)// 屏幕的宽度
            val maxSize = Math.max(width, height)// 屏幕最大的值
            val minSize = Math.min(width, height)// 屏幕最小的值
            val marginSize = dip2px(context, 5f) * 2// cardView最小两边边距和
            var size = maxSize / 2 - marginSize// 每个卡片理想对应的大小宽度
            if (size > minSize - marginSize) {// 如果每个卡片的理想对应宽度大于了（屏幕实际宽度 -
                // 左右两边边距的宽度）
                size = minSize - marginSize// 对应的理想宽度 = 屏幕实际宽度 - 左右两边边距的宽度
            }
            return size
        }

        /**
         * 获取卡片的宽度
         *
         * @param activity
         * @return
         */
        fun viewCardWidth(activity: Activity): Int {
            val marginSize = dip2px(activity, 5f) * 2// cardView最小两边边距和
            var size = 0
            if (isLandOrientScreen(activity)) {//横屏
                size = getMaxWidthOrHeight(activity) / 2 - marginSize
            } else {//竖屏
                size = getMinWidthOrHeight(activity) - marginSize
            }
            return size
        }

        /**
         * @param activity
         * @return
         * @方法说明:判断当前屏幕是否是横屏，是为true,竖屏false
         * @方法名称:isLandScreen
         * @返回值:boolean
         */
        fun isLandOrientScreen(activity: Activity): Boolean {
            var orient = activity.resources.configuration.orientation
            if (orient != Configuration.ORIENTATION_LANDSCAPE && orient != Configuration.ORIENTATION_PORTRAIT) {
                val screenWidth = getScreenWidth(activity)
                val screenHeight = getScreenHeight(activity)
                orient = if (screenWidth < screenHeight)
                    Configuration.ORIENTATION_PORTRAIT
                else
                    Configuration.ORIENTATION_LANDSCAPE
            }
            return orient == Configuration.ORIENTATION_LANDSCAPE
        }

        fun isPad(context: Context): Boolean {
            //        double x = Math.pow(getScreenWidth(context) / getDisPlayMetrics(context).xdpi, 2);
            //        double y = Math.pow(getScreenHeight(context) / getDisPlayMetrics(context).ydpi, 2);
            //        // 屏幕尺寸
            //        double screenInches = Math.sqrt(x + y);
            //        // 大于6尺寸则为Pad
            //        if (screenInches >= 6.0) {
            //            return true;
            //        }
            //        return false;
            return getScreenDimension(context) >= 5
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
        fun isPhone(context: Context): Boolean {
            return getScreenDimension(context) < 5
        }

        /**
         * 判断是否平板设备(官方给出的)
         *
         * @param context
         * @return true:平板,false:手机
         */
        fun isPadDevices(context: Context): Boolean {

            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }

        /**
         * 获取屏幕的尺寸（英寸）
         *
         * @param context
         * @return
         */
        fun getScreenDimension(context: Context): Double {
            // 得到屏幕的宽(像素)
            val screenX = getScreenWidth(context)
            // 得到屏幕的高(像素)
            val screenY = getScreenHeight(context)
            // 每英寸的像素点
            val dpi = getDensityDpi(context)
            // 得到屏幕的宽(英寸)
            val a = (screenX / dpi).toFloat()
            // 得到屏幕的高(英寸)
            val b = (screenY / dpi).toFloat()
            // 勾股定理
            return Math.sqrt((a * a + b * b).toDouble())
        }
    }
}
