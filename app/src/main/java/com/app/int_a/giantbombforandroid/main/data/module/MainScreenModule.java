package com.app.int_a.giantbombforandroid.main.data.module;

import com.app.int_a.giantbombforandroid.main.mainscreen.MainScreenContract;
import com.app.int_a.giantbombforandroid.main.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anthony on 3/5/2017.
 */

@Module
public class MainScreenModule {

    private final MainScreenContract.View mView;

    public MainScreenModule(MainScreenContract.View mView){
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainScreenContract.View providesMainScreenContractView(){
        return mView;
    }
}
