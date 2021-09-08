package com.btk.loginpage;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.btk.loginpage.databinding.ActivityMainBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements ButtonClickCallback {

    private final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel mLoginViewModel;
    private ActivityMainBinding mBinding;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mBinding.setCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    @Override
    public void onLoginClick() {
        if (1 <= mBinding.etUsername.getText().toString().length()) {
            Disposable disposable = mLoginViewModel.loginClick(mBinding.etUsername.getText().toString(), mBinding.etPassword.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable1 -> {
                        LoggerUtils.Loge(TAG, "doOnSubscribe");
                    })
                    .subscribe(result -> {
                                onLogin(result);
                            },
                            Throwable -> {
                                LoggerUtils.Loge(TAG, "onLogin error");
                                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show());

                            });
            mCompositeDisposable.add(disposable);
        }
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
