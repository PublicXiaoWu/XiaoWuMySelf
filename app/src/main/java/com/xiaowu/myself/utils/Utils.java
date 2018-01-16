package com.xiaowu.myself.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaowu.myself.app.base.MyApplication;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Utils {
    private Utils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static long lastClickTime;

    /**
     * @param times
     * @return
     * @方法说明:防止控件被重复点击，如果点击间隔时间小于指定时间就点击无效,true表示点击无效，false表示点击有效
     * @方法名称:isFastDoubleClick
     * @返回值:boolean
     */
    public static boolean isFastDoubleClick(final long times) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ((0 < timeD) && (timeD < times)) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /*
    *
    * */
    public static void jump2WebView(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(it);
    }

    public static String getTime(String formats) {
        String time = "";
        Date date = new Date();
        if (TextUtils.isEmpty(formats)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            time = format.format(date);
        } else {
            SimpleDateFormat format = new SimpleDateFormat(formats);
            time = format.format(date);
        }
        return time;
    }

    /**
     * 加载本地资源图片
     *
     * @param mImageView
     * @param sourceId
     */
    public static void loadingImg(ImageView mImageView, int sourceId) {
        Context context = MyApplication.getAppContext();
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        InputStream is = context.getResources().openRawResource(sourceId);
        mImageView.setImageBitmap(BitmapFactory.decodeStream(is, null, opt));
    }

    public static String autoSplitText(final TextView tv, float width) {
        final String rawText = tv.getText().toString(); //原始文本
        final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
        final float tvWidth = width - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度
        Log.v("width", "tv.width" + width + "left" + tv.getPaddingLeft() + "right" + tv.getPaddingRight());
        //将原始文本按行拆分
        String[] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        sbNewText.append("\n");
                        lineWidth = 0;
                        --cnt;
                    }
                }
            }
            sbNewText.append("\n");
        }

        //把结尾多余的\n去掉
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }

        return sbNewText.toString();
    }

    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 加载本地资源图片
     *
     * @param mImageButton
     * @param sourceId
     */
    public static void loadingImg(ImageButton mImageButton, int sourceId) {
        Context context = MyApplication.getAppContext();
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        InputStream is = context.getResources().openRawResource(sourceId);
        mImageButton.setImageBitmap(BitmapFactory.decodeStream(is, null, opt));
    }

    public static String math_round(float count) {
        float b = (float) (Math.round(count * 100)) / 100;
        return String.valueOf(b);
    }

    /**
     * Photos显示列数
     *
     * @return
     */
    public static int showPhotoColumns(Context context) {
        int column = 2;//默认是两列
        switch (context.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:// 横屏
                if (ScreenUtil.Companion.isPad(context)) {//平板
                    column = 5;
                } else {
                    column = 3;
                }
                break;
            case Configuration.ORIENTATION_PORTRAIT:// 竖屏
                if (ScreenUtil.Companion.isPad(context)) {//平板
                    column = 3;
                } else {
                    column = 2;
                }
                break;
        }
        return column;
    }

    /**
     * Photos中item显示的宽度
     *
     * @param context
     * @return
     */
    public static int getPhotosWidth(Context context) {
        int width = 0;
        switch (context.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:// 横屏
                width = (ScreenUtil.Companion.getMaxWidthOrHeight(context) / showPhotoColumns(context)) - ScreenUtil.Companion.dip2px(context, 40);//每个item左右间距分别是20dp
                break;
            case Configuration.ORIENTATION_PORTRAIT:// 竖屏
                width = (ScreenUtil.Companion.getMinWidthOrHeight(context) / showPhotoColumns(context)) - ScreenUtil.Companion.dip2px(context, 40);
                break;
        }
        return width;
    }

    public static int getItemViewWidth(Context context, int columns, int padding) {
        int width = 0;
        switch (context.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:// 横屏
                width = ((ScreenUtil.Companion.getMaxWidthOrHeight(context) - ScreenUtil.Companion.dip2px(context, padding)) / columns) - ScreenUtil.Companion.dip2px(context,
                        padding);//每个item左右间距分别是20dp
                break;
            case Configuration.ORIENTATION_PORTRAIT:// 竖屏
                width = ((ScreenUtil.Companion.getMinWidthOrHeight(context) - ScreenUtil.Companion.dip2px(context, padding)) / columns) - ScreenUtil.Companion.dip2px(context,
                        padding);
                break;
        }
        return width;
    }

    /**
     * Photos显示列数
     *
     * @param context
     * @return
     */
    public static int showCameraColumns(Context context) {
        int column = 3;//默认是两列
        switch (context.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:// 横屏
                if (ScreenUtil.Companion.isPadDevices(context)) {//平板
                    column = 8;
                } else if (ScreenUtil.Companion.isPhone(context)) {
                    column = 5;
                } else {
                    column = 12;
                }
                break;
            case Configuration.ORIENTATION_PORTRAIT:// 竖屏
                if (ScreenUtil.Companion.isPadDevices(context)) {//平板
                    column = 5;
                } else if (ScreenUtil.Companion.isPhone(context)) {
                    column = 3;
                } else {
                    column = 8;
                }
                break;
        }
        return column;
    }

    public static int getCameraWidth(Context context) {
        int width = 0;
        switch (context.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:// 横屏
                width = (ScreenUtil.Companion.getMaxWidthOrHeight(context) / showCameraColumns(context)) - ScreenUtil.Companion.dip2px(context, 2);//每个item左右间距分别是20dp
                break;
            case Configuration.ORIENTATION_PORTRAIT:// 竖屏
                width = (ScreenUtil.Companion.getMinWidthOrHeight(context) / showCameraColumns(context)) - ScreenUtil.Companion.dip2px(context, 2);
                break;
        }
        return width;
    }

    // Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(String param, boolean paramBoolean) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(param);
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = Uri.fromFile(new File(param));
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    //    打开Activity
    public static void openActivity(Class<?> cls) {
        Intent intent = new Intent(MyApplication.getAppContext(), cls);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getAppContext().startActivity(intent);
    }

    //    打开Activity
    public static void openActivity(Intent intent) {
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getAppContext().startActivity(intent);
    }

    // Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri uri = Uri.parse("file://" + param);
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    public static void vibrate(Context context, long duration) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                0, duration
        };
        vibrator.vibrate(pattern, -1);
    }


    /**
     * param email
     * return
     * 方法说明:判断是不是一个合法的电子邮件地址
     * 方法名称:isEmail
     * 返回值:boolean
     */
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    public static boolean isEmail(CharSequence email) {
        return !isEmpty(email) && emailer.matcher(email).matches();
    }

    /**
     * param input
     * return
     * 方法说明:判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     * 方法名称:isEmpty
     * 返回值:boolean
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


    public static int[] calculatePopWindowPos(Context context, View anchorView, View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = ((screenHeight - anchorLoc[1] - anchorHeight) < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }
}
