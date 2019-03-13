package com.data;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * Created by dendy-prtha on 27/02/2019.
 * Data source representation
 * Choices of data source
 * local = persistence data (example : shared prefference, local db, etc)
 * network = from BE
 * mock = mock data in app
 */

@StringDef({DataSource.LOCAL, DataSource.NETWORK, DataSource.MOCK})
@Retention(RetentionPolicy.SOURCE)
public @interface DataSource {
    String LOCAL = "local";

    String NETWORK = "network";

    String MOCK = "mock";
}
