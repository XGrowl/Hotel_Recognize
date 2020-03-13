package com.example.a80797.bookhotel_recognize.home;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import quatja.com.vorolay.VoronoiView;
import quatja.com.vorolay.diagram.VoronoiRegion;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a80797.bookhotel_recognize.R;

import com.example.a80797.bookhotel_recognize.home.peripheralZome.adapter.PeripheralZoneAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.tab.QMUITab;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    TextView tvSearch;
    LinearLayout mSearchLayout;
    ScrollView mScrollView;
    boolean isExpand=false;
    ImageView ivImg;
    Toolbar toolbar;
    TextView tvLocation;
    private TransitionSet mSet;
    private RecyclerView peripheralRecycler;
    private PeripheralZoneAdapter peripheralZoneAdapter;
    private VoronoiView voronoiView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        peripheralZoneAdapter=new PeripheralZoneAdapter();
        peripheralRecycler.setAdapter(peripheralZoneAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        peripheralRecycler.setLayoutManager(layoutManager);
        toolbar.getBackground().mutate().setAlpha(0);
        //scrollview滚动状态监听
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                //改变toolbar的透明度
                changeToolbarAlpha();
                //滚动距离>=大图高度-toolbar高度 即toolbar完全盖住大图的时候 且不是伸展状态 进行伸展操作
                if (mScrollView.getScrollY() >=ivImg.getHeight() - toolbar.getHeight()  && !isExpand) {
                    expand();
                    isExpand = true;
                }
                //滚动距离<=0时 即滚动到顶部时  且当前伸展状态 进行收缩操作
                else if (mScrollView.getScrollY()<=0&& isExpand) {
                    reduce();
                    isExpand = false;
                }
            }
        });
        initTheme(voronoiView);

    }

    private void initTheme(VoronoiView voronoiView) {
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i <8; i++) {
            View view = layoutInflater.inflate(R.layout.item_voronoi_1, null, false);
            voronoiView.addView(view);


            int resourceId = getResources().getIdentifier("image"+((i%5)+1), "drawable", requireActivity().getPackageName());

            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setImageResource(resourceId);

            final TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText("image "+i);

            Button button = (Button) view.findViewById(R.id.btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(requireActivity(), "Element " + textView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        voronoiView.setOnRegionClickListener(new VoronoiView.OnRegionClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.v("Region", "Region: " + position);
            }
        });
    }

    private void initView() {

        voronoiView = requireView().findViewById(R.id.voronoi);
        tvSearch=requireActivity().findViewById(R.id.tv_search);
        mSearchLayout=requireActivity().findViewById(R.id.ll_search);
        tvLocation=requireActivity().findViewById(R.id.location_tv);
        mScrollView=requireActivity().findViewById(R.id.scrollView);
        ivImg=requireActivity().findViewById(R.id.iv_img);
        toolbar=requireActivity().findViewById(R.id.toolbar);
        peripheralRecycler=requireActivity().findViewById(R.id.peripheral_recycler);
        BottomNavigationView bottomNavigationView=requireView().findViewById(R.id.hot_recon_nav);
        NavController navController= Navigation.findNavController(getActivity(),R.id.fragment3);
        AppBarConfiguration configuration=new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        //   NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    private void changeToolbarAlpha() {
        int scrollY = mScrollView.getScrollY();
        //快速下拉会引起瞬间scrollY<0
        if(scrollY<0){
            toolbar.getBackground().mutate().setAlpha(0);
            return;
        }

        //计算当前透明度比率
//        float radio= Math.min(1,scrollY/(ivImg.getHeight()-toolbar.getHeight()*1f));
//        //设置透明度
//        toolbar.getBackground().mutate().setAlpha( (int)(radio * 0xFF));
    }


    private void expand() {
        //设置伸展状态时的布局
        tvSearch.setText("搜索简书的内容和朋友");
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        tvLocation.setVisibility(View.VISIBLE);
        LayoutParams.width = LayoutParams.MATCH_PARENT;
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
    }

    private void reduce() {
        //设置收缩状态时的布局
        tvSearch.setText("搜索");
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
        LayoutParams.width = dip2px(80);
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
    }
    void beginDelayedTransition(ViewGroup view) {
        mSet = new AutoTransition();
        mSet.setDuration(300);
        TransitionManager.beginDelayedTransition(view, mSet);
    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }


}
