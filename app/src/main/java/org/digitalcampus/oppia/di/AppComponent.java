package org.digitalcampus.oppia.di;

import org.digitalcampus.oppia.activity.CourseIndexActivity;
import org.digitalcampus.oppia.activity.CourseLayout;
import org.digitalcampus.oppia.activity.DownloadActivity;
import org.digitalcampus.oppia.activity.OppiaMainActivity;
import org.digitalcampus.oppia.activity.OppiaMobileActivity;
import org.digitalcampus.oppia.activity.TagSelectActivity;
import org.digitalcampus.oppia.fragments.BadgesFragment;
import org.digitalcampus.oppia.fragments.CoursesFragment;
import org.digitalcampus.oppia.fragments.GlobalScorecardFragment;
import org.digitalcampus.oppia.fragments.PointsFragment;
import org.digitalcampus.oppia.model.User;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(OppiaMobileActivity activity);
    void inject(CourseLayout activity);
    void inject(CourseIndexActivity activity);
    void inject(TagSelectActivity activity);
    void inject(DownloadActivity activity);
    void inject(OppiaMainActivity activity);

    void inject(GlobalScorecardFragment fragment);
    void inject(PointsFragment fragment);
    void inject(BadgesFragment fragment);
    void inject(CoursesFragment fragment);

    User getUser();
}
