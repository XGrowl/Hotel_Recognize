package com.example.a80797.bookhotel_recognize.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.a80797.bookhotel_recognize.R;

public class CircleRelativeLayout extends RelativeLayout {
    private int color;
    private int[] colors;
    private int alpha;
    public CircleRelativeLayout(Context context) {
        super(context);
    }

    public CircleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
        setWillNotDraw(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getMeasuredWidth();
        Paint paint=new Paint();
        paint.setARGB(alpha,colors[0],colors[1],colors[2]);
        paint.setAntiAlias(true);
        float cirX=width/2;
        float cirY=width/2;
        float radius=width/2;
        canvas.drawCircle(cirX,cirY,radius,paint);

    }
    public void setColor(int color) { //设置背景色
        this.color = color;
        setColors();
        invalidate();
    }

    public void setAlhpa(int alhpa) { //设置透明度
        this.alpha = alhpa;
        invalidate();
    }

    private void init(Context context, AttributeSet attrs)
    {
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CircleRelativeLayoutLayout);
        color=array.getColor(R.styleable.CircleRelativeLayoutLayout_background_color,0X0000000);
        alpha=array.getInteger(R.styleable.CircleRelativeLayoutLayout_background_alpha,100);
        setColors();
        array.recycle();
    }
    public void setColors() {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        this.colors = new int[]{red,green,blue};
    }

}
