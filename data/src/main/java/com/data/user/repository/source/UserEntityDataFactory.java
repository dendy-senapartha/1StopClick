package com.data.user.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.user.repository.source.network.NetworkUserEntityData;
import com.data.user.repository.source.network.UserNetwork;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 11/03/2019.
 * User Entity Data Factory
 */

public class UserEntityDataFactory  extends AbstractEntityDataFactory {

    private final UserNetwork userNetwork;

    @Inject
    public UserEntityDataFactory(UserNetwork userNetwork) {
        this.userNetwork = userNetwork;
    }


    @Override
    public UserEntityData createData(String source) {
        return new NetworkUserEntityData(userNetwork);
    }
}
