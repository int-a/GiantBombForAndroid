package com.app.int_a.giantbombforandroid.main.mainscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.int_a.giantbombforandroid.R;
import com.app.int_a.giantbombforandroid.main.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Anthony on 4/3/2017.
 */

public class MainScreenRecyclerAdapter extends RecyclerView.Adapter<MainScreenRecyclerAdapter.ViewHolder> {

    private List<Result> myDataset;
    private Context myContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailView;
        public TextView videoTitle;

        public ViewHolder(View v) {
            super(v);
            thumbnailView = (ImageView) v.findViewById(R.id.thumbnail);
            videoTitle = (TextView) v.findViewById(R.id.video_title_view);
        }
    }

    // TODO: Should I make the list contain Video/Result objects and pull the data from that?
    public MainScreenRecyclerAdapter(List<Result> dataset, Context context) {
        myDataset = dataset;
        myContext = context;
    }

    // Create new views
    @Override
    public MainScreenRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(myContext)
                .inflate(R.layout.thumbnail_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from dataset at this position
        // - replace the contents of hte view with that element

        Result currentVideo = myDataset.get(position);

        String imageUrl =currentVideo.getImage().getMediumUrl();
        Timber.d("Image URL: " + imageUrl);

        ImageView imageView = holder.thumbnailView;
        TextView videoTitle = holder.videoTitle;

        Picasso.with(myContext).load(imageUrl).into(imageView);
        videoTitle.setText(currentVideo.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myDataset.size();
    }
}
