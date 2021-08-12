package com.btk.loginpage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.btk.loginpage.databinding.ActivityAppSettingBinding;

public class AppSettingActivity extends AppCompatActivity {

    private final String TAG = AppSettingActivity.class.getSimpleName();
    private final String PREF_THEME_KEY = "theme_key";
    private ActivityAppSettingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(AppSettingActivity.this, R.layout.activity_app_setting);

        mBinding.themegroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.theme_dark:
                        setAppTheme(AppCompatDelegate.MODE_NIGHT_YES);
                        storePreferredTheme("themeDark");
                        break;
                    case R.id.theme_light:
                        setAppTheme(AppCompatDelegate.MODE_NIGHT_NO);
                        storePreferredTheme("themeLight");
                        break;
                    case R.id.theme_default:
                        setAppTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        storePreferredTheme("themeSystemDefault");
                        break;
                }
            }
        });

        String currentTheme = getCurrentTheme();

        LoggerUtils.Logi(TAG, "Theme:" + currentTheme);

        if (currentTheme.equalsIgnoreCase("themeLight")) {
            mBinding.themeDark.setChecked(false);
            mBinding.themeLight.setChecked(true);
            mBinding.themeDefault.setChecked(false);
        } else if (currentTheme.equalsIgnoreCase("themeDark")) {
            mBinding.themeDark.setChecked(true);
            mBinding.themeLight.setChecked(false);
            mBinding.themeDefault.setChecked(false);
        } else if (currentTheme.equalsIgnoreCase("themeSystemDefault")) {
            mBinding.themeDark.setChecked(false);
            mBinding.themeLight.setChecked(false);
            mBinding.themeDefault.setChecked(true);
        }
    }

    private void setAppTheme(int theme) {
        AppCompatDelegate.setDefaultNightMode(theme);
    }

    private void storePreferredTheme(String theme) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AppSettingActivity.this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PREF_THEME_KEY, theme);
        editor.apply();
    }

    private String getCurrentTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AppSettingActivity.this);
        return preferences.getString(PREF_THEME_KEY, "themeLight");
    }


}
