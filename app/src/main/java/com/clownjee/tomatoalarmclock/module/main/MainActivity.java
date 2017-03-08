package com.clownjee.tomatoalarmclock.module.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.clownjee.tomatoalarmclock.R;
import com.clownjee.tomatoalarmclock.base.BaseActivity;
import com.clownjee.tomatoalarmclock.entity.TabEntity;
import com.clownjee.tomatoalarmclock.module.count.CountFragment;
import com.clownjee.tomatoalarmclock.module.time.TimeFragment;
import com.clownjee.tomatoalarmclock.util.ToastUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by Clownjee on 2017/2/6.
 */
public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"计时", "统计"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private ViewPager mViewPager;

    private CommonTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_settings) {
                    Toast.makeText(MainActivity.this, R.string.menu_settings, Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_about) {
                    Toast.makeText(MainActivity.this, R.string.menu_about, Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });


        mFragments.add(new TimeFragment());
        mFragments.add(new CountFragment());


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout = (CommonTabLayout) findViewById(R.id.tab);

        init();
    }

    private void init() {
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private int count = 0;

    private long lastTime = System.currentTimeMillis();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ++count;
            long off = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            if (count == 2 && off <= 2000) {
                finish();
            } else {
                count = 1;
                ToastUtil.showShort(getApplicationContext(), "再按一次退出程序");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
