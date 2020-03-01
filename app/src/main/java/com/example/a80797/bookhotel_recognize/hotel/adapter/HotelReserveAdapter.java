package com.example.a80797.bookhotel_recognize.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.view.RoundImageView;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HotelReserveAdapter extends RecyclerView.Adapter<HotelReserveAdapter.hotelReserveViewHolder> {
    private Context mContext;
    @NonNull
    @Override
    public hotelReserveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null)
            mContext=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_reserve_item, parent, false);
        return new hotelReserveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hotelReserveViewHolder holder, int position) {
        holder.hotelReserveFacalityPriceTv.setText(randomPrice(false));
        holder.hotelReserveFacalityOriginalPriceTv.setText(randomPrice(true));
        int resourceId = mContext.getResources().getIdentifier("image"+((position%5)+1), "drawable", mContext.getPackageName());
        holder.hotelReserveFacalityImage.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class hotelReserveViewHolder extends RecyclerView.ViewHolder {
        private RoundImageView hotelReserveFacalityImage;
        private TextView hotelReserveFacalityOriginalPriceTv;
        private TextView hotelReserveFacalityPriceTv;

        public hotelReserveViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelReserveFacalityImage = itemView.findViewById(R.id.hotel_reserve_facality_image);
            hotelReserveFacalityOriginalPriceTv = itemView.findViewById(R.id.hotel_reserve_facality_original_price);
            hotelReserveFacalityPriceTv = itemView.findViewById(R.id.hotel_reserve_facality_price);
        }
    }

    public String randomPrice(boolean temp) {
        int price;
        Random random = new Random();
        if (true)
            price = random.nextInt(899) + 200;
        else
            price = random.nextInt(100) + 100;
        StringBuilder builder = new StringBuilder();
        builder.append("￥").append(price);
        return builder.toString();
    }
}
