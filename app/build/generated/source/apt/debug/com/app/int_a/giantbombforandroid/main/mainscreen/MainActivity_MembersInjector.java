package com.app.int_a.giantbombforandroid.main.mainscreen;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<MainScreenPresenter> mainPresenterProvider;

  public MainActivity_MembersInjector(Provider<MainScreenPresenter> mainPresenterProvider) {
    assert mainPresenterProvider != null;
    this.mainPresenterProvider = mainPresenterProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<MainScreenPresenter> mainPresenterProvider) {
    return new MainActivity_MembersInjector(mainPresenterProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mainPresenter = mainPresenterProvider.get();
  }

  public static void injectMainPresenter(
      MainActivity instance, Provider<MainScreenPresenter> mainPresenterProvider) {
    instance.mainPresenter = mainPresenterProvider.get();
  }
}
