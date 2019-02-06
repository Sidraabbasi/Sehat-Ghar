package org.digitalcampus.oppia.activity;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.CompleteCourseProvider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CourseLayout_MembersInjector implements MembersInjector<CourseLayout> {
  private final Provider<CompleteCourseProvider> completeCourseProvider;

  public CourseLayout_MembersInjector(Provider<CompleteCourseProvider> completeCourseProvider) {
    assert completeCourseProvider != null;
    this.completeCourseProvider = completeCourseProvider;
  }

  public static MembersInjector<CourseLayout> create(
      Provider<CompleteCourseProvider> completeCourseProvider) {
    return new CourseLayout_MembersInjector(completeCourseProvider);
  }

  @Override
  public void injectMembers(CourseLayout instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.completeCourseProvider = completeCourseProvider.get();
  }

  public static void injectCompleteCourseProvider(
      CourseLayout instance, Provider<CompleteCourseProvider> completeCourseProvider) {
    instance.completeCourseProvider = completeCourseProvider.get();
  }
}
