package com.xiaowu.myself.main.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaowu.myself.R;

import java.io.File;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }

    private void init() {
    }

    public void go(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(Intent.EXTRA_STREAM, "www.baidu.com");  //传输图片或者文件 采用流的方式
        intent.putExtra(Intent.EXTRA_TEXT, "分享分享微博");   //附带的说明信息
        intent.putExtra(Intent.EXTRA_SUBJECT, "标题");
        intent.setType("image/*");   //分享图片
        startActivity(Intent.createChooser(intent,"分享"));
    }

    public static void share(Context context, String title, String type, File file) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(type);
            intent.putExtra(Intent.EXTRA_SUBJECT, title);
            intent.putExtra(Intent.EXTRA_PHONE_NUMBER, title);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent, title));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
