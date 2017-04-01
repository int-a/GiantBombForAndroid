package com.app.int_a.giantbombforandroid.main.data.component;

import com.app.int_a.giantbombforandroid.main.data.module.AppModule;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Anthony on 3/4/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    Retrofit retrofit();
}