package com.btk.loginpage;

import androidx.lifecycle.ViewModel;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

public class LoginViewModel extends ViewModel {

    public Maybe<Boolean> loginClick(final String userName, final String password) {
        return Maybe.create(emitter -> LoginViewModel.this.validateCredentials(emitter, userName, password));
    }

    private void validateCredentials(MaybeEmitter<Boolean> emitter, String username, String password) {
        if (validateUserName(username)) {
            emitter.onSuccess(true);
        } else {
            emitter.onError(new Throwable("Authentication failed"));
        }
    }

    private boolean validateUserName(String str) {
        return !str.matches("[0-9]+");
    }
}
