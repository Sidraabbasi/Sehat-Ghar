package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import org.digitalcampus.oppia.service.courseinstall.CourseInstallerServiceDelegate;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideCourseInstallerServiceDelegateFactory
    implements Factory<CourseInstallerServiceDelegate> {
  private final AppModule module;

  public AppModule_ProvideCourseInstallerServiceDelegateFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public CourseInstallerServiceDelegate get() {
    return Preconditions.checkNotNull(
        module.provideCourseInstallerServiceDelegate(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<CourseInstallerServiceDelegate> create(AppModule module) {
    return new AppModule_ProvideCourseInstallerServiceDelegateFactory(module);
  }
}
