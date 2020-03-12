package com.example.a80797.bookhotel_recognize.pay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.pay.adapter.PaymentAccountAdapter;
import com.example.a80797.bookhotel_recognize.pay.view.PayPassWordView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity implements PayPassWordView.InputCallBack, View.OnClickListener {
    QMUIRoundButton confirmPaymentAccountBtn;
    RecyclerView paymentChosenRecycler;
    List<String> list=new ArrayList();
    private PayFragment fragment;
    private Context mContext;
    public static int PAY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        QMUIStatusBarHelper.translucent(this);
        initData();
        confirmPaymentAccountBtn=findViewById(R.id.confirm_payment_account_btn);
        paymentChosenRecycler=findViewById(R.id.payment_chosen_recycler);
        PaymentAccountAdapter paymentAccountAdapter = new PaymentAccountAdapter(list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        paymentChosenRecycler.setAdapter(paymentAccountAdapter);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        paymentChosenRecycler.setLayoutManager(layoutManager);
        QMUIRoundButtonDrawable qmuiRoundButtonDrawable= (QMUIRoundButtonDrawable) confirmPaymentAccountBtn.getBackground();
        ColorStateList colorStateList=ColorStateList.valueOf(getResources().getColor(R.color.qmui_config_color_50_blue));
        qmuiRoundButtonDrawable.setBgData(colorStateList);
        confirmPaymentAccountBtn.setOnClickListener(this);
    }
    public void initData()
    {
        list.add("支付宝");
        list.add("AppPay");
        list.add("腾讯钱包");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.confirm_payment_account_btn:
                Bundle bundle = new Bundle();
              //  bundle.putString(PayFragment.EXTRA_CONTENT, "提现");
                bundle.putString(PayFragment.EXTRA_CONTENT2, "¥" + 132.00);
              //  bundle.putString(PayFragment.EXTRA_CONTENT3, "额外扣除0.1手续费");
                fragment = new PayFragment();//创建支付弹出框实例
                fragment.setArguments(bundle);//传递信息
                fragment.setPaySuccessCallBack(PayActivity.this);//设置回调
                fragment.show(getSupportFragmentManager(), "Pay");
                break;
        }
    }

    @Override
    public void onInputFinish(String result) {
        final Intent intent;
        //正确的密码为123456
        if(result.equals("123456")) {
            new LoadingDialog(this)
                    .setLoadingText("支付中")
                    .setSuccessText("支付成功")
                    .setInterceptBack(false)
                    .setLoadSpeed(LoadingDialog.Speed.SPEED_ONE)
                    .closeSuccessAnim()
                    .setRepeatCount(1000)
                    .show();
           intent= new Intent(PayActivity.this,successPayActivity.class);
        }
        else
        {
            new LoadingDialog(this)
                    .setLoadingText("支付中")
                    .setFailedText("支付失败")
                    .setInterceptBack(false)
                    .setLoadSpeed(LoadingDialog.Speed.SPEED_ONE)
                    .closeSuccessAnim()
                    .setRepeatCount(1000)
                    .show();
            intent= new Intent(PayActivity.this,failPayActivity.class);
        }
     new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(intent);
           }
       },1000);

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
