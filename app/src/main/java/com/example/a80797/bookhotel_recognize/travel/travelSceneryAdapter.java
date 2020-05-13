package com.example.a80797.bookhotel_recognize.travel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class travelSceneryAdapter extends RecyclerView.Adapter<travelSceneryAdapter.travelSceneryViewHolder> {
    public static final int mShadowElevationDp = 25;
    private Context mContext;
    public static final int M_SHADOW_ALPHA = (int) (25 * 1f / 100);
    private List<String> mList = new ArrayList<>();

    public travelSceneryAdapter(List<String> mList)
    {
        this.mList=mList;
    }
    @NonNull
    @Override
    public travelSceneryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {

            mContext = parent.getContext();
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_scenery_item, parent, false);
        travelSceneryViewHolder holder = new travelSceneryViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull travelSceneryViewHolder holder, int position) {
        int i = position % 6;
        Resources resources = mContext.getResources();

        holder.travelSceneryImage.setImageResource(resources.getIdentifier("tour" + (i % 7) , "drawable", mContext.getPackageName()));
        holder.travelSceneryTitle.setText(mList.get(i));
        holder.textView.setTextColor(randomColor());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class travelSceneryViewHolder extends RecyclerView.ViewHolder {

        ImageView travelSceneryImage;
        TextView travelSceneryTitle;
        TextView textView;

        public travelSceneryViewHolder(@NonNull View itemView) {
            super(itemView);
            //=itemView.findViewById(R.id.tour_relative);
            travelSceneryImage = itemView.findViewById(R.id.travel_scenery_image);
            travelSceneryTitle=itemView.findViewById(R.id.travel_scenery_title);
            textView= itemView.findViewById(R.id.text1);


        }
    }    private int randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        int rgb = Color.rgb(r, g, b);
        return rgb;
    }



}
