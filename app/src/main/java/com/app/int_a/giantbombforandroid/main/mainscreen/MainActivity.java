package com.app.int_a.giantbombforandroid.main.mainscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.int_a.giantbombforandroid.R;
import com.app.int_a.giantbombforandroid.main.App;
import com.app.int_a.giantbombforandroid.main.data.component.DaggerMainScreenComponent;
import com.app.int_a.giantbombforandroid.main.data.module.MainScreenModule;
import com.app.int_a.giantbombforandroid.main.model.Result;
import com.app.int_a.giantbombforandroid.main.model.Video;

import java.util.ArrayList;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    ArrayList<Result> list = new ArrayList<>();

    // Objects for RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    @Inject
    MainScreenPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree() {
            // Add the line number to the tag
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                return super.createStackElementTag(element) + ':' + element.getLineNumber();
            }
        });

        DaggerMainScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        //Call the method in MainPresenter to make Network Request
        mainPresenter.loadVideo();

        Timber.d("Array size: " + list.size());
    }

    @Override
    public void showVideos(Video video){
        // Loop through the posts, get the title of the post, and add it to our list object
        // TODO: Simplify these references with a variable?
        for(int i = 0; i < video.getResults().size(); i++){
            Result currentVideo = video.getResults().get(i);

            // Filter out Premium videos since these would require authentication
            if(currentVideo.getVideoType() != null && !currentVideo.getVideoType().equals("Premium")) {
                list.add(currentVideo);
                Timber.d("List item " + i + " = " + list.get(i));
            }
        }

        // RecyclerView implementation
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_list);
        recyclerLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new MainScreenRecyclerAdapter(list, this.getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);
        // set to true because all images will be the same size
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void showError(String message){
        // Show error message text as a Toast message
        Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();
        Timber.e("Error: " + message);
    }

    @Override
    public void showComplete(){
        // Show completed Toast message
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
    }
}
