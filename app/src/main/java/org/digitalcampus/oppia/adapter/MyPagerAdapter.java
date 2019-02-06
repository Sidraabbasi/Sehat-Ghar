package org.digitalcampus.oppia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.digitalcampus.oppia.fragments.CoursesFragment;
import org.digitalcampus.oppia.fragments.HomeFragment;
import org.digitalcampus.oppia.fragments.SearchFragment;
import org.digitalcampus.oppia.model.Course;

public class MyPagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS = 3;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return SearchFragment.newInstance();
            case 2:
                return CoursesFragment.newInstance();
            default:
                return null;

        }

    }
}