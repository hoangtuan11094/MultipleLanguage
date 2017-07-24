package com.example.kimcy929.multiplelanguagesample;

import android.app.Application;
import android.preference.PreferenceManager;

import com.kimcy929.localeutils.BuildUtils;
import com.kimcy929.localeutils.LocaleUtils;

import java.util.Locale;

/**
 * Created by kimcy929 on 7/24/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //You can handle find default language here before getting language in settings but here I don't handle

        String location = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("LANG", "en");

        Locale locale = new Locale(location);

        LocaleUtils.setLocale(locale);

        if (!BuildUtils.isAtLeast24Api()) {
            LocaleUtils.updateConfig(this, getBaseContext().getResources().getConfiguration());
        }
    }
}
