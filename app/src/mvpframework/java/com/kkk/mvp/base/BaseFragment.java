package com.kkk.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;
import com.jy.coolweather.R;
import com.trello.rxlifecycle2.components.support.RxFragment;



/*
 * created by taofu on 2019-08-26
 **/
public abstract class BaseFragment extends RxFragment {



    protected BaseActivity mBaseActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) activity;
        }
    }

    @Nullable
    @Override
    public  View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(getLayoutId(),container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View root);

    protected <T extends View>  T onViewCreatedBind(int id, View.OnClickListener listener){
        T t = getView().findViewById(id);
        t.setOnClickListener(listener);
        return t;
    }

    protected BaseFragment addFragment(FragmentManager manager, Class<? extends BaseFragment> aClass, int containerId, Bundle args) {
        if (mBaseActivity != null) {
            return mBaseActivity.addFragment(manager, aClass, containerId, args);
        }

        return null;
    }

    private ViewGroup getParentViewGroup(){
        ViewGroup viewGroup = (ViewGroup) getView().getParent();
        return viewGroup;
    }




    protected  void back(){
        getFragmentManager().popBackStack();
    }

    public int enter() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_right_in;
    }

    public int exit() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_left_out;
    }

    public int popEnter() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_left_in;
    }

    public int popExit() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_right_out;
    }

    public boolean isNeedAddToBackStack() {
        return true;
    }

    public boolean isNeedAnimation() {
        return true;
    }

    protected void showToast(@StringRes int id){
        Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@NonNull String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
