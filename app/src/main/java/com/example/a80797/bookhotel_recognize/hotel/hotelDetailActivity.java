package com.example.a80797.bookhotel_recognize.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReserveAdapter;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReserveAdapter2;
import com.example.a80797.bookhotel_recognize.hotel.adapter.ResidentEvaluationAdapter;
import com.example.a80797.bookhotel_recognize.hotel.model.Item;
import com.example.a80797.bookhotel_recognize.view.CustomScrollView;
import com.google.android.material.tabs.TabLayout;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.ramotion.foldingcell.FoldingCell;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class hotelDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Item> items=Item.getTestingList();
    private RecyclerView hotelReserveRecycler;
    private ListView hotelReserveListView;
    private RecyclerView residentsEvaluationRecycler;
    private TabLayout realTabLayout;
    private TabLayout holderTabLayout;
    private CustomScrollView scrollView;
    private LinearLayout container;
    private LinearLayout mLlTop;
    private ImageView hotelLikeImg;
    private String[] tabTxt = {"酒店预订", "订房必读", "用户评价"};
    private List<LinearLayout> anchorList = new ArrayList<>();
    //是否喜欢酒店
    private Boolean hotelLike=false;
    //判读是否是scrollview主动引起的滑动，true-是，false-否，由tablayout引起的
    private boolean isScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos = 0;
    //监听判断最后一个模块的高度，不满一屏时让最后一个模块撑满屏幕
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private Banner hotelBanner;
    private HotelReserveAdapter2 hotelReserveAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail_new);
        QMUIStatusBarHelper.translucent(this);
        init();
        initBanner();
    }
    private void init() {
        hotelLikeImg=findViewById(R.id.hotel_like_image);
        holderTabLayout =findViewById(R.id.tablayout_holder);
        realTabLayout = findViewById(R.id.tablayout_real);
        scrollView =  findViewById(R.id.scrollView_new);
        container = findViewById(R.id.container);
        mLlTop =  findViewById(R.id.ll_top);
        hotelBanner = findViewById(R.id.hotel_banner);
        LinearLayout linearLayout1=findViewById(R.id.container1);
        LinearLayout linearLayout2=findViewById(R.id.container3);
        LinearLayout linearLayout3=findViewById(R.id.container2);
        residentsEvaluationRecycler=findViewById(R.id.resident_evaluation_layout).findViewById(R.id.residents_evaluation_recycler);
        hotelReserveRecycler=findViewById(R.id.hotel_reserve_recycler);
//        hotelReserveListView = findViewById(R.id.mainListView);
        initArchor(linearLayout1, linearLayout2,linearLayout3);
        initRecycler();
        hotelLikeImg.setOnClickListener(this);
        for (int i = 0; i < tabTxt.length; i++) {
            holderTabLayout.addTab(holderTabLayout.newTab().setText(tabTxt[i]));
            realTabLayout.addTab(realTabLayout.newTab().setText(tabTxt[i]));
        }


        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //计算让最后一个view高度撑满屏幕
                int        screenH    = getScreenHeight();
                int        statusBarH = getStatusBarHeight(hotelDetailActivity.this);
                int        tabH       = holderTabLayout.getHeight();
                int        lastH      = screenH - statusBarH - tabH - 16 * 3;
                LinearLayout linearLayout = anchorList.get(anchorList.size() - 1);
                if (linearLayout.getHeight() < lastH) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.height = lastH;
                    linearLayout.setLayoutParams(params);

                }

                //一开始让实际的tablayout 移动到 占位的tablayout处，覆盖占位的tablayout
                realTabLayout.setTranslationY(holderTabLayout.getTop());
                realTabLayout.setVisibility(View.VISIBLE);
                container.getViewTreeObserver().removeOnGlobalLayoutListener(listener);

            }
        };
        container.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        //监听scrollview滑动
        scrollView.setCallback(new CustomScrollView.Callbacks() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                //根据滑动的距离y(不断变化的) 和 holderTabLayout距离父布局顶部的距离(这个距离是固定的)对比，
                //当y < holderTabLayout.getTop()时，holderTabLayout 仍在屏幕内，realTabLayout不断移动holderTabLayout.getTop()距离，覆盖holderTabLayout
                //当y > holderTabLayout.getTop()时，holderTabLayout 移出，realTabLayout不断移动y，相对的停留在顶部，看上去是静止的
                int translation = Math.max(y, holderTabLayout.getTop());
                realTabLayout.setTranslationY(translation);

                if (isScroll) {
                    for (int i = tabTxt.length - 1; i >= 0; i--) {
                        //需要y减去顶部内容区域的高度
                        if (y - mLlTop.getMeasuredHeight() > anchorList.get(i).getTop() - 10) {
                            setScrollPos(i);
                            break;
                        }
                    }
                }

            }

            @Override
            public void onInterceptTouchEvent(MotionEvent ev) {
                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                    isScroll = true;
                }
            }

        });

        //实际的tablayout的点击切换
        realTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                isScroll = false;
                int pos = tab.getPosition();
                Log.d("=======pos", "" + pos);
                int top = anchorList.get(pos).getTop();
                //同样这里滑动要加上顶部内容区域的高度(这里写死的高度)
                scrollView.smoothScrollTo(0, top + mLlTop.getMeasuredHeight());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                isScroll = false;
                int pos = tab.getPosition();
                int top = anchorList.get(pos).getTop();
                //同样这里滑动要加上顶部内容区域的高度
                scrollView.smoothScrollTo(0, top +mLlTop.getMeasuredHeight());
            }
        });
    }

    private void initRecycler() {

        ResidentEvaluationAdapter residentEvaluationAdapter=new ResidentEvaluationAdapter();
        residentsEvaluationRecycler.setAdapter(residentEvaluationAdapter);
        residentsEvaluationRecycler.setLayoutManager(new LinearLayoutManager(this));
        HotelReserveAdapter hotelReserveAdapter = new HotelReserveAdapter();
       // final HotelReserveAdapter2 hotelReserveAdapter2=new HotelReserveAdapter2();
        hotelReserveRecycler.setAdapter(hotelReserveAdapter);
        hotelReserveRecycler.setLayoutManager(new LinearLayoutManager(this));
//        hotelReserveAdapter2 = new HotelReserveAdapter2(this,items);
//        hotelReserveListView.setAdapter(hotelReserveAdapter2);
//        hotelReserveListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                // toggle clicked cell state
//
//                ((FoldingCell) view).toggle(false);
//                Log.d("xxxx", "onItemClick: "+pos);
//                // register in adapter that state for selected cell is toggled
//                hotelReserveAdapter2.registerToggle(pos);
//            }
//        });
    }

    private void initArchor(LinearLayout linearLayout1, LinearLayout linearLayout2,LinearLayout linearLayout3) {
        anchorList.add(linearLayout1);
        anchorList.add(linearLayout3);
        anchorList.add(linearLayout2);
    }


    private void setScrollPos(int newPos) {
        if (lastPos != newPos) {
            realTabLayout.setScrollPosition(newPos, 0, true);
        }
        lastPos = newPos;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    public void initBanner()
    {
        List images = new ArrayList();
        images.add(R.drawable.image5);
        images.add(R.drawable.image4);
        images.add(R.drawable.image3);
        images.add(R.drawable.image2);
        images.add(R.drawable.image1);

        hotelBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(hotelDetailActivity.this).load(path).into(imageView);
            }
        });
        hotelBanner.setImages(images);
        hotelBanner.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.hotel_like_image:
                if(!hotelLike)
                {
                    hotelLikeImg.setImageResource(R.drawable.like);
                    hotelLike=true;
                    Toast.makeText(this,"I don't like it",Toast.LENGTH_LONG).show();
                }
                else
                {
                    hotelLikeImg.setImageResource(R.drawable.dislike);
                    hotelLike=false;
                    Toast.makeText(this,"I like it",Toast.LENGTH_LONG).show();
                }
        }
    }
}
