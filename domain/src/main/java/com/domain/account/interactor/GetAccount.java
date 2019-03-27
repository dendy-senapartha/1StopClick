package com.domain.account.interactor;


import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.account.AccountResult;
import com.domain.account.repository.AccountRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 25/03/2019.
 * Usecase to retrieve account
 */

public class GetAccount extends UseCase<AccountResult, Void> {
    private final AccountRepository accountRepository;

    @Inject
    public GetAccount(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AccountRepository accountRepository) {
        super(threadExecutor, postExecutionThread);
        this.accountRepository = accountRepository;
    }

    @Override
    protected Observable<AccountResult> buildUseCaseObservable(Void aVoid) {
        return accountRepository.getAccount();
    }
}
