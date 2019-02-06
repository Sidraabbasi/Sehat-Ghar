package org.digitalcampus.oppia.fragments;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.CoursesRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalScorecardFragment_MembersInjector
    implements MembersInjector<GlobalScorecardFragment> {
  private final Provider<CoursesRepository> coursesRepositoryProvider;

  public GlobalScorecardFragment_MembersInjector(
      Provider<CoursesRepository> coursesRepositoryProvider) {
    assert coursesRepositoryProvider != null;
    this.coursesRepositoryProvider = coursesRepositoryProvider;
  }

  public static MembersInjector<GlobalScorecardFragment> create(
      Provider<CoursesRepository> coursesRepositoryProvider) {
    return new GlobalScorecardFragment_MembersInjector(coursesRepositoryProvider);
  }

  @Override
  public void injectMembers(GlobalScorecardFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.coursesRepository = coursesRepositoryProvider.get();
  }

  public static void injectCoursesRepository(
      GlobalScorecardFragment instance, Provider<CoursesRepository> coursesRepositoryProvider) {
    instance.coursesRepository = coursesRepositoryProvider.get();
  }
}
