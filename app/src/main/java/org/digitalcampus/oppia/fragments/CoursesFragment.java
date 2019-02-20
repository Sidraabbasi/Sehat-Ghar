package org.digitalcampus.oppia.fragments;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.activity.CourseIndexActivity;
import org.digitalcampus.oppia.activity.CourseLayout;
import org.digitalcampus.oppia.activity.DownloadMediaActivity;
import org.digitalcampus.oppia.activity.OppiaMainActivity;
import org.digitalcampus.oppia.activity.OppiaMobileActivity;
import org.digitalcampus.oppia.activity.PrefsActivity;
import org.digitalcampus.oppia.activity.StartUpActivity;
import org.digitalcampus.oppia.activity.TagSelectActivity;
import org.digitalcampus.oppia.adapter.CourseListAdapter;
import org.digitalcampus.oppia.application.AdminSecurityManager;
import org.digitalcampus.oppia.application.MobileLearning;
import org.digitalcampus.oppia.application.SessionManager;
import org.digitalcampus.oppia.listener.CourseInstallerListener;
import org.digitalcampus.oppia.listener.DeleteCourseListener;
import org.digitalcampus.oppia.listener.ScanMediaListener;
import org.digitalcampus.oppia.listener.UpdateActivityListener;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.model.CoursesRepository;
import org.digitalcampus.oppia.model.DownloadProgress;
import org.digitalcampus.oppia.model.Media;
import org.digitalcampus.oppia.service.courseinstall.InstallerBroadcastReceiver;
import org.digitalcampus.oppia.task.Payload;
import org.digitalcampus.oppia.task.ScanMediaTask;
import org.digitalcampus.oppia.utils.ui.CourseContextMenuCustom;
import org.digitalcampus.oppia.utils.ui.DrawerMenuManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import javax.inject.Inject;

public class CoursesFragment extends Fragment  implements
        SharedPreferences.OnSharedPreferenceChangeListener,
        ScanMediaListener,
        DeleteCourseListener,
        CourseInstallerListener,
        UpdateActivityListener,
        CourseContextMenuCustom.OnContextMenuListener,
        CourseListAdapter.OnItemInteraction {

    public static final String TAG = OppiaMobileActivity.class.getSimpleName();
    private ArrayList<Course> courses;
    private Course tempCourse;
    private int initialCourseListPadding = 0;
    TextView logout;
    LinearLayout mainLayout;
    private TextView messageText;
    private Button messageButton;
    private View messageContainer;
    private RecyclerView courseList;
    private View noCoursesView;
    CourseIndexActivity.NavigationBarStatus mListener;
    OnBackButtonClick onBackButtonClick;
    private CourseListAdapter courseListAdapter;
    private ProgressDialog progressDialog;
    private InstallerBroadcastReceiver receiver;
    private DrawerMenuManager drawer;

    @Inject
    CoursesRepository coursesRepository;
    LinearLayout noCourses;
    ImageButton back_button;
    @Inject SharedPreferences prefs;
    public static CoursesFragment newInstance() {
        CoursesFragment coursesFragment = new CoursesFragment();
        return coursesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_courses, container, false);
        initializeDagger();
        //prefs.registerOnSharedPreferenceChangeListener(this);
        /*

        if ("".equals(prefs.getString(PrefsActivity.PREF_LANGUAGE, ""))) {
            prefs.edit().putString(PrefsActivity.PREF_LANGUAGE, Locale.getDefault().getLanguage()).apply();
        } */
        logout = (TextView) rootView.findViewById(R.id.logout);
        mListener  = (OppiaMainActivity)OppiaMainActivity.getContext();
        mainLayout = (LinearLayout) rootView.findViewById(R.id.mainLayout);
        messageContainer = rootView.findViewById(R.id.home_messages);
        messageText = (TextView) rootView.findViewById(R.id.home_message);
        messageButton = (Button) rootView.findViewById(R.id.message_action_button);
        messageContainer = rootView.findViewById(R.id.home_messages);
        messageContainer = rootView.findViewById(R.id.home_messages);
        messageButton = (Button) rootView.findViewById(R.id.message_action_button);
        onBackButtonClick = (OppiaMainActivity) getActivity();
        noCourses = (LinearLayout) rootView.findViewById(R.id.noCourses);
        back_button= (ImageButton) rootView.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick.navigateToHomeScreen();
            }
        });
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showHide();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        courses = new ArrayList<>();
        courseList = (RecyclerView) rootView.findViewById(R.id.course_list);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
      //  courseList.setLayoutManager(layoutManager);
        courseList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Collections.reverse(courses);
        courseListAdapter = new CourseListAdapter(getContext(), courses,this);
        courseList.setAdapter(courseListAdapter);
        if (courses.size()>0) {
            courseList.setVisibility(View.VISIBLE);
            noCourses.setVisibility(View.GONE);

        }
        else {
            courseList.setVisibility(View.GONE);
            noCourses.setVisibility(View.VISIBLE);
        }
        Button manageBtn = (Button) rootView.findViewById(R.id.manage_courses_btn);
        manageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AdminSecurityManager.checkAdminPermission(getActivity(), R.id.menu_download, new AdminSecurityManager.AuthListener() {
                    public void onPermissionGranted() {
                        startActivity(new Intent(getActivity(), TagSelectActivity.class));
                    }
                });
            }
        });
       return rootView;
    }
    private void initializeDagger() {
        MobileLearning app = (MobileLearning) getActivity().getApplication();
        app.getComponent().inject(this);
    }

    private void displayCourses() {
        courses.clear();
        courses.addAll(coursesRepository.getCourses(getContext()));
        if (courses.size()>0) {
            courseList.setVisibility(View.VISIBLE);
            noCourses.setVisibility(View.GONE);

        }
        else {
            courseList.setVisibility(View.GONE);
            noCourses.setVisibility(View.VISIBLE);
        }
        /*
        if (courses.size() < MobileLearning.DOWNLOAD_COURSES_DISPLAY){
            displayDownloadSection();
        } else {
            TextView tv = (TextView) this.findViewById(R.id.manage_courses_text);
            tv.setText(R.string.no_courses);
            noCoursesView.setVisibility(View.GONE);
        }
        */
        Collections.reverse(courses);
        courseListAdapter.notifyDataSetChanged();
       // this.updateReminders();
        this.scanMedia();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
    @Override
    public void onStart() {
        super.onStart();

        displayCourses();
    }
    @Override
    public void apiKeyInvalidated() {

    }

    @Override
    public void onDownloadProgress(String fileUrl, int progress) {

    }

    @Override
    public void onInstallProgress(String fileUrl, int progress) {

    }

    @Override
    public void onInstallFailed(String fileUrl, String message) {

    }

    @Override
    public void onInstallComplete(String fileUrl) {

    }

    @Override
    public void onCourseDeletionComplete(Payload response) {

    }

    @Override
    public void scanStart() {

    }

    @Override
    public void scanProgressUpdate(String msg) {

    }

    @Override
    public void scanComplete(final Payload response) {
        if (!response.getResponseData().isEmpty()) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            // set title
            alertDialogBuilder.setTitle("Missing File");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Download Missing files")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            ArrayList<Object> m = (ArrayList<Object>) response.getResponseData();
                            Intent i = new Intent(getActivity(), DownloadMediaActivity.class);
                            Bundle tb = new Bundle();
                            tb.putSerializable(DownloadMediaActivity.TAG, m);
                            i.putExtras(tb);
                            startActivity(i);

                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

            /*if (messageContainer.getVisibility() != View.VISIBLE){
                messageContainer.setVisibility(View.VISIBLE);
                messageButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        @SuppressWarnings("unchecked")
                        ArrayList<Object> m = (ArrayList<Object>) view.getTag();
                        Intent i = new Intent(getActivity(), DownloadMediaActivity.class);
                        Bundle tb = new Bundle();
                        tb.putSerializable(DownloadMediaActivity.TAG, m);
                        i.putExtras(tb);
                        startActivity(i);
                    }
                });
                animateScanMediaMessage();
            }

            messageText.setText(this.getString(R.string.info_scan_media_missing));
            messageButton.setText(this.getString(R.string.scan_media_download_button));
            messageButton.setTag(response.getResponseData()); */
            Media.resetMediaScan(prefs);
        } else {
            hideScanMediaMessage();
            messageButton.setOnClickListener(null);
            messageButton.setTag(null);
            Media.updateMediaScan(prefs);
        }
    }

    @Override
    public void updateActivityComplete(Payload p) {

    }

    @Override
    public void updateActivityProgressUpdate(DownloadProgress dp) {

    }

    @Override
    public void onContextMenuItemSelected(int position, int itemId) {

    }

    @Override
    public void OnClickListener(int position) {
        Course selectedCourse = courses.get(position);

        Intent i = new Intent(getActivity(), CourseIndexActivity.class);
        Bundle tb = new Bundle();
        tb.putSerializable(Course.TAG, selectedCourse);
        i.putExtras(tb);
        startActivity(i);
    }
    public interface  OnBackButtonClick {
        void navigateToHomeScreen ();
    }



    private void animateScanMediaMessage(){
        TranslateAnimation anim = new TranslateAnimation(0, 0, -200, 0);
        anim.setDuration(900);
        messageContainer.startAnimation(anim);

        messageContainer.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ValueAnimator animator = ValueAnimator.ofInt(initialCourseListPadding, messageContainer.getMeasuredHeight());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //@Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                courseList.setPadding(0, (Integer) valueAnimator.getAnimatedValue(), 0, 0);
                courseList.setVerticalScrollbarPosition(1);
            }
        });
        animator.setStartDelay(200);
        animator.setDuration(700);
        animator.start();
    }

    private void scanMedia() {

        if (Media.shouldScanMedia(prefs)){
            ScanMediaTask task = new ScanMediaTask(getActivity());
            Payload p = new Payload(this.courses);
            scanComplete(p);
            task.setScanMediaListener(this);
            task.execute(p);
        }
        else{
            hideScanMediaMessage();
        }
    }

    public void logout () {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.logout);
        builder.setMessage(R.string.logout_confirm);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SessionManager.logoutCurrentUser(getActivity());

                Intent restartIntent = new Intent(getActivity(), StartUpActivity.class);
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(restartIntent);
                getActivity().finish();
            }
        });
        builder.setNegativeButton(R.string.no, null);
        builder.show();
    }
    private void hideScanMediaMessage(){
        messageContainer.setVisibility(View.GONE);
        courseList.setPadding(0, initialCourseListPadding, 0, 0);
    }
}
