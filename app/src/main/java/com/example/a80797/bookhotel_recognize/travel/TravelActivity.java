package com.example.a80797.bookhotel_recognize.travel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.hotel.hotelDetailActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends AppCompatActivity {

    Banner travelBanner;
    RecyclerView travelRecycler;
    List<String> mList=new ArrayList<>();
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        travelRecycler=findViewById(R.id.travel_recycler);
        travelBanner=findViewById(R.id.travel_banner);
        ScenicAdapter adapter = new ScenicAdapter(mList);
        travelRecycler.setAdapter(adapter);
        mContext=this;
        initBanner();
        initList();
        travelRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new ScenicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                Intent intent=new Intent(mContext,travelDetailActivity.class);
                startActivity(intent);
            }
        });
    }
    public void initBanner()
    {
        List images = new ArrayList();
        images.add(R.drawable.tour0);
        images.add(R.drawable.tour1);
        images.add(R.drawable.tour2);
        images.add(R.drawable.tour3);
        images.add(R.drawable.tour4);

        travelBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(TravelActivity.this).load(path).into(imageView);
            }
        });
        travelBanner.setImages(images);
        travelBanner.start();
    }  public void initList() {
        mList.add("九寨沟");
        mList.add("雁荡山");
        mList.add("楠溪江");
        mList.add("江心屿");
        mList.add("嘉兴书院");
        mList.add("水上世界");

    }
}
