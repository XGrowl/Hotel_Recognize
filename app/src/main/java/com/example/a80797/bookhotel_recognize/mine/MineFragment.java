package com.example.a80797.bookhotel_recognize.mine;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a80797.bookhotel_recognize.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {


    private View mView;
    Context mContext;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.test, container, false);

        return mView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


//        DisplayMetrics dm=getResources().getDisplayMetrics();
//        int height=dm.heightPixels;
//        LinearLayout linearLayout=requireActivity().findViewById(R.id.mine_top_linear);
//        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
//        layoutParams.height=height*3/4;
//        linearLayout.setLayoutParams(layoutParams);


}
