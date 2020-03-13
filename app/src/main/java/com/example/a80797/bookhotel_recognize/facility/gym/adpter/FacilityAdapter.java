package com.example.a80797.bookhotel_recognize.facility.gym.adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.facility.gym.model.facility;
import com.example.a80797.bookhotel_recognize.view.CircleRelativeLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder> {
    private List<facility> mList=new ArrayList<>();
    private Context mContext;
    public FacilityAdapter(List<facility> list)
    {
        mList=list;
    }

    @NonNull
    @Override
    public FacilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
            mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.gym_facility_item,parent,false);

        return new FacilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityViewHolder holder, int position) {
        int reverse=mList.get(position).getFacilityReserve();
        int use=mList.get(position).getFacilityUser();
        if (reverse==use)
            holder.gymFacilityRelative.setColor(Color.RED);
        else
            holder.gymFacilityRelative.setColor(Color.GREEN);
        holder.gymFacilityReverse.setText(String.valueOf(reverse));
        holder.gymFacilityName.setText(mList.get(position).getFacilityName());
        holder.gymFacilityUse.setText(String.valueOf(use));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class FacilityViewHolder extends RecyclerView.ViewHolder {
        private CircleRelativeLayout gymFacilityRelative;
        private TextView gymFacilityName;
        private TextView gymFacilityUse;
        private TextView gymFacilityReverse;
        public FacilityViewHolder(@NonNull View itemView) {
            super(itemView);
            gymFacilityRelative=itemView.findViewById(R.id.gym_facility_relative);
            gymFacilityUse=itemView.findViewById(R.id.gym_facility_use);
            gymFacilityName=itemView.findViewById(R.id.gym_facility_name);
            gymFacilityReverse=itemView.findViewById(R.id.gym_facility_reserve);
        }
    }
}
