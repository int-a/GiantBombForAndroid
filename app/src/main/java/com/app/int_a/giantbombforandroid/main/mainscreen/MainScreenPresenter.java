package com.app.int_a.giantbombforandroid.main.mainscreen;

import com.app.int_a.giantbombforandroid.main.model.Post;
import com.app.int_a.giantbombforandroid.main.model.Result;
import com.app.int_a.giantbombforandroid.main.model.Video;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Anthony on 3/6/2017.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {

    Retrofit retrofit;
    MainScreenContract.View view;

    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View view){
        this.retrofit = retrofit;
        this.view = view;
    }


    @Override
    public void loadVideo(){
        retrofit.create(VideoService.class).getVideoList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Video>(){
                    @Override
                    public void onCompleted(){
                        view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e){
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Video video){
                        view.showVideos(video);
                    }
                });
    }

    private interface VideoService {
        @GET("/api/videos")
        Observable<Video> getVideoList();
    }
}
