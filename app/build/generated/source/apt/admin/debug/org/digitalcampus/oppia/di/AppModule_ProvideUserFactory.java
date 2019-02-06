package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import org.digitalcampus.oppia.model.User;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideUserFactory implements Factory<User> {
  private final AppModule module;

  public AppModule_ProvideUserFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public User get() {
    return Preconditions.checkNotNull(
        module.provideUser(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<User> create(AppModule module) {
    return new AppModule_ProvideUserFactory(module);
  }
}
