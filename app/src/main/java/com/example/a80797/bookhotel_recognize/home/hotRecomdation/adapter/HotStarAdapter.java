package com.example.a80797.bookhotel_recognize.home.hotRecomdation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HotStarAdapter extends RecyclerView.Adapter<HotStarAdapter.HotStarViewHoler> {
    public static final int mShadowElevationDp = 25;
    private Context mContext;
    public static final int M_SHADOW_ALPHA = (int) (25 * 1f / 100);

    @NonNull
    @Override
    public HotStarViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.star_hot_recondate_item, parent, false);
        HotStarViewHoler holder = new HotStarViewHoler(v);
        int mRadius = QMUIDisplayHelper.dp2px(mContext, 15);
        holder.hotLinear.setRadiusAndShadow(mRadius,
                QMUIDisplayHelper.dp2px(mContext, mShadowElevationDp),
                M_SHADOW_ALPHA);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotStarViewHoler holder, int position) {

        int i = position % 5;
        int resourceId = mContext.getResources().getIdentifier("image" + ((i % 5) + 1), "drawable", mContext.getPackageName());
        holder.hotStarImage.setImageResource(resourceId);
        holder.hotStarEvaluateTv.setText(getRangeLengthName("舒适的温州"));
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class HotStarViewHoler extends RecyclerView.ViewHolder {
        private QMUILinearLayout hotLinear;
        private TextView hotStarTitleTv;
        private ImageView hotStarImage;
        private TextView hotStarEvaluateTv;

        public HotStarViewHoler(@NonNull View itemView) {
            super(itemView);
            hotLinear = itemView.findViewById(R.id.hot_line);
            hotStarEvaluateTv = itemView.findViewById(R.id.hot_star_title_evaluate);
            hotStarTitleTv = itemView.findViewById(R.id.hot_star_title_tv);
            hotStarImage = itemView.findViewById(R.id.hot_star_image);
        }
    }

    private String getRangeLengthName(String evaluate) {
        Random random = new Random();
        int length = random.nextInt(10) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(evaluate).append("\n");
        }
        return builder.toString();
    }
}
