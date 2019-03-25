package com.domain.account.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.account.AccountResult;
import com.domain.account.repository.AccountRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 22/03/2019.
 * Saving account use case
 */

public class SaveAccount extends UseCase<AccountResult, SaveAccount.Params> {
    private final AccountRepository accountRepository;

    @Inject
    public SaveAccount(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                       AccountRepository accountRepository) {
        super(threadExecutor, postExecutionThread);
        this.accountRepository = accountRepository;
    }

    @Override
    protected Observable<AccountResult> buildUseCaseObservable(Params params) {
        return accountRepository.saveAccount(params);
    }

    public static class Params {
        public String email;
        public String name;
        public String provider_id;
        public String provider;
        public String avatarUrl;
        public String authorization;

        private Params(String email, String name, String provider_id, String provider
                , String avatarUrl, String authorization) {
            this.email = email;
            this.name = name;
            this.provider_id = provider_id;
            this.provider = provider;
            this.avatarUrl = avatarUrl;
            this.authorization = authorization;
        }

        public static Params forSaveAccount(String email, String name, String provider_id, String provider
                , String avatarUrl, String authorization) {
            return new Params(email, name, provider_id, provider, avatarUrl, authorization);
        }
    }
}
