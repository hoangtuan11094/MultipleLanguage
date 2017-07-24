package com.example.kimcy929.multiplelanguagesample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.kimcy929.localeutils.BuildUtils;
import com.kimcy929.localeutils.ContextWrapper;
import com.kimcy929.localeutils.LocaleUtils;

/**
 * Created by kimcy929 on 7/24/2017.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    public BaseActivity() {
        if (!BuildUtils.isAtLeast24Api()) {
            LocaleUtils.updateConfig(this);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        if (BuildUtils.isAtLeast24Api()) {
            super.attachBaseContext(ContextWrapper.wrap(newBase, LocaleUtils.getsLocale()));
        } else {
            super.attachBaseContext(newBase);
        }
    }

}
