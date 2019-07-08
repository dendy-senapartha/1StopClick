package com.data.account.repository.source.preference;

import com.data.account.AccountEntity;
import com.data.account.repository.source.AccountEntityData;
import com.data.account.repository.source.preference.response.AccountResponse;
import com.data.exception.UnInitializedSecuredPreferencesException;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/*
 * Created by dendy-prtha on 22/03/2019.
 * account entity Data preference implementation
 */

public class PreferenceAccountEntityData implements AccountEntityData {

    private final AccountPreference accountPreference;

    public PreferenceAccountEntityData(AccountPreference accountPreference) {
        this.accountPreference = accountPreference;
    }

    @Override
    public Observable<Boolean> init(String key) {
        return initObservable(() -> {
            accountPreference.init(key);
            return true;
        });
    }

    @Override
    public Observable<AccountResponse> saveAccount(String uid, String email, String name, String provider_id, String provider, String avatarUrl, String authorization) {
        return initObservable(() -> {
            return accountPreference.saveAccount(uid, email, name, provider_id, provider, avatarUrl, authorization);
        });
    }

    @Override
    public Observable<AccountResponse> getAccount() {
        return initObservable(() -> {
            return accountPreference.getAccount();
        });
    }

    @Override
    public Observable<Boolean> removeAccount() {
        return initObservable(() -> {
            return accountPreference.removeAccount();
        });
    }

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return initializedRequest(Observable.fromCallable(callable));
    }

    private <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(initAndRetry(observable));
    }

    private <T> Function<Throwable, ? extends Observable<? extends T>>
    initAndRetry(final Observable<T> resumedObservable) {
        return (Function<Throwable, Observable<? extends T>>) throwable -> {
            if (throwable instanceof UnInitializedSecuredPreferencesException) {
                String userId = "MockUserID";
                //if (!TextUtils.isEmpty(orderId)) {
                return init(userId).concatMap(aBoolean -> resumedObservable);
                //}
            }
            return Observable.error(throwable);
        };
    }
}
