package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.annotation.Generated;
import org.digitalcampus.oppia.model.Points;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvidePointsListFactory implements Factory<List<Points>> {
  private final AppModule module;

  public AppModule_ProvidePointsListFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public List<Points> get() {
    return Preconditions.checkNotNull(
        module.providePointsList(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<List<Points>> create(AppModule module) {
    return new AppModule_ProvidePointsListFactory(module);
  }
}
