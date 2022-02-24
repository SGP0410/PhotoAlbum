package com.sun.photoalbum.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.myapplibrary.adapter.BaseRecyclerViewAdapter;
import com.sun.myapplibrary.utils.Utils;
import com.sun.photoalbum.R;
import com.sun.photoalbum.fragment.PicFragment;

import java.io.File;
import java.util.List;
import java.util.Map;

public class PicRecyclerViewAdapter extends BaseRecyclerViewAdapter<PicRecyclerViewAdapter.ViewHolder, String> {

    private List<String> picDate;
    private Map<String, List<File>> picMap;
    private PicFragment picFragment;

    public PicRecyclerViewAdapter(List<String> picDate, Map<String, List<File>> picMap, PicFragment picFragment) {
        super(picDate, R.layout.pic_recycler_view);
        this.picDate = picDate;
        this.picMap = picMap;
        this.picFragment = picFragment;
    }

    @Override
    protected ViewHolder getViewHolder(View itemView) {
        return new ViewHolder(itemView , picFragment);
    }

    @Override
    protected void setValues(ViewHolder holder, String bean) {
        holder.picItem.setBackgroundResource(R.drawable.yuan_jiao_bai);
        holder.picTitle.setText(bean);
        holder.recyclerView.setAdapter(new PicItemRecyclerViewAdapter(picMap.get(bean)));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout picItem;
        private TextView picTitle;
        private RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView , PicFragment picFragment) {
            super(itemView);
            picItem = (LinearLayout) itemView.findViewById(R.id.pic_item);
            picTitle = (TextView) itemView.findViewById(R.id.pic_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new GridLayoutManager(picFragment.getContext() , 3 , RecyclerView.VERTICAL , false));
        }
    }
}
