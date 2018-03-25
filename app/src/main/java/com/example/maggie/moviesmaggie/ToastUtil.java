package com.example.maggie.moviesmaggie;

import android.content.Context;
import android.widget.Toast;

import com.example.maggie.moviesmaggie.dagger.scopes.ApplicationContext;

public class ToastUtil {
    private Context mContext;
    private Toast mToast;

    public ToastUtil(@ApplicationContext Context context) {
        mContext = context;
    }

    public void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }
}
