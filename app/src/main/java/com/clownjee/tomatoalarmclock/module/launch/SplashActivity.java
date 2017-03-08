package com.clownjee.tomatoalarmclock.module.launch;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.clownjee.tomatoalarmclock.R;
import com.clownjee.tomatoalarmclock.databinding.ActivitySplashBinding;
import com.clownjee.tomatoalarmclock.module.main.MainActivity;


/**
 * Created by Clownjee on 2017/2/7.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binding.tvSlogan, "Alpha", 0f, 1f).setDuration(1000);
        objectAnimator.start();
        objectAnimator.setStartDelay(300);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 2000);
    }
}
