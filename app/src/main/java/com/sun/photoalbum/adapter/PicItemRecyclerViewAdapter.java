package com.sun.photoalbum.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.myapplibrary.adapter.BaseRecyclerViewAdapter;
import com.sun.myapplibrary.utils.myView.ImageViewRoundRect;
import com.sun.photoalbum.R;

import java.io.File;
import java.util.List;

public class PicItemRecyclerViewAdapter extends BaseRecyclerViewAdapter<PicItemRecyclerViewAdapter.ViewHolder, File> {

    public PicItemRecyclerViewAdapter(List<File> fileList) {
        super(fileList, R.layout.pic_item_layout);
    }

    @Override
    protected ViewHolder getViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void setValues(ViewHolder holder, File bean) {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 16;
//        Bitmap bitmap = BitmapFactory.decodeFile(bean.getPath() , options);
//        Bitmap bitmap = ThumbnailUtils.createImageThumbnail(String.valueOf(bean), ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

//        holder.image.setImageBitmap(bitmap);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageViewRoundRect image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageViewRoundRect) itemView.findViewById(R.id.image);
            image.setRx(30);
            image.setRy(30);
        }
    }
}
