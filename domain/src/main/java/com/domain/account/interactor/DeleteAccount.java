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
 * Remove account from secure preferences
 */

public class DeleteAccount extends UseCase<Boolean, Void> {

    private final AccountRepository accountRepository;

    @Inject
    public DeleteAccount(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AccountRepository accountRepository) {
        super(threadExecutor, postExecutionThread);
        this.accountRepository = accountRepository;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable(Void aVoid) {
        return accountRepository.removeAccount();
    }
}
