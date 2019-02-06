package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import org.digitalcampus.oppia.model.CourseInstallRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideCourseInstallRepositoryFactory
    implements Factory<CourseInstallRepository> {
  private final AppModule module;

  public AppModule_ProvideCourseInstallRepositoryFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public CourseInstallRepository get() {
    return Preconditions.checkNotNull(
        module.provideCourseInstallRepository(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<CourseInstallRepository> create(AppModule module) {
    return new AppModule_ProvideCourseInstallRepositoryFactory(module);
  }
}
