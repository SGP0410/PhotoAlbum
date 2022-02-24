package com.sun.photoalbum.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.myapplibrary.fragment.BaseFragment;
import com.sun.myapplibrary.utils.myView.ImageViewOval;
import com.sun.photoalbum.R;
import com.sun.photoalbum.adapter.ClassifyRecyclerViewAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassifyFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ImageViewOval imageView;
    private Map<String , List<String>> groupPic;

    @Override
    protected int layoutResId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        imageView = (ImageViewOval) view.findViewById(R.id.image_view);
    }

    @Override
    protected void initData() {
        groupPic = new HashMap<>();
        groupPic.put("1" , null);
        groupPic.put("2" , null);
        groupPic.put("3" , null);
        groupPic.put("4" , null);
        showRecyclerView();
    }

    private void showRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext() , getSpanCount(200) , RecyclerView.VERTICAL , false));
        recyclerView.setAdapter(new ClassifyRecyclerViewAdapter(groupPic));
    }

    @Override
    protected void initColor(boolean isNight) {

    }

    @Override
    public void onClick(View view) {

    }
}
