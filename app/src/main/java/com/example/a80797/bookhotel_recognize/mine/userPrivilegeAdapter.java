package com.example.a80797.bookhotel_recognize.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class userPrivilegeAdapter extends RecyclerView.Adapter<userPrivilegeAdapter.userPrivilegeViewHolder> {
    Context mContext;
    List<String> list=new ArrayList<>();
    public userPrivilegeAdapter(List<String> list)
    {
        this.list=list;
    }

    @NonNull
    @Override
    public userPrivilegeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
            mContext=parent.getContext();
        View view=LayoutInflater.from(mContext).inflate(R.layout.user_privilege_item,parent,false);
        return new userPrivilegeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userPrivilegeViewHolder holder, int position) {
        holder.userPrivilegeText.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class userPrivilegeViewHolder extends RecyclerView.ViewHolder {
        TextView userPrivilegeText;
        public userPrivilegeViewHolder(@NonNull View itemView) {
            super(itemView);
            userPrivilegeText=itemView.findViewById(R.id.user_privilege_text);
        }
    }
}
