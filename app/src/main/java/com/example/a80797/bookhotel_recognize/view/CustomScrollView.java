package com.example.a80797.bookhotel_recognize.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class CustomScrollView extends NestedScrollView {
    public Callbacks  mCallback;
    public CustomScrollView(@NonNull Context context) {
        super(context);
    }

    public CustomScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCallback(Callbacks callback) {
        mCallback = callback;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mCallback!=null)
        {
            mCallback.onScrollChanged(l,t,oldl,oldt);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mCallback!=null)
            mCallback.onInterceptTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    //定义接口用于回调
    public interface Callbacks{
        void onScrollChanged(int l, int t, int oldl, int oldt);
        void onInterceptTouchEvent(MotionEvent ev);
    }

}
