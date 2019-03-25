package com.data.account.repository.source;
import com.data.AbstractEntityDataFactory;
import com.data.account.repository.source.preference.AccountPreference;
import com.data.account.repository.source.preference.PreferenceAccountEntityData;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 22/03/2019.
 * Account Entity Data Factory
 */

@Singleton
public class AccountEntityDataFactory extends AbstractEntityDataFactory<AccountEntityData> {

    private final AccountPreference accountPreference;

    @Inject
    public  AccountEntityDataFactory(AccountPreference accountPreference)
    {
        this.accountPreference = accountPreference;
    }

    @Override
    public AccountEntityData createData(String source) {
        return new PreferenceAccountEntityData(accountPreference);
    }
}
