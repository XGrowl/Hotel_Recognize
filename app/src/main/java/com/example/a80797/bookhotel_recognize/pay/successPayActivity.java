package com.example.a80797.bookhotel_recognize.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a80797.bookhotel_recognize.R;
import com.sunshine.viewlibrary.WaveView;

public class successPayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_pay);
        WaveView waveView= findViewById(R.id.waveView);
        waveView.setFlowNum(70);
        waveView.setUpSpeed(10);
        waveView.setWaveSpeed(10);
        waveView.setScore(600);
        waveView.start();
    }
}
