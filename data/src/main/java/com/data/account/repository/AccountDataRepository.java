package com.data.account.repository;

import com.data.Source;
import com.data.account.mapper.AccountRespondMapper;
import com.data.account.repository.source.AccountEntityDataFactory;
import com.domain.account.AccountResult;
import com.domain.account.interactor.SaveAccount;
import com.domain.account.repository.AccountRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 22/03/2019.
 * account Repository
 */

@Singleton
public class AccountDataRepository implements AccountRepository {

    private final AccountEntityDataFactory accountEntityDataFactory;

    private final AccountRespondMapper mapper;

    @Inject
    public AccountDataRepository(AccountEntityDataFactory accountEntityDataFactory, AccountRespondMapper mapper) {
        this.accountEntityDataFactory = accountEntityDataFactory;
        this.mapper = mapper;
    }

    @Override
    public Observable<String> getName() {
        return null;
    }

    @Override
    public Observable<AccountResult> saveAccount(SaveAccount.Params params) {
        return initializedRequest(accountEntityDataFactory.createData(Source.LOCAL)
                .saveAccount(params.email, params.name, params.provider_id, params.provider, params.avatarUrl, params.authorization)
                .map(mapper::transform));
    }

    @Override
    public Observable<String> getAvatarUrl() {
        return null;
    }

    /*Instructs an ObservableSource to pass control to another ObservableSource rather than invoking onError if it encounters an error.
     * */
    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }
}
