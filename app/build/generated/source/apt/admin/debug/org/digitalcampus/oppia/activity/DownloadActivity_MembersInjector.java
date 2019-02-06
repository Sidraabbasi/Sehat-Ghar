package org.digitalcampus.oppia.activity;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.CourseInstallRepository;
import org.digitalcampus.oppia.service.courseinstall.CourseInstallerServiceDelegate;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DownloadActivity_MembersInjector implements MembersInjector<DownloadActivity> {
  private final Provider<CourseInstallRepository> courseInstallRepositoryProvider;

  private final Provider<CourseInstallerServiceDelegate> courseInstallerServiceDelegateProvider;

  public DownloadActivity_MembersInjector(
      Provider<CourseInstallRepository> courseInstallRepositoryProvider,
      Provider<CourseInstallerServiceDelegate> courseInstallerServiceDelegateProvider) {
    assert courseInstallRepositoryProvider != null;
    this.courseInstallRepositoryProvider = courseInstallRepositoryProvider;
    assert courseInstallerServiceDelegateProvider != null;
    this.courseInstallerServiceDelegateProvider = courseInstallerServiceDelegateProvider;
  }

  public static MembersInjector<DownloadActivity> create(
      Provider<CourseInstallRepository> courseInstallRepositoryProvider,
      Provider<CourseInstallerServiceDelegate> courseInstallerServiceDelegateProvider) {
    return new DownloadActivity_MembersInjector(
        courseInstallRepositoryProvider, courseInstallerServiceDelegateProvider);
  }

  @Override
  public void injectMembers(DownloadActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.courseInstallRepository = courseInstallRepositoryProvider.get();
    instance.courseInstallerServiceDelegate = courseInstallerServiceDelegateProvider.get();
  }

  public static void injectCourseInstallRepository(
      DownloadActivity instance,
      Provider<CourseInstallRepository> courseInstallRepositoryProvider) {
    instance.courseInstallRepository = courseInstallRepositoryProvider.get();
  }

  public static void injectCourseInstallerServiceDelegate(
      DownloadActivity instance,
      Provider<CourseInstallerServiceDelegate> courseInstallerServiceDelegateProvider) {
    instance.courseInstallerServiceDelegate = courseInstallerServiceDelegateProvider.get();
  }
}
