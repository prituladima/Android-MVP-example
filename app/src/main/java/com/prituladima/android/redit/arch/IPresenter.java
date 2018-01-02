package com.prituladima.android.redit.arch;

/**
 * Created by prituladima on 12/14/17.
 */

public interface IPresenter<T extends IView> {

    void attachView(T t);

    void detachView();

}
