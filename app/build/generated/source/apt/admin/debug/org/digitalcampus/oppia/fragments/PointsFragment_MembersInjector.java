package org.digitalcampus.oppia.fragments;

import dagger.MembersInjector;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.Points;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PointsFragment_MembersInjector implements MembersInjector<PointsFragment> {
  private final Provider<List<Points>> pointsProvider;

  public PointsFragment_MembersInjector(Provider<List<Points>> pointsProvider) {
    assert pointsProvider != null;
    this.pointsProvider = pointsProvider;
  }

  public static MembersInjector<PointsFragment> create(Provider<List<Points>> pointsProvider) {
    return new PointsFragment_MembersInjector(pointsProvider);
  }

  @Override
  public void injectMembers(PointsFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.points = pointsProvider.get();
  }

  public static void injectPoints(PointsFragment instance, Provider<List<Points>> pointsProvider) {
    instance.points = pointsProvider.get();
  }
}
