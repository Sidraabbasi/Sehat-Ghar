package org.digitalcampus.oppia.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.adapter.ActionPointLayoutAdapter;
import org.digitalcampus.oppia.adapter.CourseVideoLayoutAdapter;
import org.digitalcampus.oppia.model.Activity;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.model.Points;
import org.digitalcampus.oppia.utils.resources.JSInterfaceForResourceImages;
import org.digitalcampus.oppia.utils.storage.FileUtils;
import org.digitalcampus.oppia.utils.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ActionPointsListActivity extends AppActivity implements ActionPointLayoutAdapter.OnItemInteraction {

    WebView webView;
    ArrayList<Activity> actionPoints;
    private SharedPreferences prefs;
    RecyclerView recyclerView;

    ActionPointLayoutAdapter actionPointLayoutAdapter;
    Course course;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_action_point);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int defaultFontSize = Integer.parseInt(prefs.getString(PrefsActivity.PREF_TEXT_SIZE, "16"));
        actionPoints =( ArrayList<Activity> )getIntent().getSerializableExtra("actionPoints");
        course =( Course) getIntent().getSerializableExtra("course");

        actionPointLayoutAdapter = new ActionPointLayoutAdapter(getApplicationContext(), actionPoints, course, this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setScrollbarFadingEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            recyclerView.setScrollBarFadeDuration(0);
        }
        recyclerView.setAdapter(actionPointLayoutAdapter);
    }


    @Override
    public void OnClickListener(int position) {
        Intent i = new Intent(ActionPointsListActivity.this ,ActionPointDetailedActivity.class );
        i.putExtra("actionPoint", actionPoints.get(position));
        i.putExtra("course", course);
        startActivity(i);
    }
}
