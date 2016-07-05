package com.glima.socialbasepoc.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gustavo on 05/07/16.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected ViewDataBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout());
    }

    public abstract int getLayout();
}
