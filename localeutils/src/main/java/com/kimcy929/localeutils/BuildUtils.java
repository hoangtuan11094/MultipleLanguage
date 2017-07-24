package com.kimcy929.localeutils;

import android.os.Build;

/**
 * Created by kimcy929 on 7/6/2017.
 */

public class BuildUtils {

    public static boolean isAtLeast24Api() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeast17Api() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }
}
