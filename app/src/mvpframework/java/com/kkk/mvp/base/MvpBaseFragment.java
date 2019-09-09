package com.kkk.mvp.base;

import android.app.Activity;
import android.content.Context;


/*
 * created by taofu on 2019-08-26
 **/
@SuppressWarnings("ALL")
public abstract class MvpBaseFragment< P extends IBasePresenter> extends BaseFragment implements IBaseView<P> {

    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mPresenter = createPresenter();

        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }


    @Override
    public Activity getActivityObj() {
        return getActivity();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

}
