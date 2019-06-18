package com.data.account.repository.source;
import com.data.account.repository.source.preference.response.AccountResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 22/03/2019.
 * Account Data action interface. this represent what action can we do toward account Account data
 */

public interface AccountEntityData {
    Observable<Boolean> init(String key);

    Observable<AccountResponse> saveAccount(String uid, String email, String name, String provider_id, String provider, String avatarUrl, String authorization);

    Observable<AccountResponse> getAccount();

    Observable<Boolean> removeAccount();
}
