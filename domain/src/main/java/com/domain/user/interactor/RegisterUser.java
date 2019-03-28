package com.domain.user.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.user.repository.UserRepository;
import com.domain.user.UserRegistrationResult;

import javax.inject.Inject;

import io.reactivex.Observable;
/*
 * Created by dendy-prtha on 13/03/2019.
 * interactor for register user
 */

public class RegisterUser extends UseCase<UserRegistrationResult, RegisterUser.Params> {

    private final UserRepository userRepository;

    @Inject
    public RegisterUser(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                        UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<UserRegistrationResult> buildUseCaseObservable(Params params) {
        return userRepository.UserRegistration(params);
    }

    public static class Params {
        public String email;
        public Boolean emailVerified;
        public String password;
        public String provider;
        public String providerId;
        public String name;
        public String dob;
        public String phone;
        public String imageUrl;

        private Params(String email, Boolean emailVerified, String password, String provider,
                       String providerId, String name, String dob, String phone, String imageUrl) {
            this.email = email;
            this.emailVerified = emailVerified;
            this.password = password;
            this.provider = provider;
            this.providerId = providerId;
            this.name = name;
            this.dob = dob;
            this.phone = phone;
            this.imageUrl = imageUrl;
        }

        public static Params forRegisterUser(String email, Boolean emailVerified, String password, String provider,
                                             String providerId, String name, String dob, String phone, String imageUrl) {
            return new Params(email, emailVerified, password, provider, providerId, name, dob, phone, imageUrl);
        }
    }
}
