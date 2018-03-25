package com.example.maggie.moviesmaggie.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maggie.moviesmaggie.BaseActivity;
import com.example.maggie.moviesmaggie.R;
import com.example.maggie.moviesmaggie.ToastUtil;
import com.example.maggie.moviesmaggie.dagger.component.ActivityComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class LoadingFragment extends Fragment {
    @Inject
    ToastUtil toastUtil;

    private Unbinder unbinder;
    private View view;

    //is current fragment visible
    protected boolean isVisible;
    //is view initlize
    protected boolean isInitView;
    //is data load
    protected boolean isLoadData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDagger(((BaseActivity) getActivity()).activityComponent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            loadData();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isInitView = true;
        if (getUserVisibleHint()) {
            loadData();
        }
    }

    protected void loadData() {
        if (!isInitView || !isVisible || isLoadData) {
            return;
        }
        initData();
        isLoadData = true;
    }

    public View getView() {
        return view;
    }

    /**
     * override when need Dagger2
     */
    protected void injectDagger(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    /**
     * showToast
     */
    protected void showToast(String text) {
        toastUtil.showToast(text);
    }

    /**
     * load the layout file
     */
    protected abstract int getFragmentLayout();

    /**
     * get data refresh ui
     */
    protected abstract void initData();
}
