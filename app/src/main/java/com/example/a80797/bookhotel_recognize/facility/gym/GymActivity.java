package com.example.a80797.bookhotel_recognize.facility.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.facility.gym.adpter.FacilityAdapter;
import com.example.a80797.bookhotel_recognize.facility.gym.model.facility;

import java.util.ArrayList;
import java.util.List;

public class GymActivity extends AppCompatActivity {

    List<facility> mList=new ArrayList<>();
    RecyclerView hotelGymFacilityRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);
        hotelGymFacilityRecycler=findViewById(R.id.hotel_gym_facility_recycler);
        initData();
        FacilityAdapter facilityAdapter=new FacilityAdapter(mList);
        hotelGymFacilityRecycler.setAdapter(facilityAdapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        hotelGymFacilityRecycler.setLayoutManager(gridLayoutManager);

    }
    public void initData()
    {
        mList.add(new facility("跑步机",50,17));
        mList.add(new facility("自行车",50,50));
        mList.add(new facility("卧推",50,7));
        mList.add(new facility("杠铃",50,7));
        mList.add(new facility("沙包",50,7));
        mList.add(new facility("跳绳",50,7));
        mList.add(new facility("深蹲",50,7));
        mList.add(new facility("引体向上",50,7));
    }
}
