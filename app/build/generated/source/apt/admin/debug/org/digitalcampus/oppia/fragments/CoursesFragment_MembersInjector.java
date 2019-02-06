package org.digitalcampus.oppia.fragments;

import android.content.SharedPreferences;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.CoursesRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CoursesFragment_MembersInjector implements MembersInjector<CoursesFragment> {
  private final Provider<CoursesRepository> coursesRepositoryProvider;

  private final Provider<SharedPreferences> prefsProvider;

  public CoursesFragment_MembersInjector(
      Provider<CoursesRepository> coursesRepositoryProvider,
      Provider<SharedPreferences> prefsProvider) {
    assert coursesRepositoryProvider != null;
    this.coursesRepositoryProvider = coursesRepositoryProvider;
    assert prefsProvider != null;
    this.prefsProvider = prefsProvider;
  }

  public static MembersInjector<CoursesFragment> create(
      Provider<CoursesRepository> coursesRepositoryProvider,
      Provider<SharedPreferences> prefsProvider) {
    return new CoursesFragment_MembersInjector(coursesRepositoryProvider, prefsProvider);
  }

  @Override
  public void injectMembers(CoursesFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.coursesRepository = coursesRepositoryProvider.get();
    instance.prefs = prefsProvider.get();
  }

  public static void injectCoursesRepository(
      CoursesFragment instance, Provider<CoursesRepository> coursesRepositoryProvider) {
    instance.coursesRepository = coursesRepositoryProvider.get();
  }

  public static void injectPrefs(
      CoursesFragment instance, Provider<SharedPreferences> prefsProvider) {
    instance.prefs = prefsProvider.get();
  }
}
