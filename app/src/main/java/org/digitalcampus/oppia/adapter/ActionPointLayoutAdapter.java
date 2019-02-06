package org.digitalcampus.oppia.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.digitalcampus.mobile.learning.R;
import org.digitalcampus.oppia.activity.PrefsActivity;
import org.digitalcampus.oppia.model.Activity;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.utils.mediaplayer.VideoPlayerActivity;
import org.digitalcampus.oppia.utils.resources.JSInterfaceForResourceImages;
import org.digitalcampus.oppia.utils.storage.FileUtils;
import org.digitalcampus.oppia.utils.storage.Storage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ActionPointLayoutAdapter extends RecyclerView.Adapter<ActionPointLayoutAdapter.CourseViewHolder>  {

    private final ArrayList<Activity> actionPointList;
    private final Context ctx;
    private SharedPreferences prefs;
    Course course;
    OnItemInteraction mOnItemInteraction;

    public ActionPointLayoutAdapter(Context context, ArrayList<Activity> actionPointList, Course course, OnItemInteraction mOnItemInteraction) {

        this.ctx = context;
        this.actionPointList = actionPointList;
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        //this.onItemInteraction  = onItemInteraction;
        this.course = course;
        this.mOnItemInteraction  =mOnItemInteraction;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.action_point_layout, parent, false);

        return new ActionPointLayoutAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder viewHolder, final int position) {
        String description  = actionPointList.get(position).getMultiLangInfo().getTitle(prefs.getString(PrefsActivity.PREF_LANGUAGE, Locale.getDefault()
                .getLanguage()));
        String image = course.getLocation() + actionPointList.get(position).getImageFilePath("");
        Picasso.with(ctx).load(new File(image))
                .placeholder(R.drawable.default_course)
                .into(viewHolder.imageView);
        viewHolder.description.setText(description);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemInteraction.OnClickListener(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return actionPointList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView description ;
        ImageView imageView;
        CardView cardView;

        public CourseViewHolder(View convertView) {
            super(convertView);
            description = (TextView) convertView.findViewById(R.id.actionPointLabel);
            imageView = (ImageView) convertView.findViewById(R.id.actionPointImage);
            cardView = (CardView) convertView.findViewById(R.id.cardView);
        }
    }
    public interface OnItemInteraction {
        void OnClickListener(int position);
    }
}
