package com.sun.photoalbum.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sun.myapplibrary.fragment.BaseFragment;
import com.sun.photoalbum.R;
import com.sun.photoalbum.adapter.PicRecyclerViewAdapter;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PicFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private Map<String, List<File>> picMap;
    private List<String> picDate;

    @Override
    protected int layoutResId() {
        return R.layout.fragment_pic;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void initData() {
        picMap = new HashMap<>();
        getLocalPhotos(Objects.requireNonNull(getContext()));
        picListSort();
        showRecyclerView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void picListSort() {
        picDate = new ArrayList<>(picMap.keySet());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        picDate.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                LocalDate dateTime1 = LocalDate.parse(o1.split(" ")[0].replaceAll("[\\u4e00-\\u9fa5]" , "-").substring(0 , 10), dateTimeFormatter);
                LocalDate dateTime2 = LocalDate.parse(o2.split(" ")[0].replaceAll("[\\u4e00-\\u9fa5]" , "-").substring(0 , 10), dateTimeFormatter);
                if (dateTime1.isAfter(dateTime2)){
                    return -1;
                }else if (dateTime1.isBefore(dateTime2)){
                    return 1;
                }else {
                    return 0;
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getLocalPhotos(Context context) {

        String[] projection  = {MediaStore.Images.Media.DATA};
        String where = MediaStore.Images.Media.MIME_TYPE + "=? or " +
                MediaStore.Images.Media.MIME_TYPE + "=? or " +
                MediaStore.Images.Media.MIME_TYPE + "=? or " +
                MediaStore.Images.Media.MIME_TYPE + "=?";
        String[] whereArgs = {"image/jpeg", "image/png", "image/jpg", "image/gif"};
        @SuppressLint("Recycle") Cursor cursor = context.getContentResolver()
                .query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        projection, where, whereArgs,
                        MediaStore.Images.Media.DATE_MODIFIED + " desc "
                );

        Log.d("TAG", "getLocalPhotos: "+cursor.getCount());

        //没有图片时返回0
        while (cursor.moveToNext()){
            //获取图片资源路径
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String imagePath = new String(data, 0, data.length - 1);
            //将图片资源路径转化为File对象
            File file = new File(imagePath);
            //获取图片日期 yyyy年MM月dd日 EEEE
            Instant instant = Instant.ofEpochMilli(file.lastModified());
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
            String date = dateTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 EEEE"));

            if (picMap.get(date) != null){
                Objects.requireNonNull(picMap.get(date)).add(file);
            }else {
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                picMap.put(date , fileList);
            }
        }
    }

    private void showRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false));
        recyclerView.setAdapter(new PicRecyclerViewAdapter(picDate , picMap , PicFragment.this));
    }


    @Override
    protected void initColor(boolean isNight) {

    }

    @Override
    public void onClick(View view) {

    }
}
