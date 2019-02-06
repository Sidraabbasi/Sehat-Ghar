package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import javax.annotation.Generated;
import org.digitalcampus.oppia.model.Badges;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideBadgesListFactory implements Factory<ArrayList<Badges>> {
  private final AppModule module;

  public AppModule_ProvideBadgesListFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public ArrayList<Badges> get() {
    return Preconditions.checkNotNull(
        module.provideBadgesList(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ArrayList<Badges>> create(AppModule module) {
    return new AppModule_ProvideBadgesListFactory(module);
  }
}
