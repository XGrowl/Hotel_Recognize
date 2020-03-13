package com.example.a80797.bookhotel_recognize.mine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.view.water.WaterFlake;
import com.example.a80797.bookhotel_recognize.view.water.WaterModel;
import com.sunshine.viewlibrary.WaveView;

import java.util.ArrayList;
import java.util.List;

public class MineActivity extends AppCompatActivity {

    public static final String TAG = "xxxx";
    public static  int NUM = 600;
    WaterFlake mWaterFlake;
    private List<WaterModel> mModelList;
    private WaveView mWaveView;
    RelativeLayout waterRelative;
    RecyclerView userPrivilegeRecycler;
    private List<String> mList=new ArrayList<>();

//    private float wx;
//    private float y;
    private int mPx;
    private int mPy;
//  屏幕的中心点
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine);
        mWaveView = findViewById(R.id.waveView);
        mWaveView.setFlowNum(70);
        mWaveView.setUpSpeed(10);
        mWaveView.setWaveSpeed(10);
        mWaveView.setScore(NUM);
        mWaveView.start();
        mWaterFlake = findViewById(R.id.custom_view);
        waterRelative=findViewById(R.id.water_relative);
        userPrivilegeRecycler=findViewById(R.id.user_privilege_recycler);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        userPrivilegeRecycler.setLayoutManager(layoutManager);

        initMyData();
        userPrivilegeRecycler.setAdapter(new userPrivilegeAdapter(mList));
        getLocation();
        initData();
        mWaterFlake.setOnWaterItemListener(new WaterFlake.OnWaterItemListener() {
            @Override
            public void onItemClick(WaterModel pos) {
              //  getLocation();
//                获得 mWaveView的中心坐标
                Toast.makeText(MineActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                NUM+=5;
                mWaveView.setScore(NUM);
            }
        });


    }


    private void getLocation() {
//        int[] location=new int[2];
//        mWaveView.getLocationInWindow(location);
//        int x=location[0];
//        y = location[1];
//        Log.i(TAG, "onItemClick: x："+x+" y:"+y);
//        wx = mWaveView.getRight()-mWaveView.getLeft();
//
//        Log.i(TAG, "onItemClick: 图片各个角Left："+mWaveView.getLeft()+"Right："+mWaveView.getRight()+"Top："+mWaveView.getTop()+"Bottom："+mWaveView.getBottom());
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeigh = dm.heightPixels;
        //各取以二
        mPx = screenWidth / 2;
        mPy = screenHeigh/2;
    }

    private void initData() {

        mModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mModelList.add(new WaterModel("sds"));
        }

                //此处目前写死坐标，后期可以获取小树的坐标添加进去
                mWaterFlake.setModelList(mModelList, mPx,mPy/2);



    }
    public void initMyData()
    {

        mList.add("海绵宝宝");
        mList.add("小蜗");
        mList.add("蟹老板");
        mList.add("痞老板");
        mList.add("莉萨");
        mList.add("派大星");
        mList.add("章鱼哥");
        mList.add("海神");
    }

}
