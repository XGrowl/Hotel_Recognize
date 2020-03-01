package com.example.a80797.bookhotel_recognize.hotel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;

import org.w3c.dom.Text;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResidentEvaluationAdapter extends RecyclerView.Adapter<ResidentEvaluationAdapter.ResidentEvaluationViewHolder> {
    private Context mContext;
    @NonNull
    @Override
    public ResidentEvaluationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null)
            mContext=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.resident_evaluation_item,parent,false);
        return new ResidentEvaluationViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ResidentEvaluationViewHolder holder, int position) {
        holder.residentEvaluationTv.setText(randomEvaluatoin());
        holder.residentHeadImg.setBackgroundColor(randomColor());
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ResidentEvaluationViewHolder extends RecyclerView.ViewHolder {
        private TextView residentEvaluationTv;
        private ImageView residentHeadImg;
        public ResidentEvaluationViewHolder(@NonNull View itemView) {
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
