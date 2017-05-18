package com.app.int_a.giantbombforandroid.main;

import android.app.Application;

import com.app.int_a.giantbombforandroid.main.data.component.DaggerNetComponent;
import com.app.int_a.giantbombforandroid.main.data.component.NetComponent;
import com.app.int_a.giantbombforandroid.main.data.module.AppModule;
import com.app.int_a.giantbombforandroid.main.data.module.NetModule;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by Anthony on 3/4/2017.
 */

public class App extends Application {

    private NetComponent netComponent;
    protected String userAgent;

    @Override
    public void onCreate(){
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                //.netModule(new NetModule("http://jsonplaceholder.typicode.com/"))
                .netModule(new NetModule())
                .build();

        // ExoPlayer
        userAgent = Util.getUserAgent(this, "GiantBombForAndroid");
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }

    public DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(this, bandwidthMeter,
                buildHttpDataSourceFactory(bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter);
    }

    public boolean useExtensionRenderers() {
        return false;
        //return BuildConfig.FLAVOR.equals("withExtensions");
    }

}
