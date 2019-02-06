package org.digitalcampus.oppia.fragments;

import dagger.MembersInjector;
import java.util.ArrayList;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.digitalcampus.oppia.model.Badges;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BadgesFragment_MembersInjector implements MembersInjector<BadgesFragment> {
  private final Provider<ArrayList<Badges>> badgesProvider;

  public BadgesFragment_MembersInjector(Provider<ArrayList<Badges>> badgesProvider) {
    assert badgesProvider != null;
    this.badgesProvider = badgesProvider;
  }

  public static MembersInjector<BadgesFragment> create(Provider<ArrayList<Badges>> badgesProvider) {
    return new BadgesFragment_MembersInjector(badgesProvider);
  }

  @Override
  public void injectMembers(BadgesFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.badges = badgesProvider.get();
  }

  public static void injectBadges(
      BadgesFragment instance, Provider<ArrayList<Badges>> badgesProvider) {
    instance.badges = badgesProvider.get();
  }
}
