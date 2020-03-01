package com.example.a80797.bookhotel_recognize.Tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TourCardSwipeAdapter extends RecyclerView.Adapter<TourCardSwipeAdapter.TourCardSwipeViewHolder> {

    private Context mContext;
    private List colorList=new ArrayList();
    @NonNull
    @Override
    public TourCardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null)
            mContext=parent.getContext();
//        initData();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_card_swipe_item,parent,false);

        return new TourCardSwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourCardSwipeViewHolder holder, int position) {
        holder.tourItemLinear.setBackgroundColor((Integer) colorList.get(position));

    }

    @Override
    public int getItemCount() {
        return 5;
    }
  public class TourCardSwipeViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout tourItemLinear;
     //   private Button tourDetailBt;
      private RoundImageView tourItemImage;
        public ImageView dislikeImageView;
        public ImageView likeImageView;
        public TourCardSwipeViewHolder(@NonNull View itemView) {
            super(itemView);
          //  tourDetailBt=itemView.findViewById(R.id.tour_card_swip_detail_button);
            tourItemLinear=itemView.findViewById(R.id.tour_item_linear);
            tourItemImage=itemView.findViewById(R.id.tour_card_swipe_image);
            dislikeImageView=itemView.findViewById(R.id.iv_dislike);
            likeImageView=itemView.findViewById(R.id.iv_like);
        }
    }
    public void getTourCardSwipeViewHolder()
    {
    
    }
}
