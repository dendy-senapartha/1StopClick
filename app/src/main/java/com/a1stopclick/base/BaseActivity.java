package com.a1stopclick.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.a1stopclick.OneStopClickApplication;
import com.a1stopclick.dependencyinjection.components.ApplicationComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Created by dendy-prtha on 25/02/2019.
 * as base Activity. all activity must extend this class!
 */

public abstract class BaseActivity extends AppCompatActivity
        implements PresenterHandler, DisposableHandler {

    private Unbinder unbinder;

    private CompositeDisposable disposables;

    private List<BaseContract.BasePresenterContract> presenterContractList;

    public abstract int getLayout();

    public abstract void init();

    public void init(Bundle savedInstanceState) {
        init();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        dispose();
        disposePresenter();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * get {@link OneStopClickApplication}
     */
    public ApplicationComponent getApplicationComponent() {
        return ((OneStopClickApplication) getApplication()).getApplicationComponent();
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }

        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }

    @Override
    public void dispose() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
            disposables = null;
        }
    }

    @Override
    public void registerPresenter(BaseContract.BasePresenterContract... presenters) {
        if (presenterContractList == null) {
            presenterContractList = new ArrayList<>();
        }

        if (presenters != null && presenters.length > 0) {
            presenterContractList.addAll(Arrays.asList(presenters));
        }
    }

    @Override
    public void disposePresenter() {
        if (presenterContractList != null) {
            for (BaseContract.BasePresenterContract presenterContract : presenterContractList) {
                presenterContract.onDestroy();
            }
        }
    }
}
