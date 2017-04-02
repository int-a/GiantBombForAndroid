package com.app.int_a.giantbombforandroid.main.mainscreen;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainScreenPresenter_Factory implements Factory<MainScreenPresenter> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<MainScreenContract.View> viewProvider;

  public MainScreenPresenter_Factory(
      Provider<Retrofit> retrofitProvider, Provider<MainScreenContract.View> viewProvider) {
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
    assert viewProvider != null;
    this.viewProvider = viewProvider;
  }

  @Override
  public MainScreenPresenter get() {
    return new MainScreenPresenter(retrofitProvider.get(), viewProvider.get());
  }

  public static Factory<MainScreenPresenter> create(
      Provider<Retrofit> retrofitProvider, Provider<MainScreenContract.View> viewProvider) {
    return new MainScreenPresenter_Factory(retrofitProvider, viewProvider);
  }
}
