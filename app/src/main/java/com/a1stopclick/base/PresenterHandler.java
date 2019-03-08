package com.a1stopclick.base;

/*
 * Created by dendy-prtha on 26/02/2019.
 * basic handler for presenter
 */
public interface PresenterHandler {
    void registerPresenter(BaseContract.BasePresenterContract... presenters);

    void disposePresenter();
}
