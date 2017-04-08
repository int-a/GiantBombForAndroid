package com.app.int_a.giantbombforandroid.main.data.component;

import android.app.Application;
import com.app.int_a.giantbombforandroid.main.data.module.AppModule;
import com.app.int_a.giantbombforandroid.main.data.module.AppModule_ProvideApplicationFactory;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule_ProvideGsonFactory;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule_ProvideHttpCacheFactory;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule_ProvideOkhttpClientFactory;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule_ProvideRetrofitFactory;
import com.google.gson.Gson;
import dagger.internal.Preconditions;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerNetComponent implements NetComponent {
  private Provider<Gson> provideGsonProvider;

  private Provider<Application> provideApplicationProvider;

  private Provider<Cache> provideHttpCacheProvider;

  private Provider<OkHttpClient> provideOkhttpClientProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private DaggerNetComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideGsonProvider =
        ScopedProvider.create(NetModule_ProvideGsonFactory.create(builder.netModule));

    this.provideApplicationProvider =
        ScopedProvider.create(AppModule_ProvideApplicationFactory.create(builder.appModule));

    this.provideHttpCacheProvider =
        ScopedProvider.create(
            NetModule_ProvideHttpCacheFactory.create(
                builder.netModule, provideApplicationProvider));

    this.provideOkhttpClientProvider =
        ScopedProvider.create(
            NetModule_ProvideOkhttpClientFactory.create(
                builder.netModule, provideHttpCacheProvider));

    this.provideRetrofitProvider =
        ScopedProvider.create(
            NetModule_ProvideRetrofitFactory.create(
                builder.netModule, provideGsonProvider, provideOkhttpClientProvider));
  }

  @Override
  public Retrofit retrofit() {
    return provideRetrofitProvider.get();
  }

  public static final class Builder {
    private NetModule netModule;

    private AppModule appModule;

    private Builder() {}

    public NetComponent build() {
      if (netModule == null) {
        this.netModule = new NetModule();
      }
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      return new DaggerNetComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder netModule(NetModule netModule) {
      this.netModule = Preconditions.checkNotNull(netModule);
      return this;
    }
  }
}
