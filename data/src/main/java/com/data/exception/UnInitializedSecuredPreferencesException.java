
package com.data.exception;

/**
 * Created by dendy-prtha on 25/03/2019.
 * Exception to handle uninit Secure preference
 */
public class UnInitializedSecuredPreferencesException extends Exception {

    public UnInitializedSecuredPreferencesException() {
        super("Secured Preferences has not been initialized");
    }
}