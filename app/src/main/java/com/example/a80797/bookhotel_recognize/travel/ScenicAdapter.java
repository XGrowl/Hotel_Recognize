package com.example.a80797.bookhotel_recognize.travel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a80797.bookhotel_recognize.R;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;



import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScenicAdapter extends RecyclerView.Adapter<ScenicAdapter.ScenicViewHolder> {
    public static final int mShadowElevationDp = 25;
    private Context mContext;
    public static final int M_SHADOW_ALPHA = (int) (25 * 1f / 100);
    private List<String> mList = new ArrayList<>();

  public interface OnItemClickListener {
        void onItemClick();
    }
    public ScenicAdapter(List<String> mList)
    {
        this.mList=mList;
    }
    private ScenicAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(ScenicAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public ScenicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {

            mContext = parent.getContext();
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenic_item, parent, false);
        ScenicViewHolder holder = new ScenicViewHolder(v);
        int mRadius = QMUIDisplayHelper.dp2px(mContext, 15);
//        holder.ImageView1.setRadiusAndShadow(mRadius,
//                QMUIDisplayHelper.dp2px(mContext, mShadowElevationDp),
//                M_SHADOW_ALPHA);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScenicViewHolder holder, int position) {
        int i = position % 6;
        Resources resources = mContext.getResources();

        holder.ImageView1.setImageResource(resources.getIdentifier("tour" + (i % 7) , "drawable", mContext.getPackageName()));

        holder.ImageView2.setImageResource(resources.getIdentifier("tour" + ((i+1) % 7) , "drawable", mContext.getPackageName()));


        holder.ImageView3.setImageResource(resources.getIdentifier("tour" + ((i+2) % 7) , "drawable", mContext.getPackageName()));
        holder.scenicTitle.setText(mList.get(i).toString());
        if(onItemClickListener!=null) {

            holder.tourRelative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onItemClick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ScenicViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout tourRelative;
        ImageView ImageView1;
        ImageView ImageView2;
        ImageView ImageView3;
        TextView scenicTitle;

        public ScenicViewHolder(@NonNull View itemView) {
            super(itemView);
            tourRelative=itemView.findViewById(R.id.tour_relative);
            ImageView1 = itemView.findViewById(R.id.image1);
            ImageView2 = itemView.findViewById(R.id.image2);
            ImageView3= itemView.findViewById(R.id.image3);
            scenicTitle = itemView.findViewById(R.id.scenic_title);


        }
    }



}
