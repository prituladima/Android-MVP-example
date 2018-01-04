package com.prituladima.android.redit.arch;

public interface IPresenter<T extends IView> {

    void attachView(T t);

    void detachView();

}
