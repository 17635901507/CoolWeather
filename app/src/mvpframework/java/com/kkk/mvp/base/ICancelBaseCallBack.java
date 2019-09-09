package com.kkk.mvp.base;

import io.reactivex.disposables.Disposable;

/*
 * created by taofu on 2019-09-05
 **/
public interface ICancelBaseCallBack<T> extends IBaseCallBack<T> {

    void onStart(Disposable disposable);
}
