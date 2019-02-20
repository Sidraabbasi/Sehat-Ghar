/* 
 * This file is part of OppiaMobile - https://digital-campus.org/
 * 
 * OppiaMobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OppiaMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OppiaMobile. If not, see <http://www.gnu.org/licenses/>.
 */

package org.digitalcampus.oppia.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.activity.PrefsActivity;
import org.digitalcampus.oppia.application.MobileLearning;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.utils.ImageUtils;
import org.digitalcampus.oppia.utils.ui.ProgressBarAnimator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

	public static final String TAG = CourseListAdapter.class.getSimpleName();

	private final Context ctx;
	private final ArrayList<Course> courseList;
    OnItemInteraction onItemInteraction;
	private SharedPreferences prefs;
	
	public CourseListAdapter(Context context, ArrayList<Course> courseList, OnItemInteraction onItemInteraction) {

		this.ctx = context;
		this.courseList = courseList;

		prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		this.onItemInteraction  = onItemInteraction;
	}


    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_row, parent, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder viewHolder, final int position) {
        Course c = courseList.get(position);
        String lang = prefs.getString(PrefsActivity.PREF_LANGUAGE, Locale.getDefault().getLanguage());
        viewHolder.courseTitle.setText(c.getMultiLangInfo().getTitle(lang));
        String description = c.getMultiLangInfo().getDescription(lang);
        if ((description != null) && prefs.getBoolean(PrefsActivity.PREF_SHOW_COURSE_DESC, true)){
            viewHolder.courseDescription.setText(description);
        } else {
            viewHolder.courseDescription.setVisibility(View.GONE);
        }

        if (prefs.getBoolean(PrefsActivity.PREF_SHOW_PROGRESS_BAR, MobileLearning.DEFAULT_DISPLAY_PROGRESS_BAR)){
            int courseProgress = (int) c.getProgressPercent();
            viewHolder.courseProgress.setProgress(courseProgress);
            viewHolder.barAnimator.animate(courseProgress);
            //Set the value to true so it doesn' t get animated again
            viewHolder.barAnimator.setAnimated(true);
        } else {
            viewHolder.courseProgress.setVisibility(View.GONE);
        }
        viewHolder.course_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemInteraction.OnClickListener(position);
            }
        });

        if(c.getImageFile() != null){
            String image = c.getImageFileFromRoot();
            Picasso.with(ctx).load(new File(image))
                    .placeholder(R.drawable.default_course)
                    .into(viewHolder.courseImage);
        }
        else{
            viewHolder.courseImage.setImageResource(R.drawable.default_course);
        }
//        BitmapDrawable bm = ImageUtils.LoadBMPsdcard(c.getImageFileFromRoot(), ctx.getResources(), MobileLearning.APP_LOGO);
  //      viewHolder.courseImage.setImageBitmap(bm);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle;
        TextView courseDescription;
        ProgressBar courseProgress;
        ImageView courseImage;
        ProgressBarAnimator barAnimator;
        LinearLayout course_row;

        public CourseViewHolder(View convertView) {
            super(convertView);
           courseTitle = (TextView) convertView.findViewById(R.id.course_title);
           courseDescription = (TextView) convertView.findViewById(R.id.course_description);
           courseProgress = (ProgressBar) convertView.findViewById(R.id.course_progress_bar);
           courseImage = (ImageView) convertView.findViewById(R.id.course_image);
           barAnimator = new ProgressBarAnimator(courseProgress);
            course_row = (LinearLayout) convertView.findViewById(R.id.course_row);


        }
    }
    public interface OnItemInteraction {
	    void OnClickListener(int position);
    }
}
