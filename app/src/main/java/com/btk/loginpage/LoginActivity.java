package com.btk.loginpage;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.btk.loginpage.databinding.ActivityMainBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements ButtonClickCallback {

    private final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel mLoginViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mBinding.setCallback(this);
    }

    @Override
    public void onLoginClick() {

       /* if(1<= mainBinding.etUsername.getText().toString().length()) {
            mViewmodel.loginClick(mainBinding.etUsername.getText().toString(), mainBinding.etPassword.getText().toString()).subscribeOn(Schedulers.io())
                    .subscribe(new DisposableMaybeObserver<Boolean>() {
                        @Override
                        public void onSuccess(Boolean o) {
                            Log.v(TAG, "onSuccess :" + o);
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show());
                            WelcomeActivity.start(LoginActivity.this, mainBinding.etUsername.getText().toString(),true);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v(TAG, "onError :");
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show());
                        }

                        @Override
                        public void onComplete() {
                            Log.v(TAG, "onComplete :");
                        }
                    });
        }*/

        if (1 <= mBinding.etUsername.getText().toString().length()) {
            mLoginViewModel.loginClick(mBinding.etUsername.getText().toString(), mBinding.etPassword.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                onLogin(result);
                            },
                            Throwable -> {
                                LoggerUtils.Loge(TAG, "onLogin error");
                                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show());

                            });
        }

       /* if (1 <= mBinding.etUsername.getText().toString().length()) {
            mLoginViewModel.loginClick(mBinding.etUsername.getText().toString(), mBinding.etPassword.getText().toString())
                    .subscribe(new MaybeObserver<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            LoggerUtils.Logv(TAG, "onSubscribe");
                        }

                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            LoggerUtils.Logv(TAG, "Onsuccess:" + aBoolean);
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show());
                            WelcomeActivity.start(LoginActivity.this, mBinding.etUsername.getText().toString(), false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v(TAG, "onError:");
                        }

                        @Override
                        public void onComplete() {
                            LoggerUtils.Logv(TAG, "onComplete:");
                        }
                    });
        }*/
    }

    private void onLogin(boolean result) {
        if (result) {
            LoggerUtils.Logv(TAG, "OnSuccess:" + result);
            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show());
            WelcomeActivity.start(LoginActivity.this, mBinding.etUsername.getText().toString(), false);
            mBinding.etUsername.setText("");
        } else {
            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show());
        }
    }
}
