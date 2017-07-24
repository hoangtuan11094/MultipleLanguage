package com.example.kimcy929.multiplelanguagesample;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.kimcy929.localeutils.LocaleUtils;

import java.lang.ref.WeakReference;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button btnChangeLanguage = (Button) findViewById(R.id.btnChangeLanguage);
        btnChangeLanguage.setText(R.string.change_language);
        btnChangeLanguage.setOnClickListener(view -> {
            showDialogChangeLanguage();
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void showDialogChangeLanguage() {
        new ChangeLanguageTask(this).execute();
    }

    private static class ChangeLanguageTask extends AsyncTask<Void, Void, Void> {

        private int checkedItem = 0;
        private String[] arrayLanguageCode;

        private WeakReference<MainActivity> mActivity;

        public ChangeLanguageTask(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (mActivity.get() != null) {
                arrayLanguageCode = mActivity.get().getResources().getStringArray(R.array.array_language_code);
                String location = PreferenceManager.getDefaultSharedPreferences(mActivity.get().getApplicationContext()).getString("LANG", "en");

                for (int i = 0; i < arrayLanguageCode.length; i++) {
                    if (location.equals(arrayLanguageCode[i])) {
                        checkedItem = i;
                        break;
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity.get());
            builder.setTitle(R.string.language)
                    .setSingleChoiceItems(
                            mActivity.get().getResources().getStringArray(R.array.array_language_name),
                            checkedItem, (dialogInterface, i) -> {
                        changeLanguage(arrayLanguageCode[i]);
                        dialogInterface.dismiss();
                    })
                    .setPositiveButton(android.R.string.cancel, null)
                    .show();
        }

        private void changeLanguage(String language) {
            if (mActivity.get() != null) {
                if (PreferenceManager
                        .getDefaultSharedPreferences(mActivity.get().getApplicationContext())
                        .edit().putString("LANG", language)
                        .commit()) {
                    LocaleUtils.setLocale(new Locale(language));
                    mActivity.get().recreate();
                }
            }
        }
    }
}
