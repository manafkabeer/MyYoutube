package com.example.myyoutubedemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.NotificationListViewHolder> {

    private Context context;
    String[] title;
    int[] images;
    private HomeRecyclerAdapter.OnClickItemListner onClickListner;
//    List<String> subTitle;


//
    public HomeRecyclerAdapter(String[] item,int[] images, Context context) {
        this.context = context;
        this.title = item;
        this.images = images;

    }

    @NonNull
    @Override
    public HomeRecyclerAdapter.NotificationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_adapter, parent, false);
        return new HomeRecyclerAdapter.NotificationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeRecyclerAdapter.NotificationListViewHolder holder, final int pos) {

        String area1 = title[pos];
        final String[] area2 = area1.split("#");
        holder.image.setImageDrawable(context.getResources().getDrawable(images[pos]));

        holder.name.setText(area2[0]);
        holder.publisher.setText(area2[2]);
        holder.year.setText(area2[3]);
        holder.viewers.setText(area2[4]);
        Log.e("name ",area2[0]);
        Log.e("Viewers",area2[4]);
        Log.e("UId",area2[1]);
        Log.e("year",area2[3]);

        holder.linearVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListner.onVideoItemClick(title,pos,area2[1]);
            }
        });
//        holder.amount.setText(("₹ ").concat(serviceIncludedList.get(pos).getServiceFee()).concat("/-"));
//        Glide.with(context)
//                .load(serviceIncludedList.get(pos).getServiceImage())
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                //.fitCenter()
//                .into(holder.pngService);


    }


    @Override
    public int getItemCount() {
        try {
            return images.length;


        } catch (Throwable e) {
            e.printStackTrace();
            return 0;
        }
    }


    public class NotificationListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, publisher, year, viewers;
        ImageView image;

        LinearLayout linearVideo;
//        ImageView pngService;

        private NotificationListViewHolder(View itemView) {
            super(itemView);
            try {
                name = itemView.findViewById(R.id.name);
                publisher = itemView.findViewById(R.id.publisher);
                year = itemView.findViewById(R.id.year);
                viewers = itemView.findViewById(R.id.viwers);
                image = itemView.findViewById(R.id.image_view);
                linearVideo =   itemView.findViewById(R.id.linear_video);

//                amount        =   itemView.findViewById(R.id.text_include_cash);
//                pngService      =   itemView.findViewById(R.id.service_png);


            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {


        }


    }


    public interface OnClickItemListner {
        void onVideoItemClick(String[] item,int pos,String uid);
    }

    public void setVideoItemClickListener(@NonNull HomeRecyclerAdapter.OnClickItemListner onRecyclerViewItemClickListener) {
        this.onClickListner = onRecyclerViewItemClickListener;
    }
}


