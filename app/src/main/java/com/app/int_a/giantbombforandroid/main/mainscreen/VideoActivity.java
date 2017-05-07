package com.app.int_a.giantbombforandroid.main.mainscreen;

import android.content.Intent;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;

import com.app.int_a.giantbombforandroid.BuildConfig;
import com.app.int_a.giantbombforandroid.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;

import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by Anthony on 4/29/2017.
 */

public class VideoActivity extends AppCompatActivity {

    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

        // Get the Intent that started this activity and extract the video url
        Intent intent = getIntent();
        String mp4Url = intent.getStringExtra(MainActivity.VIDEO_URL);

        // Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        // Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();

        // Create the player
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector); // no LoadControl?
        simpleExoPlayerView = new SimpleExoPlayerView(this);
        simpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.player_view);

        // Set media controller
        simpleExoPlayerView.setUseController(true);
        simpleExoPlayerView.requestFocus();

        // Bind player to the view
        simpleExoPlayerView.setPlayer(exoPlayer);

        // Create Uri from video location
        // TODO: should this be in some network class? Should I be appending APIKEY here?
        Uri mp4Uri = Uri.parse(mp4Url + "?api_key=" + BuildConfig.GIANTBOMB_API_KEY);
        Timber.v("Video url with api key: " + mp4Uri.toString());

        // Create another bandwidth meter for bandwidth during playback (not strictly necessary)
        DefaultBandwidthMeter playbackBandwidthMeter = new DefaultBandwidthMeter();

        // DataSourceFactory to produce DataSource instances through which media data is loaded
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "GiantBombForAndroid"),
                playbackBandwidthMeter);

        // Produces Extractor instances for parsing the media data
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        ExtractorMediaSource.EventListener eventListener = new ExtractorMediaSource.EventListener() {
            @Override
            public void onLoadError(IOException error) {
                Timber.e("Error loading video from source");
            }
        };

        final MediaSource videoSource = new ExtractorMediaSource(mp4Uri,
                dataSourceFactory,
                extractorsFactory,
                mainHandler,
                eventListener);

        exoPlayer.prepare(videoSource);

        exoPlayer.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onLoadingChanged(boolean isLoading) {
                Timber.v("Listener-onLoadingChanged...");

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Timber.v("Listener-onPlayerStateChanged...");

            }

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {
                Timber.v("Listener-onTimelineChanged...");

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                // TODO: Do I need anything here?
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Timber.v("Listener-onPlayerError...");
                exoPlayer.stop();
                exoPlayer.prepare(videoSource);
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPositionDiscontinuity() {
                Timber.v("Listener-onPositionDiscontinuity...");

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                // TODO: Do I need anything here?
            }
        });
        exoPlayer.setPlayWhenReady(true);
















        /*
        surfaceView= (SurfaceView) findViewById(R.id.surfaceView);

        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:40.0) Gecko/20100101 Firefox/40.0";
        String url = "http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_5mb.mp4";
        Allocator allocator = new DefaultAllocator(minBufferMs);
        MediaSource mediaSource = new ExtractorMediaSource(
                Uri.parse(url),
                new DefaultDataSourceFactory(this, userAgent, null),
                new DefaultExtractorsFactory(), null, null);

        if (exoPlayer == null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, null);
        }


        ExtractorSampleSource sampleSource = new ExtractorSampleSource( Uri.parse(url), dataSource, allocator,
                BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);

        MediaCodecVideoTrackRenderer videoRenderer = new
                MediaCodecVideoTrackRenderer(this, sampleSource, MediaCodecSelector.DEFAULT,
                MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);

        MediaCodecAudioTrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource, MediaCodecSelector.DEFAULT);

        exoPlayer = ExoPlayer.Factory.newInstance(RENDERER_COUNT);
        exoPlayer.prepare(videoRenderer, audioRenderer);
        exoPlayer.sendMessage(videoRenderer,
                MediaCodecVideoTrackRenderer.MSG_SET_SURFACE,
                surfaceView.getHolder().getSurface());
        exoPlayer.setPlayWhenReady(true);
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.v("onDestroy()...");
        exoPlayer.release();

    }
}
