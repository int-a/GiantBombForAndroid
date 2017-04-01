package com.app.int_a.giantbombforandroid.main.data.module;

import com.app.int_a.giantbombforandroid.main.mainscreen.MainScreenContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainScreenModule_ProvidesMainScreenContractViewFactory
    implements Factory<MainScreenContract.View> {
  private final MainScreenModule module;

  public MainScreenModule_ProvidesMainScreenContractViewFactory(MainScreenModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public MainScreenContract.View get() {
    return Preconditions.checkNotNull(
        module.providesMainScreenContractView(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MainScreenContract.View> create(MainScreenModule module) {
    return new MainScreenModule_ProvidesMainScreenContractViewFactory(module);
  }
}
