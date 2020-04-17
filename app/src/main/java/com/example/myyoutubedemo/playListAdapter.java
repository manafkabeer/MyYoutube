package com.example.myyoutubedemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class playListAdapter extends RecyclerView.Adapter<playListAdapter.NotificationListViewHolder> {

    private Context context;
    String[] title;

//    private playListAdapter.OnClickItemListner onClickListner;
//    List<String> subTitle;

//    List<String>amountList;


    //    public playListAdapter(List<String> serviceIncludeList,List<String> serviceCashList , ) {
//        this.includeList = serviceIncludeList;
//        this.amountList = serviceCashList;
//        this.context = context;
//    }
//
    public playListAdapter(String[] item, Context context) {
        this.context = context;
        this.title = item;

//        this.subTitle= subTitle;
    }

    @NonNull
    @Override
    public playListAdapter.NotificationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_list_adapter, parent, false);
        return new playListAdapter.NotificationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final playListAdapter.NotificationListViewHolder holder, final int pos) {

        String area1 = title[pos];
        final String[] area2 = area1.split("#");

        holder.name.setText(area2[0]);
        holder.publisher.setText(area2[2]);
        holder.year.setText(area2[3]);
        holder.viewers.setText(area2[4]);
        Log.e("name ",area2[0]);
        Log.e("Viewers",area2[4]);
        Log.e("UId",area2[1]);
        Log.e("year",area2[3]);

//        holder.linearVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickListner.onVideoItemClick(title,pos);
//            }
//        });
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
            return title.length;


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
                name = itemView.findViewById(R.id.name_);
                publisher = itemView.findViewById(R.id.publisher_);
                year = itemView.findViewById(R.id.year_);
                viewers = itemView.findViewById(R.id.viwers_);
                image = itemView.findViewById(R.id.image_);
                linearVideo =   itemView.findViewById(R.id.linear_video_);

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

//    public interface OnClickItemListner {
//        void onVideoItemClick(String[] item,int pos);
//    }
//    public void setVideoItemClickListener(@NonNull playListAdapter.OnClickItemListner onRecyclerViewItemClickListener) {
//        this.onClickListner = onRecyclerViewItemClickListener;
//    }
}



