package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import org.digitalcampus.oppia.model.CoursesRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideCoursesRepositoryFactory implements Factory<CoursesRepository> {
  private final AppModule module;

  public AppModule_ProvideCoursesRepositoryFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public CoursesRepository get() {
    return Preconditions.checkNotNull(
        module.provideCoursesRepository(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<CoursesRepository> create(AppModule module) {
    return new AppModule_ProvideCoursesRepositoryFactory(module);
  }
}
