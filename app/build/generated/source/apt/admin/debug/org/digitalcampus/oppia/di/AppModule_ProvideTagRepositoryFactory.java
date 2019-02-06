package org.digitalcampus.oppia.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import org.digitalcampus.oppia.model.TagRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideTagRepositoryFactory implements Factory<TagRepository> {
  private final AppModule module;

  public AppModule_ProvideTagRepositoryFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public TagRepository get() {
    return Preconditions.checkNotNull(
        module.provideTagRepository(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<TagRepository> create(AppModule module) {
    return new AppModule_ProvideTagRepositoryFactory(module);
  }
}
