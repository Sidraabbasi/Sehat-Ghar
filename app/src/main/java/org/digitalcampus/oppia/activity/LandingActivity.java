package org.digitalcampus.oppia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import org.digitalcampus.mobile.learning.R;

public class LandingActivity extends AppActivity {
    CardView courses ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        courses = (CardView) findViewById(R.id.courses);

        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(LandingActivity.this, OppiaMainActivity.class));
            }
        });

    }
}
