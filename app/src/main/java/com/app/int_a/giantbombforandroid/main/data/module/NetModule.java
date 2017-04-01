package com.app.int_a.giantbombforandroid.main.data.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.app.int_a.giantbombforandroid.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anthony on 3/2/2017.
 */
@Module
public class NetModule {
    // Maybe one day this will be a view object to contain a video?
    // Maybe it will become a dependency and will be injected via
    // another module? Let Dagger find a view object and create it
    String mBaseUrl;

    private final String LOG_TAG = NetModule.class.getSimpleName();
    private String apiKey;

    public NetModule(String mBaseUrl){
        this.mBaseUrl = mBaseUrl;
        Log.d(LOG_TAG, "Base URL: " + mBaseUrl);
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);

        // Get api key from application context
        apiKey = application.getString(R.string.giant_bomb_api_key);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient (Cache cache){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);

        // Add GiantBomb.com api key to request
        // Add json parameter because all requests will expect json
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", apiKey)
                        .addQueryParameter("format","json")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Log.d(LOG_TAG,"URL:" + url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}