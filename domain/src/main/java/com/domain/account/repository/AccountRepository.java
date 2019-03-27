package com.domain.account.repository;

import com.domain.account.AccountResult;
import com.domain.account.interactor.SaveAccount;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 22/03/2019.
 * Repository domain for account
 */

public interface AccountRepository {

    /**
     * @return name
     */
    Observable<String> getName();

    /**
     * @name save name
     */
    Observable<AccountResult> saveAccount(SaveAccount.Params params);

    /**
     * @return avatar url
     */
    Observable<String> getAvatarUrl();

    /**
     * @return avatar url
     */
    Observable<AccountResult> getAccount();

    /**
     * @return void
     */
    Observable<Boolean> removeAccount();
}
