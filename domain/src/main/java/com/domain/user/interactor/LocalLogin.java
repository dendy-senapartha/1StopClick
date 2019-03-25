package com.domain.user.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.user.LoginResult;
import com.domain.user.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/03/2019.
 * LocalLogin interactor
 */

public class LocalLogin extends UseCase<LoginResult, LocalLogin.Params> {

    private final UserRepository userRepository;

    @Inject
    public LocalLogin(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<LoginResult> buildUseCaseObservable(Params loginRequest) {
        return userRepository.LocalLogin(loginRequest);
    }

    public static class Params {
        public String email;
        public String password;

        private Params(String email, String password)
        {
            this.email = email;
            this.password = password;
        }

        public static Params forLogin(String email, String password) {
            return new Params(email, password);
        }
    }
}
