package com.domain.user.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.user.LoginResult;
import com.domain.user.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 21/03/2019.
 * social LocalLogin Usecase
 */

public class SocialLogin extends UseCase<LoginResult, SocialLogin.Params> {

    private final UserRepository userRepository;

    @Inject
    public SocialLogin(ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread,
                       UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<LoginResult> buildUseCaseObservable(Params params) {
        return userRepository.SocialLogin(params);
    }

    public static class Params {
        public String email;
        public String xidToken;
        public String provider;

        private Params(String email, String xidToken, String provider) {
            this.email = email;
            this.xidToken = xidToken;
            this.provider = provider;
        }

        public static Params forLogin(String email, String xidToken, String provider) {
            return new Params(email, xidToken, provider);
        }
    }
}
