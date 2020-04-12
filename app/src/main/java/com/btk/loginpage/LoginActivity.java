package com.btk.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.btk.loginpage.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity implements ButtonClickCallback {

    private final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel mViewmodel;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainBinding.setCallback(this);
        mViewmodel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void onLoginClick() {

    }
}
