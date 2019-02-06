package org.digitalcampus.oppia.activity;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.CompleteCourseProvider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CourseIndexActivity_MembersInjector
    implements MembersInjector<CourseIndexActivity> {
  private final Provider<CompleteCourseProvider> completeCourseProvider;

  public CourseIndexActivity_MembersInjector(
      Provider<CompleteCourseProvider> completeCourseProvider) {
    assert completeCourseProvider != null;
    this.completeCourseProvider = completeCourseProvider;
  }

  public static MembersInjector<CourseIndexActivity> create(
      Provider<CompleteCourseProvider> completeCourseProvider) {
    return new CourseIndexActivity_MembersInjector(completeCourseProvider);
  }

  @Override
  public void injectMembers(CourseIndexActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.completeCourseProvider = completeCourseProvider.get();
  }

  public static void injectCompleteCourseProvider(
      CourseIndexActivity instance, Provider<CompleteCourseProvider> completeCourseProvider) {
    instance.completeCourseProvider = completeCourseProvider.get();
  }
}
