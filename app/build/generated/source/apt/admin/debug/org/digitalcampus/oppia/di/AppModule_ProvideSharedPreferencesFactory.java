package org.digitalcampus.oppia.di;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideSharedPreferencesFactory implements Factory<SharedPreferences> {
  private final AppModule module;

  public AppModule_ProvideSharedPreferencesFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public SharedPreferences get() {
    return Preconditions.checkNotNull(
        module.provideSharedPreferences(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SharedPreferences> create(AppModule module) {
    return new AppModule_ProvideSharedPreferencesFactory(module);
  }
}
