package com.example.a80797.bookhotel_recognize.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.pay.model.orderPayment;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class orderDetailAdapter extends RecyclerView.Adapter<orderDetailAdapter.orderDetailViewHolder> {
    List<orderPayment> list=new ArrayList<>();
    private Context mContext;
    public orderDetailAdapter(List<orderPayment> list)
    {
        this.list=list;
    }
    @NonNull
    @Override
    public orderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
            mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.order_detail_item,parent,false);
        return new orderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderDetailViewHolder holder, int position) {
        holder.orderDetailDataDescription.setText(list.get(position).getOrderDataDescription());
        holder.orderDetailData.setText(list.get(position).getOrderData());
        holder.orderDetailAccount.setText(list.get(position).getOrderAccount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class orderDetailViewHolder extends RecyclerView.ViewHolder {
        TextView orderDetailData;
        TextView orderDetailDataDescription;
        TextView orderDetailAccount;
        public orderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDetailAccount=itemView.findViewById(R.id.order_detail_account);
            orderDetailData=itemView.findViewById(R.id.order_detail_data);
            orderDetailDataDescription=itemView.findViewById(R.id.order_detail_data_description);
        }
    }


}
