package org.digitalcampus.oppia.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.adapter.SectionListAdapter;
import org.digitalcampus.oppia.application.MobileLearning;
import org.digitalcampus.oppia.model.Activity;
import org.digitalcampus.oppia.model.CompleteCourse;
import org.digitalcampus.oppia.model.CompleteCourseProvider;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.model.Section;
import org.digitalcampus.oppia.task.ParseCourseXMLTask;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

public class CourseLayout extends AppActivity  implements SharedPreferences.OnSharedPreferenceChangeListener, ParseCourseXMLTask.OnParseXmlListener {

    public static final String TAG = CourseIndexActivity.class.getSimpleName();
    public static final String JUMPTO_TAG = "JumpTo";
    public static final int RESULT_JUMPTO = 99;

    private Course course;
    private CompleteCourse parsedCourse;

    private ArrayList<Section> sections;
    private SharedPreferences prefs;
    private Activity baselineActivity;
    private AlertDialog aDialog;
    private View loadingCourseView;
    private SectionListAdapter sla;

    private String digestJumpTo;

    @Inject CompleteCourseProvider completeCourseProvider;

    TextView description ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_layout);
        initializeDagger();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        description =  (TextView) findViewById(R.id.description);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            course = (Course) bundle.getSerializable(Course.TAG);

            String digest = (String) bundle.getSerializable(JUMPTO_TAG);
            completeCourseProvider.getCompleteCourseAsync(this, course);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    private void initializeDagger() {
        MobileLearning app = (MobileLearning) getApplication();
        app.getComponent().inject(this);
    }
    @Override
    public void onParseComplete(CompleteCourse parsed) {
        parsedCourse = parsed;
        course.setMetaPages(parsedCourse.getMetaPages());
        course.setMedia(parsedCourse.getMedia());
        course.setGamificationEvents(parsedCourse.getGamification());
        sections = parsedCourse.getSections();


        String lang = prefs.getString(PrefsActivity.PREF_LANGUAGE, Locale.getDefault().getLanguage());

        String fileUrl = course.getLocation() + sections.get(0).getActivities().get(0).getLocation(lang);

        // show description if any
        
        String desc = course.getLocation() + sections.get(0).getActivities().get(2).getMultiLangInfo().getDescription(lang);

        if ((desc != null) && desc.length() > 0){
            description.setText(desc);
        } else {
            description.setVisibility(View.GONE);
        }

    }

    @Override
    public void onParseError() {

    }
}
