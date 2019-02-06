package org.digitalcampus.oppia.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.Customs.CustomViewPager;
import org.digitalcampus.oppia.adapter.MyPagerAdapter;
import org.digitalcampus.oppia.application.MobileLearning;
import org.digitalcampus.oppia.fragments.CoursesFragment;
import org.digitalcampus.oppia.fragments.HomeFragment;
import org.digitalcampus.oppia.model.Activity;

public class OppiaMainActivity extends AppCompatActivity implements HomeFragment.OnCardInteraction, CoursesFragment.OnBackButtonClick , CourseIndexActivity.NavigationBarStatus {

    AHBottomNavigation bottomNavigation;
    CustomViewPager mViewPager;
    MyPagerAdapter mPageAdapter;
    public static Context mContext;
    boolean hide = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.oppia_main_activity);
        mContext = this;
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        mViewPager = (CustomViewPager) findViewById(R.id.viewpager);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_name_1, R.drawable.home_icon, R.color.tab_active);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_name_2, R.drawable.search, R.color.tab_active);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_name_3, R.drawable.courses_icon, R.color.tab_active);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(OppiaMainActivity.this, R.color.white));

        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(OppiaMainActivity.this, R.color.white));
        bottomNavigation.setAccentColor(ContextCompat.getColor(OppiaMainActivity.this, R.color.tab_active));
        bottomNavigation.setInactiveColor(ContextCompat.getColor(OppiaMainActivity.this, R.color.tab_inactive));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);
        mPageAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setPagingEnabled(false);
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.hideBottomNavigation();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            bottomNavigation.setCurrentItem(position);
                if (position == 0) {
                    bottomNavigation.hideBottomNavigation();
                }
                else {
                    bottomNavigation.restoreBottomNavigation();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {



            }
        });
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                mViewPager.setCurrentItem(position,false);
                return true;
            }
        });
    }
    private void initializeDagger() {
        MobileLearning app = (MobileLearning) getApplication();
        app.getComponent().inject(this);
    }

    @Override
    public void navigateToCourses() {
        bottomNavigation.setCurrentItem(2);
        bottomNavigation.restoreBottomNavigation();
        mViewPager.setCurrentItem(2);
    }

    @Override
    public void navigateToSettings() {

    }

    @Override
    public void navigateToHomeScreen() {
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.hideBottomNavigation();
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void showHide() {

        if (hide) {
            hide = false;
            bottomNavigation.restoreBottomNavigation();
        }
        else {
            hide = true;
            bottomNavigation.hideBottomNavigation();
        }
    }
    public static Context getContext ( ) {
        return mContext;
    }
}
