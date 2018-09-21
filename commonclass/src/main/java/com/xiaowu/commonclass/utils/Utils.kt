package com.xiaowu.myself.utils

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class Utils private constructor() {
    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        private var lastClickTime: Long = 0

        /**
         * @param times
         * @return
         * @方法说明:防止控件被重复点击，如果点击间隔时间小于指定时间就点击无效,true表示点击无效，false表示点击有效
         * @方法名称:isFastDoubleClick
         * @返回值:boolean
         */
        fun isFastDoubleClick(times: Long): Boolean {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            if (0 < timeD && timeD < times) {
                return true
            }
            lastClickTime = time
            return false
        }

        /*
    *
    * */
        fun jump2WebView(context: Context, url: String) {
            val uri = Uri.parse(url)
            val it = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(it)
        }

        fun getTime(formats: String): String {
            var time = ""
            val date = Date()
            if (TextUtils.isEmpty(formats)) {
                val format = SimpleDateFormat("yyyyMMddHHmmss")
                time = format.format(date)
            } else {
                val format = SimpleDateFormat(formats)
                time = format.format(date)
            }
            return time
        }


        fun dpToPx(dp: Float, resources: Resources): Int {
            val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
            return px.toInt()
        }


        fun math_round(count: Float): String {
            val b = Math.round(count * 100).toFloat() / 100
            return b.toString()
        }

        /**
         * Photos显示列数
         *
         * @return
         */
        fun showPhotoColumns(context: Context): Int {
            var column = 2//默认是两列
            when (context.resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE// 横屏
                -> if (ScreenUtil.isPad(context)) {//平板
                    column = 5
                } else {
                    column = 3
                }
                Configuration.ORIENTATION_PORTRAIT// 竖屏
                -> if (ScreenUtil.isPad(context)) {//平板
                    column = 3
                } else {
                    column = 2
                }
            }
            return column
        }

        /**
         * Photos中item显示的宽度
         *
         * @param context
         * @return
         */
        fun getPhotosWidth(context: Context): Int {
            var width = 0
            when (context.resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE// 横屏
                -> width = ScreenUtil.getMaxWidthOrHeight(context) / showPhotoColumns(context) - ScreenUtil.dip2px(context, 40f)//每个item左右间距分别是20dp
                Configuration.ORIENTATION_PORTRAIT// 竖屏
                -> width = ScreenUtil.getMinWidthOrHeight(context) / showPhotoColumns(context) - ScreenUtil.dip2px(context, 40f)
            }
            return width
        }

        fun getItemViewWidth(context: Context, columns: Int, padding: Int): Int {
            var width = 0
            when (context.resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE// 横屏
                -> width = (ScreenUtil.getMaxWidthOrHeight(context) - ScreenUtil.dip2px(context, padding.toFloat())) / columns - ScreenUtil.dip2px(context,
                        padding.toFloat())//每个item左右间距分别是20dp
                Configuration.ORIENTATION_PORTRAIT// 竖屏
                -> width = (ScreenUtil.getMinWidthOrHeight(context) - ScreenUtil.dip2px(context, padding.toFloat())) / columns - ScreenUtil.dip2px(context,
                        padding.toFloat())
            }
            return width
        }

        /**
         * Photos显示列数
         *
         * @param context
         * @return
         */
        fun showCameraColumns(context: Context): Int {
            var column = 3//默认是两列
            when (context.resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE// 横屏
                -> if (ScreenUtil.isPadDevices(context)) {//平板
                    column = 8
                } else if (ScreenUtil.isPhone(context)) {
                    column = 5
                } else {
                    column = 12
                }
                Configuration.ORIENTATION_PORTRAIT// 竖屏
                -> if (ScreenUtil.isPadDevices(context)) {//平板
                    column = 5
                } else if (ScreenUtil.isPhone(context)) {
                    column = 3
                } else {
                    column = 8
                }
            }
            return column
        }

        fun getCameraWidth(context: Context): Int {
            var width = 0
            when (context.resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE// 横屏
                -> width = ScreenUtil.getMaxWidthOrHeight(context) / showCameraColumns(context) - ScreenUtil.dip2px(context, 2f)//每个item左右间距分别是20dp
                Configuration.ORIENTATION_PORTRAIT// 竖屏
                -> width = ScreenUtil.getMinWidthOrHeight(context) / showCameraColumns(context) - ScreenUtil.dip2px(context, 2f)
            }
            return width
        }

        // Android获取一个用于打开文本文件的intent
        fun getTextFileIntent(param: String, paramBoolean: Boolean): Intent {
            val intent = Intent("android.intent.action.VIEW")
            intent.addCategory("android.intent.category.DEFAULT")
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            if (paramBoolean) {
                val uri1 = Uri.parse(param)
                intent.setDataAndType(uri1, "text/plain")
            } else {
                val uri2 = Uri.fromFile(File(param))
                intent.setDataAndType(uri2, "text/plain")
            }
            return intent
        }


        // Android获取一个用于打开Html文件的intent
        fun getHtmlFileIntent(param: String): Intent {
            val intent = Intent("android.intent.action.VIEW")
            val uri = Uri.parse("file://" + param)
            intent.setDataAndType(uri, "text/html")
            return intent
        }

        /**
         * param email
         * return
         * 方法说明:判断是不是一个合法的电子邮件地址
         * 方法名称:isEmail
         * 返回值:boolean
         */
        private val emailer = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")

        fun isEmail(email: CharSequence): Boolean {
            return emailer.matcher(email).matches()
        }


        fun calculatePopWindowPos(context: Context, anchorView: View, contentView: View): IntArray {
            val windowPos = IntArray(2)
            val anchorLoc = IntArray(2)
            // 获取锚点View在屏幕上的左上角坐标位置
            anchorView.getLocationOnScreen(anchorLoc)
            val anchorHeight = anchorView.height
            // 获取屏幕的高宽
            val screenHeight = context.resources.displayMetrics.heightPixels
            val screenWidth = context.resources.displayMetrics.widthPixels
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            // 计算contentView的高宽
            val windowHeight = contentView.measuredHeight
            val windowWidth = contentView.measuredWidth
            // 判断需要向上弹出还是向下弹出显示
            val isNeedShowUp = screenHeight - anchorLoc[1] - anchorHeight < windowHeight
            if (isNeedShowUp) {
                windowPos[0] = screenWidth - windowWidth
                windowPos[1] = anchorLoc[1] - windowHeight
            } else {
                windowPos[0] = screenWidth - windowWidth
                windowPos[1] = anchorLoc[1] + anchorHeight
            }
            return windowPos
        }
    }
}
