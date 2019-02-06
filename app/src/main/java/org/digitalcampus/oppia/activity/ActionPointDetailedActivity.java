package org.digitalcampus.oppia.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.model.Activity;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.utils.mediaplayer.VideoPlayerActivity;
import org.digitalcampus.oppia.utils.resources.JSInterfaceForResourceImages;
import org.digitalcampus.oppia.utils.storage.FileUtils;
import org.digitalcampus.oppia.utils.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ActionPointDetailedActivity extends AppActivity  {

    Activity actionPoint;
    Course course;
    SharedPreferences prefs;
    WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_point_detail_activity);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        actionPoint = (Activity) getIntent().getSerializableExtra("actionPoint");
        course = (Course) getIntent().getSerializableExtra("course");
        webView = (WebView) findViewById(R.id.actionPointWebView);
        String url = course.getLocation() + actionPoint.getLocation(prefs.getString(PrefsActivity.PREF_LANGUAGE, Locale.getDefault()
                .getLanguage()));
        int defaultFontSize = Integer.parseInt(prefs.getString(PrefsActivity.PREF_TEXT_SIZE, "16"));
        webView.getSettings().setDefaultFontSize(defaultFontSize);
        webView.getSettings().setJavaScriptEnabled(true);
        //We inject the interface to launch intents from the HTML

        webView.addJavascriptInterface(
                new JSInterfaceForResourceImages(getApplicationContext(), course.getLocation()),
                JSInterfaceForResourceImages.InterfaceExposedName);

        try {
            webView.loadDataWithBaseURL("file://" + course.getLocation() + File.separator, FileUtils.readFile(url), "text/html", "utf-8", null);
        } catch (IOException e) {
            webView.loadUrl("file://" + url);
        }
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                //We execute the necessary JS code to bind click on images with our JavascriptInterface
                view.loadUrl(JSInterfaceForResourceImages.JSInjection);
            }

            // set up the page to intercept videos
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("/video/")) {
                    // extract video name from url
                    int startPos = url.indexOf("/video/") + 7;
                    String mediaFileName = url.substring(startPos, url.length());
                    startMediaPlayerWithFile(mediaFileName, actionPoint);
                    return true;

                } else {

                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.parse(url);
                        intent.setData(data);
                        getApplicationContext().startActivity(intent);
                        return true;
                    } catch (ActivityNotFoundException anfe) {
                        // Log.d(TAG,"Activity not found", anfe);
                    }
                    return false;
                }
            }
        });
    }

    protected void startMediaPlayerWithFile(String mediaFileName, Activity activity){
        // check media file exists
        boolean exists = Storage.mediaFileExists(this, mediaFileName);
        if (!exists) {
            Toast.makeText(this,
                    this.getString(R.string.error_media_not_found, mediaFileName),
                    Toast.LENGTH_LONG).show();
            return;
        }

        String mimeType = FileUtils.getMimeType(Storage.getMediaPath(this) + mediaFileName);
        if (!FileUtils.isSupportedMediafileType(mimeType)) {
            Toast.makeText(this,
                    this.getString(R.string.error_media_unsupported, mediaFileName),
                    Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, VideoPlayerActivity.class);
        Bundle tb = new Bundle();
        tb.putSerializable(VideoPlayerActivity.MEDIA_TAG, mediaFileName);
        tb.putSerializable(Activity.TAG, activity);
        tb.putSerializable(Course.TAG, course);
        intent.putExtras(tb);
        this.startActivity(intent);
    }
}
