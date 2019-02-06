package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import org.digitalcampus.oppia.model.CompleteCourseProvider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideCompleteCourseProviderFactory
    implements Factory<CompleteCourseProvider> {
  private final AppModule module;

  public AppModule_ProvideCompleteCourseProviderFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public CompleteCourseProvider get() {
    return Preconditions.checkNotNull(
        module.provideCompleteCourseProvider(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<CompleteCourseProvider> create(AppModule module) {
    return new AppModule_ProvideCompleteCourseProviderFactory(module);
  }
}
