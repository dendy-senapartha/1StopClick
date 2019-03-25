package com.data.account.repository.source.preference;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.data.BuildConfig;
import com.data.SecureableSharedPreferences;
import com.data.Serializer;
import com.data.account.AccountEntity;
import com.data.account.repository.source.preference.response.AccountResponse;
import com.data.exception.UnInitializedSecuredPreferencesException;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 22/03/2019.
 * Secure account preferences repository action
 */

@Singleton
public class AccountPreference {

    private static final String ACCOUNT_PREFERENCE = "AccountPreference" +
            BuildConfig.FLAVOR;

    private final Context context;

    private final Serializer serializer;

    private SecureableSharedPreferences secureableSharedPreferences;

    private static class Key {

        private static final String ACCOUNT = "account";
    }

    @Inject
    public AccountPreference(Context context, Serializer serializer) {
        this.context = context;
        this.serializer = serializer;
    }

    public void init(String key) {
        secureableSharedPreferences = new SecureableSharedPreferences.Builder(context)
                .setPreferenceGroup(ACCOUNT_PREFERENCE)
                .setSerializerFacade(serializer)
                .setPassword(key)
                .build();
    }

    private void initChecking() throws UnInitializedSecuredPreferencesException {
        if (secureableSharedPreferences == null) {
            throw new UnInitializedSecuredPreferencesException();
        }
    }

    public AccountResponse saveAccount(String email, String name, String provider_id, String provider, String avatarUrl,
                                       String authorization)
            throws UnInitializedSecuredPreferencesException {
        initChecking();
        //get available notes list first
        JSONObject account = secureableSharedPreferences.getObject(Key.ACCOUNT, JSONObject.class);

        boolean noteIsAvailable = false;
        //parse it
        AccountEntity accountEntity = null;
        if (account != null) {
            noteIsAvailable = true;
            TypeReference<AccountEntity> typeRef = new TypeReference<AccountEntity>() {
            };
            accountEntity = JSON.parseObject(account.toString(), typeRef);
            accountEntity.setEmail(email);
            accountEntity.setName(name);
            accountEntity.setProvider_id(provider_id);
            accountEntity.setProvider(provider);
            accountEntity.setAvatarUrl(avatarUrl);
            accountEntity.setAuthorization(authorization);
        }

        //if not, then add it
        if (!noteIsAvailable) {
            accountEntity = new AccountEntity(email, name, provider_id, provider, avatarUrl, authorization);
        }
        //save again
        secureableSharedPreferences.saveData(Key.ACCOUNT, accountEntity);
        return new AccountResponse(accountEntity.getEmail(), accountEntity.getName(), accountEntity.getProvider_id(),
                accountEntity.getProvider(), accountEntity.getAvatarUrl(), accountEntity.getAuthorization());
    }

    public boolean getNote(String mId) throws UnInitializedSecuredPreferencesException {
        initChecking();

        return false;
    }
}
