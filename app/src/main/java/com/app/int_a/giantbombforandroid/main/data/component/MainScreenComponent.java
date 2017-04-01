package com.app.int_a.giantbombforandroid.main.data.component;

import com.app.int_a.giantbombforandroid.main.data.module.MainScreenModule;
import com.app.int_a.giantbombforandroid.main.mainscreen.MainActivity;
import com.app.int_a.giantbombforandroid.main.util.CustomScope;

import dagger.Component;

/**
 * Created by Anthony on 3/5/2017.
 */

@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
