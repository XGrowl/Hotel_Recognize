package com.example.a80797.bookhotel_recognize.travel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.view.CircleRelativeLayout;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class travelEvaluationAdapter extends RecyclerView.Adapter<travelEvaluationAdapter.travelEvaluationViewHolder> {
    private Context mContext;
    @NonNull
    @Override
    public travelEvaluationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null)
            mContext=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.resident_evaluation_item,parent,false);
        return new travelEvaluationViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull travelEvaluationViewHolder holder, int position) {
        holder.residentEvaluationTv.setText(randomEvaluatoin());
        holder.residentHeadImg.setColor(randomColor());
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class travelEvaluationViewHolder extends RecyclerView.ViewHolder {
        private TextView residentEvaluationTv;
        private CircleRelativeLayout residentHeadImg;
        public travelEvaluationViewHolder(@NonNull View itemView) {
            super(itemView);
            residentEvaluationTv=itemView.findViewById(R.id.resident_evaluation);
            residentHeadImg=itemView.findViewById(R.id.resident_head);

        }
    }
    public String randomEvaluatoin()
    {
        Random random=new Random();
        int length=random.nextInt(10)+10;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("优秀");
        }
        return builder.toString();
    }
    private int randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        int rgb = Color.rgb(r, g, b);
        return rgb;
    }

}
