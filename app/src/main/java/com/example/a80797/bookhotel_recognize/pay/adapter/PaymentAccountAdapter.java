package com.example.a80797.bookhotel_recognize.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentAccountAdapter extends RecyclerView.Adapter<PaymentAccountAdapter.PaymentAccountViewHolder> {
    List<String> list=new ArrayList();
    Context mContext;
    public PaymentAccountAdapter(List<String> list)
    {
        this.list=list;
    }

    @NonNull
    @Override
    public PaymentAccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null) {
            mContext = parent.getContext();

        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.chosen_pay_type_item,parent,false);
        return new PaymentAccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAccountViewHolder holder, int position) {
            holder.paymentAccountTypeText.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PaymentAccountViewHolder extends RecyclerView.ViewHolder {
        TextView paymentAccountTypeText;
        public PaymentAccountViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentAccountTypeText=itemView.findViewById(R.id.payment_account_type_text);
        }
    }


}
