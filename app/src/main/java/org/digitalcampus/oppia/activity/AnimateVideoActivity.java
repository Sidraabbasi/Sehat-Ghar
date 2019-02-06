package org.digitalcampus.oppia.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.application.SessionManager;
import org.digitalcampus.oppia.model.Activity;

import java.io.IOException;
import java.io.InputStream;

public class AnimateVideoActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_video);
        AssetManager assetManager = getAssets();

            String path = "android.resource://" + getPackageName() + "/" + R.raw.animate;
            Uri uri = Uri.parse(path);
            VideoView videoView =(VideoView)findViewById(R.id.videoView);
            MediaController mediaController= new MediaController(this);
            mediaController.setAnchorView(videoView);
            //Setting MediaController and URI, then starting the videoView
            videoView.setMediaController(null);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    startActivity(new Intent(AnimateVideoActivity.this,
                            SessionManager.isLoggedIn(getApplicationContext())
                                    ? OppiaMainActivity.class
                                    : WelcomeActivity.class));

                    AnimateVideoActivity.this.finish();
                }
            });



    }

}
