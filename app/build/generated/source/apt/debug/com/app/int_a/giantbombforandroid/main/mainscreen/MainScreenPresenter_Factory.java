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

  private final Provider<MainScreenContract.View> mViewProvider;

  public MainScreenPresenter_Factory(
      Provider<Retrofit> retrofitProvider, Provider<MainScreenContract.View> mViewProvider) {
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
    assert mViewProvider != null;
    this.mViewProvider = mViewProvider;
  }

  @Override
  public MainScreenPresenter get() {
    return new MainScreenPresenter(retrofitProvider.get(), mViewProvider.get());
  }

  public static Factory<MainScreenPresenter> create(
      Provider<Retrofit> retrofitProvider, Provider<MainScreenContract.View> mViewProvider) {
    return new MainScreenPresenter_Factory(retrofitProvider, mViewProvider);
  }
}
