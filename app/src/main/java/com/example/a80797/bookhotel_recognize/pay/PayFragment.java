package com.example.a80797.bookhotel_recognize.pay;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.pay.view.PassWordInputView;
import com.example.a80797.bookhotel_recognize.pay.view.PayPassWordView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class PayFragment extends DialogFragment implements View.OnClickListener {
    public static final String EXTRA_CONTENT = "extra_content"; //提示框内容
    public static final String EXTRA_CONTENT2 = "extra_content2"; //提示框内容
    public static final String EXTRA_CONTENT3 = "extra_content3"; //提示框内容

    private PayPassWordView psw_input;
    private PayPassWordView.InputCallBack inputCallBack;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_pay);
        dialog.setCanceledOnTouchOutside(true); //外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.TOP;
        window.setAttributes(lp);
        initView(dialog);
        return dialog;
    }

    private void initView(Dialog dialog) {
        Bundle bundle = getArguments();
        if (bundle != null) {
          //  TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
            TextView tv_content2 = (TextView) dialog.findViewById(R.id.tv_content2);
           // TextView tv_content3 = (TextView) dialog.findViewById(R.id.tv_content3);
           // tv_content.setText(bundle.getString(EXTRA_CONTENT));
            tv_content2.setText(bundle.getString(EXTRA_CONTENT2));
          //  tv_content3.setText(bundle.getString(EXTRA_CONTENT3));
        }
        psw_input = (PayPassWordView) dialog.findViewById(R.id.payPwdView);
        PassWordInputView inputMethodView = (PassWordInputView) dialog.findViewById(R.id.inputMethodView);
        psw_input.setInputMethodView(inputMethodView);
        psw_input.setInputCallBack(inputCallBack);
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    /**
     * 设置输入回调
     *
     * @param inputCallBack
     */
    public void setPaySuccessCallBack(PayPassWordView.InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
    }
}
