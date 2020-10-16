package com.btk.loginpage;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.btk.loginpage.databinding.ActivityMainBinding;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends AppCompatActivity implements ButtonClickCallback {

    private final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel mViewmodel;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewmodel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mainBinding.setCallback(this);
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

        if (1 <= mainBinding.etUsername.getText().toString().length()) {
            mViewmodel.loginClick(mainBinding.etUsername.getText().toString(), mainBinding.etPassword.getText().toString())
                    .subscribe(new MaybeObserver<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.v(TAG, "onSubscribe");
                        }

                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            Log.v(TAG, "Onsuccess:" + aBoolean);
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show());
                            WelcomeActivity.start(LoginActivity.this, mainBinding.etUsername.getText().toString(),false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v(TAG, "onError:");
                        }

                        @Override
                        public void onComplete() {
                            Log.v(TAG, "onComplete:");
                        }
                    });
        }
    }


}
