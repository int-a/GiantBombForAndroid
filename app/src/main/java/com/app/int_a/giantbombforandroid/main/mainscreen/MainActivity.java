package com.app.int_a.giantbombforandroid.main.mainscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.int_a.giantbombforandroid.BuildConfig;
import com.app.int_a.giantbombforandroid.R;
import com.app.int_a.giantbombforandroid.main.App;
import com.app.int_a.giantbombforandroid.main.SettingsActivity;
import com.app.int_a.giantbombforandroid.main.data.component.DaggerMainScreenComponent;
import com.app.int_a.giantbombforandroid.main.data.module.MainScreenModule;
import com.app.int_a.giantbombforandroid.main.model.Result;
import com.app.int_a.giantbombforandroid.main.model.Video;
import com.app.int_a.giantbombforandroid.main.util.RecyclerItemClickListener;

import java.util.ArrayList;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    ArrayList<Result> list = new ArrayList<>();

    // Objects for RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    // Constant key for video url string
    public static final String URI_LIST_EXTRA = "com.app.int_a.giantbombforandroid.main.mainscreen.VIDEO_URL";

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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.preferences:
                // Code to show SettingsActivity
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showVideos(Video video){
        // Loop through the posts, get the title of the post, and add it to our list object
        for(int i = 0; i < video.getResults().size(); i++){
            Result currentVideo = video.getResults().get(i);

            // Filter out Premium videos since these would require authentication
            if(currentVideo.getVideoType() != null && !currentVideo.getVideoType().equals("Premium")) {
                list.add(currentVideo);
                Timber.d("List item " + i + " = " + list.get(list.size()-1));
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

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // Get current video quality setting
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        String videoQuality = sharedPref.getString("pref_video_quality", "HD");
                        String videoUrl;

                        switch(videoQuality) {
                            case "Low":
                                videoUrl = list.get(position).getLowUrl();
                                break;
                            case "High":
                                videoUrl = list.get(position).getHighUrl();
                                break;
                            default:
                                videoUrl = list.get(position).getHdUrl();
                                break;
                        }

                        Timber.v("Video url: " + videoUrl);
                        Uri videoUris = Uri.parse(videoUrl + "?api_key=" + BuildConfig.GIANTBOMB_API_KEY);

                        // Create intent to launch VideoActiviy with given video URL
                        // TODO: Is this the right context to include?
                        Intent intent = new Intent(view.getContext(), VideoActivity.class);
                        intent.setAction(VideoActivity.ACTION_VIEW);
                        intent.setData(videoUris);
                        startActivity(intent);
                    }
                })
        );

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
