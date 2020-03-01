package com.example.a80797.bookhotel_recognize.hotel;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.hotel.adapter.HotelReserveAdapter2;
import com.example.a80797.bookhotel_recognize.hotel.model.Item;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelFragment extends Fragment {

ArrayList<Item> items=Item.getTestingList();
    private Boolean IsFold;
    private ListView theListView;
    private HotelReserveAdapter2 hotelReserveAdapter2;

    public HotelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        theListView = requireActivity().findViewById(R.id.hotel_pachage_facility_list);
        hotelReserveAdapter2 = new HotelReserveAdapter2(requireActivity(),items);
        theListView.setAdapter(hotelReserveAdapter2);
        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireActivity(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });
        hotelReserveAdapter2.setOnItemClickListener(new HotelReserveAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                hotelReserveAdapter2.registerToggle(position);
            }
        });


        // set on click event listener to list view

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
                hotelReserveAdapter2.registerToggle(pos);
            }
        });
    }
}
