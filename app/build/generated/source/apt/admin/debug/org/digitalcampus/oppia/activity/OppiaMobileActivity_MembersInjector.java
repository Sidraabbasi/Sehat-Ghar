package org.digitalcampus.oppia.activity;

import android.content.SharedPreferences;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.CoursesRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class OppiaMobileActivity_MembersInjector
    implements MembersInjector<OppiaMobileActivity> {
  private final Provider<CoursesRepository> coursesRepositoryProvider;

  private final Provider<SharedPreferences> prefsProvider;

  public OppiaMobileActivity_MembersInjector(
      Provider<CoursesRepository> coursesRepositoryProvider,
      Provider<SharedPreferences> prefsProvider) {
    assert coursesRepositoryProvider != null;
    this.coursesRepositoryProvider = coursesRepositoryProvider;
    assert prefsProvider != null;
    this.prefsProvider = prefsProvider;
  }

  public static MembersInjector<OppiaMobileActivity> create(
      Provider<CoursesRepository> coursesRepositoryProvider,
      Provider<SharedPreferences> prefsProvider) {
    return new OppiaMobileActivity_MembersInjector(coursesRepositoryProvider, prefsProvider);
  }

  @Override
  public void injectMembers(OppiaMobileActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.coursesRepository = coursesRepositoryProvider.get();
    instance.prefs = prefsProvider.get();
  }

  public static void injectCoursesRepository(
      OppiaMobileActivity instance, Provider<CoursesRepository> coursesRepositoryProvider) {
    instance.coursesRepository = coursesRepositoryProvider.get();
  }

  public static void injectPrefs(
      OppiaMobileActivity instance, Provider<SharedPreferences> prefsProvider) {
    instance.prefs = prefsProvider.get();
  }
}
