package com.btk.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.btk.loginpage.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private ActivityWelcomeBinding mWelcomeBinding;

    public static void start(Activity activity, String user, boolean finishCurrent) {
        Intent intent = new Intent();
        intent.setClass(activity, WelcomeActivity.class);
        intent.putExtra("username", user);
        activity.startActivity(intent);
        if (finishCurrent) {
            activity.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.txt_welcome));
        }


        if (getIntent() != null && getIntent().getExtras() != null) {
            String user = getIntent().getExtras().getString("username");
            mWelcomeBinding.setUsername(user);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_setting:
                openAppSettings();
                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openAppSettings() {
        Intent intent = new Intent();
        intent.setClass(this, AppSettingActivity.class);
        startActivity(intent);
    }
}
