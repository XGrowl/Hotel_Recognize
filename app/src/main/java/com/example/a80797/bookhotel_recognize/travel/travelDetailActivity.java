package com.example.a80797.bookhotel_recognize.travel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a80797.bookhotel_recognize.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class travelDetailActivity extends AppCompatActivity {

    Banner travelBanner;
    RecyclerView travelEvaluteRecycler;
    RecyclerView travelSceneryRecycler;
    List<String> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_travel_detail);
        initList();
        travelEvaluteRecycler=findViewById(R.id.travel_evaluate_recycler);
        travelSceneryRecycler=findViewById(R.id.travel_scenery_recycler);
        travelBanner=findViewById(R.id.travel_scenery_banner);
        travelEvaluteRecycler.setAdapter(new travelEvaluationAdapter());
        travelEvaluteRecycler.setLayoutManager(new LinearLayoutManager(this));
        travelSceneryRecycler.setAdapter(new travelSceneryAdapter(mList));
        travelSceneryRecycler.setLayoutManager(new LinearLayoutManager(this));
        initBanner();

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
                Glide.with(travelDetailActivity.this).load(path).into(imageView);
            }
        });
        travelBanner.setImages(images);
        travelBanner.start();
    }
    public void initList()
    {
        mList.add("四川");
        mList.add("戈壁");
        mList.add("秘境");

    }
}
