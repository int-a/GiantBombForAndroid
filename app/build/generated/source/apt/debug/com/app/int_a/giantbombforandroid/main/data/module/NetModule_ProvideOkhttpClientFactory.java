package com.app.int_a.giantbombforandroid.main.data.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideOkhttpClientFactory implements Factory<OkHttpClient> {
  private final NetModule module;

  private final Provider<Cache> cacheProvider;

  public NetModule_ProvideOkhttpClientFactory(NetModule module, Provider<Cache> cacheProvider) {
    assert module != null;
    this.module = module;
    assert cacheProvider != null;
    this.cacheProvider = cacheProvider;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.provideOkhttpClient(cacheProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(NetModule module, Provider<Cache> cacheProvider) {
    return new NetModule_ProvideOkhttpClientFactory(module, cacheProvider);
  }
}
