package com.clownjee.tomatoalarmclock.module.time;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clownjee.tomatoalarmclock.R;
import com.clownjee.tomatoalarmclock.common.Constants;
import com.clownjee.tomatoalarmclock.databinding.FragmentTimeBinding;
import com.clownjee.tomatoalarmclock.db.DBUtil;
import com.clownjee.tomatoalarmclock.entity.TomatoAlarmClock;
import com.clownjee.tomatoalarmclock.util.ToastUtil;

/**
 * Created by Clownjee on 2017/2/6.
 */
public class TimeFragment extends Fragment {

    private int index = -1;

    private FragmentTimeBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_time, container, false);
        mBinding.setPresenter(new Presenter());
        return mBinding.getRoot();
    }

    public class Presenter {
        public void startOnClick(View view) {
            if (index < 0) {
                ToastUtil.showShort(getContext(), "请选择一个任务");
                return;
            }
            Intent intent = new Intent();
            intent.setClass(getActivity(), TimerActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
            // 进行数据保存操作
            TomatoAlarmClock tomato = new TomatoAlarmClock(null, System.currentTimeMillis(), null, null, 25, 0, System.currentTimeMillis(), index);
            long id = new DBUtil(getContext()).insert(tomato);
            SharedPreferences preferences = getContext().getSharedPreferences(
                    Constants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(Constants.CURRENT_TASK_ID, id);
            editor.apply();
        }

        public void workOnClick(View view) {
            hideViews();
            mBinding.viewBelowWork.setVisibility(View.VISIBLE);
            mBinding.viewBelowWork.setBackgroundColor(Color.parseColor("#5DC1FF"));
            mBinding.view1.setBackgroundColor(Color.parseColor("#5DC1FF"));
            mBinding.tvWork.setTextColor(Color.parseColor("#333333"));
            index = 0;
        }

        public void studyOnClick(View view) {
            hideViews();
            mBinding.viewBelowStudy.setVisibility(View.VISIBLE);
            mBinding.viewBelowStudy.setBackgroundColor(Color.parseColor("#72D72A"));
            mBinding.view2.setBackgroundColor(Color.parseColor("#72D72A"));
            mBinding.tvStudy.setTextColor(Color.parseColor("#333333"));
            index = 1;
        }

        public void thinkOnClick(View view) {
            hideViews();
            mBinding.viewBelowThink.setVisibility(View.VISIBLE);
            mBinding.viewBelowThink.setBackgroundColor(Color.parseColor("#FFA517"));
            mBinding.view3.setBackgroundColor(Color.parseColor("#FFA517"));
            mBinding.tvThink.setTextColor(Color.parseColor("#333333"));
            index = 2;
        }

        public void writeOnClick(View view) {
            hideViews();
            mBinding.viewBelowWrite.setVisibility(View.VISIBLE);
            mBinding.viewBelowWrite.setBackgroundColor(Color.parseColor("#16DBD8"));
            mBinding.tvWrite.setTextColor(Color.parseColor("#333333"));
            index = 3;
        }

        public void sportOnClick(View view) {
            hideViews();
            mBinding.viewBelowSport.setVisibility(View.VISIBLE);
            mBinding.viewBelowSport.setBackgroundColor(Color.parseColor("#968ED4"));
            mBinding.tvSport.setTextColor(Color.parseColor("#333333"));
            index = 4;
        }

        public void readOnClick(View view) {
            hideViews();
            mBinding.viewBelowRead.setVisibility(View.VISIBLE);
            mBinding.viewBelowRead.setBackgroundColor(Color.parseColor("#E35252"));
            mBinding.tvRead.setTextColor(Color.parseColor("#333333"));
            index = 5;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void hideViews() {
        mBinding.viewBelowWork.setVisibility(View.GONE);
        mBinding.viewBelowStudy.setVisibility(View.GONE);
        mBinding.viewBelowThink.setVisibility(View.GONE);
        mBinding.viewBelowWrite.setVisibility(View.GONE);
        mBinding.viewBelowSport.setVisibility(View.GONE);
        mBinding.viewBelowRead.setVisibility(View.GONE);
        mBinding.view1.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray));
        mBinding.view2.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray));
        mBinding.view3.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray));
        mBinding.tvWork.setTextColor(Color.parseColor("#999999"));
        mBinding.tvStudy.setTextColor(Color.parseColor("#999999"));
        mBinding.tvThink.setTextColor(Color.parseColor("#999999"));
        mBinding.tvWrite.setTextColor(Color.parseColor("#999999"));
        mBinding.tvSport.setTextColor(Color.parseColor("#999999"));
        mBinding.tvRead.setTextColor(Color.parseColor("#999999"));
    }
}
