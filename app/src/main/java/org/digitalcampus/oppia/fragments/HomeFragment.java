package org.digitalcampus.oppia.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.activity.CourseIndexActivity;
import org.digitalcampus.oppia.activity.OppiaMainActivity;
import org.digitalcampus.oppia.activity.OppiaMobileActivity;
import org.digitalcampus.oppia.activity.PrefsActivity;

public class HomeFragment extends Fragment {
    CardView courses;
    CardView settings;
    OnCardInteraction onCardInteraction;
    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        onCardInteraction = (OppiaMainActivity) getActivity();
        courses = (CardView) rootView.findViewById(R.id.courses);
        settings = (CardView) rootView.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onCardInteraction.navigateToSettings();
                startActivity(new Intent(getActivity(),PrefsActivity.class));
            }
        });
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCardInteraction.navigateToCourses();
            }
        });
        return rootView;
    }
    public  interface OnCardInteraction{
        void navigateToCourses();
        void navigateToSettings();
    }
}
