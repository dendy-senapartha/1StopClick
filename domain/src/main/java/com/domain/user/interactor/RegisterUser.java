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
        public String username;
        public String password;
        public String firstName;
        public String lastName;
        public String dob;
        public String phone;
        public String profilePhoto;

        private Params(String username, String password, String firstName,
                       String lastName, String dob, String phone, String profilePhoto) {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;
            this.phone = phone;
            this.profilePhoto = profilePhoto;
        }

        public static Params forRegisterUser(String username, String password, String firstName,
                                             String lastName, String dob, String phone, String profilePhoto) {
            return new Params(username, password, firstName, lastName, dob, phone, profilePhoto);
        }
    }
}
