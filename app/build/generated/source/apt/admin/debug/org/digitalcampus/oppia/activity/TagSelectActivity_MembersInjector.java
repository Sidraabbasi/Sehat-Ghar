package org.digitalcampus.oppia.activity;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.TagRepository;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TagSelectActivity_MembersInjector implements MembersInjector<TagSelectActivity> {
  private final Provider<TagRepository> tagRepositoryProvider;

  public TagSelectActivity_MembersInjector(Provider<TagRepository> tagRepositoryProvider) {
    assert tagRepositoryProvider != null;
    this.tagRepositoryProvider = tagRepositoryProvider;
  }

  public static MembersInjector<TagSelectActivity> create(
      Provider<TagRepository> tagRepositoryProvider) {
    return new TagSelectActivity_MembersInjector(tagRepositoryProvider);
  }

  @Override
  public void injectMembers(TagSelectActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.tagRepository = tagRepositoryProvider.get();
  }

  public static void injectTagRepository(
      TagSelectActivity instance, Provider<TagRepository> tagRepositoryProvider) {
    instance.tagRepository = tagRepositoryProvider.get();
  }
}
