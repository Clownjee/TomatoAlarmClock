package com.ghizzoniandrea.tomatoalarmclock.module.option;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ghizzoniandrea.tomatoalarmclock.R;
import com.ghizzoniandrea.tomatoalarmclock.databinding.ActivityOptionBinding;

/**
 * Created by ghizzoniandrea on 2017/3/6.
 */
public class OptionActivity extends AppCompatActivity {

    private ActivityOptionBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_option);
    }
}
