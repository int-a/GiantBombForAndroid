package com.app.int_a.giantbombforandroid.main.mainscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.int_a.giantbombforandroid.R;
import com.app.int_a.giantbombforandroid.main.App;
import com.app.int_a.giantbombforandroid.main.data.component.DaggerMainScreenComponent;
import com.app.int_a.giantbombforandroid.main.data.module.MainScreenModule;
import com.app.int_a.giantbombforandroid.main.model.Video;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

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
        listView = (ListView) findViewById(R.id.my_list);
        list = new ArrayList<>();

        DaggerMainScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        //Call the method in MainPresenter to make Network Request
        mainPresenter.loadVideo();
    }

    @Override
    public void showVideos(Video video){
        // Loop through the posts, get the title of the post, and add it to our list object
        // TODO: Simplify these references with a variable?
        for(int i = 0; i < video.getResults().size(); i++){
            // TODO: add second for loop, or simplyfy and get rid of Video object
            list.add(video.getResults().get(i).getSiteDetailUrl());
            //list.add(video.get(i).getSiteDetailUrl());
            Log.d(LOG_TAG, "List item " + i + " = " + list.get(i));
        }


        //Create the array adapter and set it to the list
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void showError(String message){
        // Show error message text as a Toast message
        Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();
        Log.e(LOG_TAG, "Error: " + message);
    }

    @Override
    public void showComplete(){
        // Show completed Toast message
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
    }
}
