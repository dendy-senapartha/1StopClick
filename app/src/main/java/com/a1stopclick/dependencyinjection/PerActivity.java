package com.a1stopclick.dependencyinjection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.*;

/*
 * Created by dendy-prtha on 26/02/2019.
 * TODO: Add a class header comment!
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}