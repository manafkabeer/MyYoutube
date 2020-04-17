package com.example.myyoutubedemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myyoutubedemo.db.myDbAdapter;

import java.io.IOException;

@SuppressWarnings("KotlinInternalInJava")
public class YouTubePlayerActivity extends AppCompatActivity {

    private VideoView youTubePlayerView;
    String videoId="";
    myDbAdapter helper;
    private RecyclerView recycler_view;
//    private FullScreenHelper fullScreenHelper = new FullScreenHelper(YouTubePlayerActivity.this);
    String item ="";
    String url ="https://www.youtube.com/watch?v=";

    MediaPlayer mediaPlayer;
    RelativeLayout relativeLayout;
    ImageView fullscreen;
    TextView name,year,publisher,viewrs;
    playListAdapter recentAdapter;
    private String[] item_1 = new String[]{""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player_activity);
        recycler_view = findViewById(R.id.recyclerPlyList);
        youTubePlayerView = findViewById(R.id.video_view);
        relativeLayout = findViewById(R.id.relative_video);
        fullscreen      =   findViewById(R.id.full_screen);
        name            =   findViewById(R.id.name_player);
        year            =   findViewById(R.id.year_player);
        publisher       =   findViewById(R.id.publisher_player);
        viewrs          =   findViewById(R.id.viwers_player);
        if (getIntent()!=null)
        {
            Intent intent = getIntent();
            item = intent.getStringExtra("item");

            if (item!=null)
            {

                final String[] area2 = item.split("#");

                name.setText(area2[0]);
                publisher.setText(area2[2]);
                year.setText(area2[3]);
                viewrs.setText(area2[4]);
                videoId = area2[1];
            }
        }





        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(youTubePlayerView);
        Uri uri=Uri.parse(videoId);
        youTubePlayerView.setMediaController(mediaController);
        youTubePlayerView.setVideoURI(uri);
        youTubePlayerView.requestFocus();
        youTubePlayerView.start();

        final int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            params.width = metrics.widthPixels;
            params.height = metrics.heightPixels;
            params.leftMargin = 0;
            relativeLayout.setLayoutParams(params);
            fullscreen.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SourceLockedOrientationActivity")
                @Override
                public void onClick(View v) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            });

        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            params.width = (int)(metrics.widthPixels);
            params.height = (int)(250*metrics.density);
            relativeLayout.setLayoutParams(params);
            fullscreen.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SourceLockedOrientationActivity")
                @Override
                public void onClick(View v) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            });
        }
// MediaController containing controls for a MediaPlayer
        helper = new myDbAdapter(this);
//
        item_1  =   helper.getSearchResultFromDb("1");
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(YouTubePlayerActivity.this, RecyclerView.VERTICAL, false);
        recycler_view.setLayoutManager(layoutManager1);
        recycler_view.setHasFixedSize(true);
        recycler_view.setNestedScrollingEnabled(false);
        recentAdapter = new playListAdapter(item_1, YouTubePlayerActivity.this);
        recycler_view.setAdapter(recentAdapter);
//        recentAdapter.setVideoItemClickListener((playListAdapter.OnClickItemListner) YouTubePlayerActivity.this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();

    }
}
