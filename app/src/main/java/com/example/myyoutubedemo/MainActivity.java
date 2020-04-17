package com.example.myyoutubedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.example.myyoutubedemo.db.myDbAdapter;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements HomeRecyclerAdapter.OnClickItemListner {

    myDbAdapter helper;
    private RecyclerView recycler_view;

    int[] images = {R.drawable.image_1,R.drawable.image_2,R.drawable.image_3,R.drawable.image_4};
    private String[] item_1 = new String[]{""};
    HomeRecyclerAdapter recentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        helper = new myDbAdapter(this);
//
            item_1  =   helper.getItemsFromDb();
//            Log.e("item",helper.getSearchResult());
//
//
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recycler_view.setLayoutManager(layoutManager1);
        recycler_view.setHasFixedSize(true);
        recycler_view.setNestedScrollingEnabled(false);
        recentAdapter = new HomeRecyclerAdapter(item_1,images, MainActivity.this);
        recycler_view.setAdapter(recentAdapter);
        recentAdapter.setVideoItemClickListener(MainActivity.this);








    }


    private void init() {

        recycler_view = findViewById(R.id.recycler_view);

    }

    @Override
    public void onVideoItemClick(String[] item , int pos,String uid) {
        helper.getUpdateTomDb(uid);
        Intent go = new Intent(MainActivity.this,YouTubePlayerActivity.class);
        go.putExtra("item",item[pos]);
        startActivity(go);
    }
}
