package com.app.int_a.giantbombforandroid.main.mainscreen;

import com.app.int_a.giantbombforandroid.main.model.Result;
import com.app.int_a.giantbombforandroid.main.model.Video;

import java.util.List;

/**
 * Created by Anthony on 3/4/2017.
 */

public interface MainScreenContract {
    
    interface View{
        void showVideos(Video Video);

        void showError(String message);

        void showComplete();
    }

    interface Presenter{
        void loadVideo();
    }
}