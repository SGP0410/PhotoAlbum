package com.sun.photoalbum.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.myapplibrary.adapter.BaseRecyclerViewAdapter;
import com.sun.photoalbum.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassifyRecyclerViewAdapter extends BaseRecyclerViewAdapter<ClassifyRecyclerViewAdapter.ViewHolder , String> {
    private Map<String, List<String>> groupPic;
    public ClassifyRecyclerViewAdapter(Map<String, List<String>> groupPic) {
        super(new ArrayList<>(groupPic.keySet()) , R.layout.group_item);
        this.groupPic = groupPic;
    }

    @Override
    protected ViewHolder getViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void setValues(ViewHolder holder, String bean) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
