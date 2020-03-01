package com.example.a80797.bookhotel_recognize.seach;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReadAdapter;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReserveAdapter;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReserveAdapter2;
import com.example.a80797.bookhotel_recognize.view.AnChorView;
import com.example.a80797.bookhotel_recognize.view.CustomScrollView;
import com.google.android.material.tabs.TabLayout;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

import static androidx.transition.TransitionManager.beginDelayedTransition;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private ListView theListView;
    private HotelReadAdapter hotelReadAdapter;


    public SearchFragment() {
        // Required empty public constructor
    }
    List list=new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list.add(1);
        list.add(2);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        theListView = requireActivity().findViewById(R.id.temp_list);
        hotelReadAdapter = new HotelReadAdapter(requireActivity(),list);
        theListView.setAdapter(hotelReadAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state

                ((FoldingCell) view).toggle(false);
                Log.d("xxxx", "onItemClick: "+pos);
                // register in adapter that state for selected cell is toggled
                hotelReadAdapter.registerToggle(pos);
            }
        });
    }
}
