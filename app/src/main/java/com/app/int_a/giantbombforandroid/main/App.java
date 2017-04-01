package com.app.int_a.giantbombforandroid.main;

import android.app.Application;

import com.app.int_a.giantbombforandroid.main.data.component.DaggerNetComponent;
import com.app.int_a.giantbombforandroid.main.data.component.NetComponent;
import com.app.int_a.giantbombforandroid.main.data.module.AppModule;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule;

/**
 * Created by Anthony on 3/4/2017.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                //.netModule(new NetModule("http://jsonplaceholder.typicode.com/"))
                .netModule(new NetModule("http://www.giantbomb.com/"))
                .build();
    }

    public NetComponent getmNetComponent(){
        return mNetComponent;
    }
}
