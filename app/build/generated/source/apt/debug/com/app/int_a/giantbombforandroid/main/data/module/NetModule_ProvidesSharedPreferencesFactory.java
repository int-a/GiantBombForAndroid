package com.app.int_a.giantbombforandroid.main.data.module;

import android.app.Application;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvidesSharedPreferencesFactory
    implements Factory<SharedPreferences> {
  private final NetModule module;

  private final Provider<Application> applicationProvider;

  public NetModule_ProvidesSharedPreferencesFactory(
      NetModule module, Provider<Application> applicationProvider) {
    assert module != null;
    this.module = module;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public SharedPreferences get() {
    return Preconditions.checkNotNull(
        module.providesSharedPreferences(applicationProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SharedPreferences> create(
      NetModule module, Provider<Application> applicationProvider) {
    return new NetModule_ProvidesSharedPreferencesFactory(module, applicationProvider);
  }
}
