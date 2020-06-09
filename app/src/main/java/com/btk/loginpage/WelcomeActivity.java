package com.btk.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.btk.loginpage.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private ActivityWelcomeBinding mWelcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWelcomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_welcome);

        if(getIntent()!=null && getIntent().getExtras()!=null) {
            String user = getIntent().getExtras().getString("username");
            mWelcomeBinding.setUsername(user);
        }
    }

    public static void start(Activity activity,String user,boolean finishCurrent) {
        Intent intent = new Intent();
        intent.setClass(activity,WelcomeActivity.class);
        intent.putExtra("username",user);
        activity.startActivity(intent);
        if(finishCurrent) {
            activity.finish();
        }
    }
}
