package com.example.a80797.bookhotel_recognize.pay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReadAdapter;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReserveAdapter;
import com.example.a80797.bookhotel_recognize.pay.adapter.orderDetailAdapter;
import com.example.a80797.bookhotel_recognize.pay.model.orderPayment;
import com.qmuiteam.qmui.layout.QMUIFrameLayout;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.popup.QMUIFullScreenPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



public class hotelDetailBottomActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int mShadowElevationDp=20;
    TextView orderDetailText;
    public static final int M_SHADOW_ALPHA = (int) (20 * 1f / 100);
    QMUILinearLayout lin1,lin2,lin3;
    QMUIRoundButton confirmPaymentOrderBtn;
    RecyclerView orderDetailRecycler;
    private QMUIPopup mNormalPopup;
    private RelativeLayout orderDetailMessage;
    List<orderPayment> list=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail_bottom);
        lin1=findViewById(R.id.lin1);
        lin2=findViewById(R.id.lin2);
        orderDetailText=findViewById(R.id.order_detail_text);
       // orderDetailRecycler=findViewById(R.id.order_detail_message_recycler);
        orderDetailMessage = findViewById(R.id.order_detail_message_layout);
        int mRadius= QMUIDisplayHelper.dp2px(this, 2);
        lin1.setRadiusAndShadow(mRadius,
                QMUIDisplayHelper.dp2px(this, mShadowElevationDp),
                M_SHADOW_ALPHA);
        lin2.setRadiusAndShadow(mRadius,
                QMUIDisplayHelper.dp2px(this, mShadowElevationDp),
                M_SHADOW_ALPHA);
       confirmPaymentOrderBtn=findViewById(R.id.confirm_payment_order_btn);
        QMUIRoundButtonDrawable qmuiRoundButtonDrawable= (QMUIRoundButtonDrawable) confirmPaymentOrderBtn.getBackground();
        ColorStateList colorStateList=ColorStateList.valueOf(getResources().getColor(R.color.light_green_btn_color));
        qmuiRoundButtonDrawable.setBgData(colorStateList);
        confirmPaymentOrderBtn.setOnClickListener(this);
        initData();
        orderDetailText.setOnClickListener(this);
        recyclerView = new RecyclerView(this);
        orderDetailAdapter orderDetailAdapter=new orderDetailAdapter(list);
        recyclerView.setAdapter(orderDetailAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.order_detail_text:

                mNormalPopup = QMUIPopups.popup(this, QMUIDisplayHelper.dp2px(this, 250))
                        .preferredDirection(QMUIPopup.DIRECTION_BOTTOM)
                        .view(recyclerView)
                        .edgeProtection(QMUIDisplayHelper.dp2px(this, 20))
                        .dimAmount(0.6f)
                        .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                        .onDismiss(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                               // Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(v);
                break;
            case R.id.confirm_payment_order_btn:
                startActivity(new Intent(hotelDetailBottomActivity.this,PayActivity.class));
        }

    }
    public void initData()
    {
        list.add(new orderPayment("03-03","无餐盒","1×￥328"));
        list.add(new orderPayment("03-04","2份早餐",""));
    }
}
