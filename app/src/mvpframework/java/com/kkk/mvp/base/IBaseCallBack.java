package com.kkk.mvp.base;

/*
 * created by taofu on 2019-08-26
 **/
public interface IBaseCallBack<T> {




    void onSuccess(T data);

    void onFail(String e);

}
