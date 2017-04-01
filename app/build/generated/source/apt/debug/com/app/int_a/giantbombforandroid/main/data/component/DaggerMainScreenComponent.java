package com.app.int_a.giantbombforandroid.main.data.component;

import com.app.int_a.giantbombforandroid.main.data.module.MainScreenModule;
import com.app.int_a.giantbombforandroid.main.data.module.MainScreenModule_ProvidesMainScreenContractViewFactory;
import com.app.int_a.giantbombforandroid.main.mainscreen.MainActivity;
import com.app.int_a.giantbombforandroid.main.mainscreen.MainActivity_MembersInjector;
import com.app.int_a.giantbombforandroid.main.mainscreen.MainScreenContract;
import com.app.int_a.giantbombforandroid.main.mainscreen.MainScreenPresenter;
import com.app.int_a.giantbombforandroid.main.mainscreen.MainScreenPresenter_Factory;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerMainScreenComponent implements MainScreenComponent {
  private Provider<Retrofit> retrofitProvider;

  private Provider<MainScreenContract.View> providesMainScreenContractViewProvider;

  private Provider<MainScreenPresenter> mainScreenPresenterProvider;

  private MembersInjector<MainActivity> mainActivityMembersInjector;

  private DaggerMainScreenComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.retrofitProvider =
        new Factory<Retrofit>() {
          private final NetComponent netComponent = builder.netComponent;

          @Override
          public Retrofit get() {
            return Preconditions.checkNotNull(
                netComponent.retrofit(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.providesMainScreenContractViewProvider =
        ScopedProvider.create(
            MainScreenModule_ProvidesMainScreenContractViewFactory.create(
                builder.mainScreenModule));

    this.mainScreenPresenterProvider =
        MainScreenPresenter_Factory.create(
            retrofitProvider, providesMainScreenContractViewProvider);

    this.mainActivityMembersInjector =
        MainActivity_MembersInjector.create(mainScreenPresenterProvider);
  }

  @Override
  public void inject(MainActivity activity) {
    mainActivityMembersInjector.injectMembers(activity);
  }

  public static final class Builder {
    private MainScreenModule mainScreenModule;

    private NetComponent netComponent;

    private Builder() {}

    public MainScreenComponent build() {
      if (mainScreenModule == null) {
        throw new IllegalStateException(MainScreenModule.class.getCanonicalName() + " must be set");
      }
      if (netComponent == null) {
        throw new IllegalStateException(NetComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerMainScreenComponent(this);
    }

    public Builder mainScreenModule(MainScreenModule mainScreenModule) {
      this.mainScreenModule = Preconditions.checkNotNull(mainScreenModule);
      return this;
    }

    public Builder netComponent(NetComponent netComponent) {
      this.netComponent = Preconditions.checkNotNull(netComponent);
      return this;
    }
  }
}
