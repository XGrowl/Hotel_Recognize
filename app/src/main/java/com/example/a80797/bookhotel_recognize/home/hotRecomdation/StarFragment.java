package com.example.a80797.bookhotel_recognize.home.hotRecomdation;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a80797.bookhotel_recognize.R;
import com.example.a80797.bookhotel_recognize.home.hotRecomdation.adapter.HotStarAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StarFragment extends Fragment {


    private RecyclerView hotStarRecycler;
    private HotStarAdapter hotStartAdapter;


    public StarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_star, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hotStarRecycler = requireActivity().findViewById(R.id.star_hot_recondate_recycler);
        hotStartAdapter=new HotStarAdapter();
        hotStarRecycler.setAdapter(hotStartAdapter);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        hotStarRecycler.setLayoutManager(layoutManager);
    }
}
