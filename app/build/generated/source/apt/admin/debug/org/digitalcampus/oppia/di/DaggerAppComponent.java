package org.digitalcampus.oppia.di;

import android.content.SharedPreferences;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.activity.CourseIndexActivity;
import org.digitalcampus.oppia.activity.CourseIndexActivity_MembersInjector;
import org.digitalcampus.oppia.activity.CourseLayout;
import org.digitalcampus.oppia.activity.CourseLayout_MembersInjector;
import org.digitalcampus.oppia.activity.DownloadActivity;
import org.digitalcampus.oppia.activity.DownloadActivity_MembersInjector;
import org.digitalcampus.oppia.activity.OppiaMainActivity;
import org.digitalcampus.oppia.activity.OppiaMobileActivity;
import org.digitalcampus.oppia.activity.OppiaMobileActivity_MembersInjector;
import org.digitalcampus.oppia.activity.TagSelectActivity;
import org.digitalcampus.oppia.activity.TagSelectActivity_MembersInjector;
import org.digitalcampus.oppia.fragments.BadgesFragment;
import org.digitalcampus.oppia.fragments.BadgesFragment_MembersInjector;
import org.digitalcampus.oppia.fragments.CoursesFragment;
import org.digitalcampus.oppia.fragments.CoursesFragment_MembersInjector;
import org.digitalcampus.oppia.fragments.GlobalScorecardFragment;
import org.digitalcampus.oppia.fragments.GlobalScorecardFragment_MembersInjector;
import org.digitalcampus.oppia.fragments.PointsFragment;
import org.digitalcampus.oppia.fragments.PointsFragment_MembersInjector;
import org.digitalcampus.oppia.model.Badges;
import org.digitalcampus.oppia.model.CompleteCourseProvider;
import org.digitalcampus.oppia.model.CourseInstallRepository;
import org.digitalcampus.oppia.model.CoursesRepository;
import org.digitalcampus.oppia.model.Points;
import org.digitalcampus.oppia.model.TagRepository;
import org.digitalcampus.oppia.model.User;
import org.digitalcampus.oppia.service.courseinstall.CourseInstallerServiceDelegate;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<CoursesRepository> provideCoursesRepositoryProvider;

  private Provider<SharedPreferences> provideSharedPreferencesProvider;

  private MembersInjector<OppiaMobileActivity> oppiaMobileActivityMembersInjector;

  private Provider<CompleteCourseProvider> provideCompleteCourseProvider;

  private MembersInjector<CourseLayout> courseLayoutMembersInjector;

  private MembersInjector<CourseIndexActivity> courseIndexActivityMembersInjector;

  private Provider<TagRepository> provideTagRepositoryProvider;

  private MembersInjector<TagSelectActivity> tagSelectActivityMembersInjector;

  private Provider<CourseInstallRepository> provideCourseInstallRepositoryProvider;

  private Provider<CourseInstallerServiceDelegate> provideCourseInstallerServiceDelegateProvider;

  private MembersInjector<DownloadActivity> downloadActivityMembersInjector;

  private MembersInjector<GlobalScorecardFragment> globalScorecardFragmentMembersInjector;

  private Provider<List<Points>> providePointsListProvider;

  private MembersInjector<PointsFragment> pointsFragmentMembersInjector;

  private Provider<ArrayList<Badges>> provideBadgesListProvider;

  private MembersInjector<BadgesFragment> badgesFragmentMembersInjector;

  private MembersInjector<CoursesFragment> coursesFragmentMembersInjector;

  private Provider<User> provideUserProvider;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideCoursesRepositoryProvider =
        DoubleCheck.provider(AppModule_ProvideCoursesRepositoryFactory.create(builder.appModule));

    this.provideSharedPreferencesProvider =
        DoubleCheck.provider(AppModule_ProvideSharedPreferencesFactory.create(builder.appModule));

    this.oppiaMobileActivityMembersInjector =
        OppiaMobileActivity_MembersInjector.create(
            provideCoursesRepositoryProvider, provideSharedPreferencesProvider);

    this.provideCompleteCourseProvider =
        DoubleCheck.provider(
            AppModule_ProvideCompleteCourseProviderFactory.create(builder.appModule));

    this.courseLayoutMembersInjector =
        CourseLayout_MembersInjector.create(provideCompleteCourseProvider);

    this.courseIndexActivityMembersInjector =
        CourseIndexActivity_MembersInjector.create(provideCompleteCourseProvider);

    this.provideTagRepositoryProvider =
        DoubleCheck.provider(AppModule_ProvideTagRepositoryFactory.create(builder.appModule));

    this.tagSelectActivityMembersInjector =
        TagSelectActivity_MembersInjector.create(provideTagRepositoryProvider);

    this.provideCourseInstallRepositoryProvider =
        DoubleCheck.provider(
            AppModule_ProvideCourseInstallRepositoryFactory.create(builder.appModule));

    this.provideCourseInstallerServiceDelegateProvider =
        DoubleCheck.provider(
            AppModule_ProvideCourseInstallerServiceDelegateFactory.create(builder.appModule));

    this.downloadActivityMembersInjector =
        DownloadActivity_MembersInjector.create(
            provideCourseInstallRepositoryProvider, provideCourseInstallerServiceDelegateProvider);

    this.globalScorecardFragmentMembersInjector =
        GlobalScorecardFragment_MembersInjector.create(provideCoursesRepositoryProvider);

    this.providePointsListProvider = AppModule_ProvidePointsListFactory.create(builder.appModule);

    this.pointsFragmentMembersInjector =
        PointsFragment_MembersInjector.create(providePointsListProvider);

    this.provideBadgesListProvider = AppModule_ProvideBadgesListFactory.create(builder.appModule);

    this.badgesFragmentMembersInjector =
        BadgesFragment_MembersInjector.create(provideBadgesListProvider);

    this.coursesFragmentMembersInjector =
        CoursesFragment_MembersInjector.create(
            provideCoursesRepositoryProvider, provideSharedPreferencesProvider);

    this.provideUserProvider = AppModule_ProvideUserFactory.create(builder.appModule);
  }

  @Override
  public void inject(OppiaMobileActivity activity) {
    oppiaMobileActivityMembersInjector.injectMembers(activity);
  }

  @Override
  public void inject(CourseLayout activity) {
    courseLayoutMembersInjector.injectMembers(activity);
  }

  @Override
  public void inject(CourseIndexActivity activity) {
    courseIndexActivityMembersInjector.injectMembers(activity);
  }

  @Override
  public void inject(TagSelectActivity activity) {
    tagSelectActivityMembersInjector.injectMembers(activity);
  }

  @Override
  public void inject(DownloadActivity activity) {
    downloadActivityMembersInjector.injectMembers(activity);
  }

  @Override
  public void inject(OppiaMainActivity activity) {
    MembersInjectors.<OppiaMainActivity>noOp().injectMembers(activity);
  }

  @Override
  public void inject(GlobalScorecardFragment fragment) {
    globalScorecardFragmentMembersInjector.injectMembers(fragment);
  }

  @Override
  public void inject(PointsFragment fragment) {
    pointsFragmentMembersInjector.injectMembers(fragment);
  }

  @Override
  public void inject(BadgesFragment fragment) {
    badgesFragmentMembersInjector.injectMembers(fragment);
  }

  @Override
  public void inject(CoursesFragment fragment) {
    coursesFragmentMembersInjector.injectMembers(fragment);
  }

  @Override
  public User getUser() {
    return provideUserProvider.get();
  }

  public static final class Builder {
    private AppModule appModule;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }
  }
}
