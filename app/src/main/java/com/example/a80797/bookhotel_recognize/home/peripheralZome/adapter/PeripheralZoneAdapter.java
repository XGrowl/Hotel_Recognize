package com.example.a80797.bookhotel_recognize.home.peripheralZome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PeripheralZoneAdapter extends RecyclerView.Adapter<PeripheralZoneAdapter.peripheralViewHolder> {
    public static final int mShadowElevationDp=50;
    private Context mContext;
    public static final int M_SHADOW_ALPHA = (int) (50 * 1f / 100);

    @NonNull
    @Override
    public peripheralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
            mContext=parent.getContext();
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.peripheral_item,parent,false);
        peripheralViewHolder holder= new peripheralViewHolder(v);
        int mRadius= QMUIDisplayHelper.dp2px(mContext, 15);
        holder.mLinearLayout.setRadiusAndShadow(mRadius,
                QMUIDisplayHelper.dp2px(mContext, mShadowElevationDp),
                M_SHADOW_ALPHA);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull peripheralViewHolder holder, int position) {
        holder.peripheralZomeTv.setText(new StringBuilder().append("item").append(position));

        if(position%2!=0)
            holder.peripheralZomeImg.setImageResource(R.drawable.bg2);
    }


    @Override
    public int getItemCount() {
        return 10;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class peripheralViewHolder extends RecyclerView.ViewHolder {
        private TextView peripheralZomeTv;
        private ImageView peripheralZomeImg;
        private QMUILinearLayout mLinearLayout;
        public peripheralViewHolder(@NonNull View itemView) {
            super(itemView);
            mLinearLayout=itemView.findViewById(R.id.peripheral_linear);
            peripheralZomeImg=itemView.findViewById(R.id.peripheral_image);
            peripheralZomeTv=itemView.findViewById(R.id.periphral_tv);
        }
    }
}
