package com.example.a80797.bookhotel_recognize.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;

public class CustomLocationTitleView extends FrameLayout  {
    private View.OnClickListener mOnClickListener;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    public CustomLocationTitleView(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.custom_loaction_view,this);
        mTextView=findViewById(R.id.location_name);
        mLinearLayout=findViewById(R.id.locaiton_view);
        mLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void setLocationCity(String city)
    {
        mTextView.setText(city);
    }



}
