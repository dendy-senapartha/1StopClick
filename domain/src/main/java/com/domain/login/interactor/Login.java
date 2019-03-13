package com.domain.login.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.login.LoginRequest;
import com.domain.login.LoginResult;
import com.domain.login.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/03/2019.
 * Login interactor
 */

public class Login extends UseCase<LoginResult, LoginRequest> {

    private final UserRepository userRepository;

    @Inject
    public Login(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<LoginResult> buildUseCaseObservable(LoginRequest loginRequest) {
        return userRepository.Login(loginRequest);
    }
}
