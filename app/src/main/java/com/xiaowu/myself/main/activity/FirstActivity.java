package com.xiaowu.myself.main.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaowu.myself.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }

    private void init() {
    }
}
