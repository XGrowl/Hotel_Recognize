package com.example.a80797.bookhotel_recognize.Tour;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.hotel.hotelDetailActivity;
import com.example.a80797.bookhotel_recognize.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class TourFragment extends Fragment {

    private List<Integer> list = new ArrayList<>();


    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tour, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();


    }

    private void initView() {
        final RecyclerView recyclerView = (RecyclerView) requireActivity().findViewById(R.id.tour_recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new TourCardSwipeAdapter());
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<Integer>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                TourCardSwipeAdapter.tourCardSwipeViewHolder myHolder = (TourCardSwipeAdapter.tourCardSwipeViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer o, int direction) {
                TourCardSwipeAdapter.tourCardSwipeViewHolder myHolder = (TourCardSwipeAdapter.tourCardSwipeViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
                Toast.makeText(requireActivity(), direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(requireActivity(), "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 3000L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void initData() {
        list.add(R.drawable.image5);
        list.add(R.drawable.image4);
        list.add(R.drawable.image3);
        list.add(R.drawable.image2);
        list.add(R.drawable.image1);

    }

    public class TourCardSwipeAdapter extends RecyclerView.Adapter<TourCardSwipeAdapter.tourCardSwipeViewHolder> {

        private Context mContext;
//        private List colorList=new ArrayList();

        @NonNull
        @Override
        public tourCardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (mContext==null)
                mContext=parent.getContext();
//        initData();
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_card_swipe_item,parent,false);

            return new tourCardSwipeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull tourCardSwipeViewHolder holder, int position) {
            holder.tourItemImage.setImageResource(list.get(position));
            holder.tourdetailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, hotelDetailActivity.class);
                    intent.putExtra("hotel_message","hotelId");
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class tourCardSwipeViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout tourItemLinear;
         //   private Button tourDetailBt;
            private RoundImageView tourItemImage;
            public ImageView dislikeImageView;
            public ImageView likeImageView;
            public Button tourdetailBtn;
            public tourCardSwipeViewHolder(@NonNull View itemView) {
                super(itemView);
         //       tourDetailBt=itemView.findViewById(R.id.tour_card_swip_detail_button);
                tourItemLinear=itemView.findViewById(R.id.tour_item_linear);
                tourItemImage=itemView.findViewById(R.id.tour_card_swipe_image);
                dislikeImageView=itemView.findViewById(R.id.iv_dislike);
                likeImageView=itemView.findViewById(R.id.iv_like);
                tourdetailBtn=itemView.findViewById(R.id.tour_card_swip_detail_button);

            }
        }
    }




}
