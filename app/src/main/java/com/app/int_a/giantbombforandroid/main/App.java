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

    private NetComponent netComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                //.netModule(new NetModule("http://jsonplaceholder.typicode.com/"))
                .netModule(new NetModule("http://www.giantbomb.com/"))
                .build();
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }
}
