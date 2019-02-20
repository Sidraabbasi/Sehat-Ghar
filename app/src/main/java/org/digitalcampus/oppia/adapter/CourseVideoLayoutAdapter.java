package org.digitalcampus.oppia.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.activity.CourseIndexActivity;
import org.digitalcampus.oppia.activity.PrefsActivity;
import org.digitalcampus.oppia.application.MobileLearning;
import org.digitalcampus.oppia.model.Activity;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.utils.mediaplayer.VideoPlayerActivity;
import org.digitalcampus.oppia.utils.resources.JSInterfaceForResourceImages;
import org.digitalcampus.oppia.utils.storage.FileUtils;
import org.digitalcampus.oppia.utils.storage.Storage;
import org.digitalcampus.oppia.utils.ui.ProgressBarAnimator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class CourseVideoLayoutAdapter extends RecyclerView.Adapter<CourseVideoLayoutAdapter.CourseViewHolder> {
    public static final String TAG = CourseListAdapter.class.getSimpleName();

    private final Context ctx;
    private final ArrayList<Activity> courseVideoList;
    CourseVideoLayoutAdapter.OnItemInteraction onItemInteraction;
    private SharedPreferences prefs;

    Course course;

    public CourseVideoLayoutAdapter(Context context, ArrayList<Activity> courseVideoList, Course course) {

        this.ctx = context;
        this.courseVideoList = courseVideoList;
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        //this.onItemInteraction  = onItemInteraction;
        this.course = course;
    }


    @Override
    public CourseVideoLayoutAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_video_layout, parent, false);

        return new CourseVideoLayoutAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseVideoLayoutAdapter.CourseViewHolder viewHolder, final int position) {
        final Activity activity = courseVideoList.get(position);
        String currentLang = prefs.getString(PrefsActivity.PREF_LANGUAGE, Locale.getDefault().getLanguage());
        viewHolder.description.setText(activity.getMultiLangInfo().getTitle(currentLang));
        String url = course.getLocation() + activity.getLocation(prefs.getString(PrefsActivity.PREF_LANGUAGE, Locale.getDefault()
                .getLanguage()));
        int defaultFontSize = Integer.parseInt(prefs.getString(PrefsActivity.PREF_TEXT_SIZE, "16"));
        viewHolder.webView.getSettings().setDefaultFontSize(defaultFontSize);
        viewHolder.webView.getSettings().setJavaScriptEnabled(true);
        //We inject the interface to launch intents from the HTML

        viewHolder.webView.addJavascriptInterface(
                new JSInterfaceForResourceImages(ctx, course.getLocation()),
                JSInterfaceForResourceImages.InterfaceExposedName);

        try {
            viewHolder.webView.loadDataWithBaseURL("file://" + course.getLocation() + File.separator, FileUtils.readFile(url), "text/html", "utf-8", null);
        } catch (IOException e) {
            viewHolder.webView.loadUrl("file://" + url);
        }
        viewHolder.webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                //We execute the necessary JS code to bind click on images with our JavascriptInterface
                //view.loadUrl(JSInterfaceForResourceImages.JSInjection);
            }

            // set up the page to intercept videos
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("/video/")) {
                    // extract video name from url
                    int startPos = url.indexOf("/video/") + 7;
                    String mediaFileName = url.substring(startPos, url.length());
                    startMediaPlayerWithFile(mediaFileName,activity);
                    return true;

                } else {

                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.parse(url);
                        intent.setData(data);
                        ctx.startActivity(intent);
                        return true;
                    } catch (ActivityNotFoundException anfe) {
                        Log.d(TAG,"Activity not found", anfe);
                    }
                    return false;
                }
            }
        });
    }
    protected void startMediaPlayerWithFile(String mediaFileName, Activity activity){
        // check media file exists
        boolean exists = Storage.mediaFileExists(ctx, mediaFileName);
        if (!exists) {
            Toast.makeText(ctx,
                    ctx.getString(R.string.error_media_not_found, mediaFileName),
                    Toast.LENGTH_LONG).show();
            return;
        }

        String mimeType = FileUtils.getMimeType(Storage.getMediaPath(ctx) + mediaFileName);
        if (!FileUtils.isSupportedMediafileType(mimeType)) {
            Toast.makeText(ctx,
                    ctx.getString(R.string.error_media_unsupported, mediaFileName),
                    Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(ctx, VideoPlayerActivity.class);
        Bundle tb = new Bundle();
        tb.putSerializable(VideoPlayerActivity.MEDIA_TAG, mediaFileName);
        tb.putSerializable(Activity.TAG, activity);
        tb.putSerializable(Course.TAG, course);
        intent.putExtras(tb);
        ctx.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return courseVideoList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
       WebView webView;
       TextView description;

        public CourseViewHolder(View convertView) {
            super(convertView);

            webView = (WebView) convertView.findViewById(R.id.fragment_webview);
            description = (TextView) convertView.findViewById(R.id.title);


        }
    }
    public interface OnItemInteraction {
        void OnClickListener(int position);
    }
}
