package com.example.mainpage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.example.mainpage.adapters.TextAdapter;
import com.example.mainpage.beans.Content;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity {
    private Context context;
 //   public static int COL_NUM = 0;
    private List<Content> textList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        context=this;
        initText();
        //瀑布布局
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        TextAdapter adapter = new TextAdapter(textList);
        recyclerView.setAdapter(adapter);
    }

    private void initText() {
            Content text1 = new Content("课程",R.drawable.kecheng);
            textList.add(text1);
            Content text2 = new Content("任务",R.drawable.renwu);
            textList.add(text2);
            Content text3 = new Content("统计",R.drawable.tongji);
            textList.add(text3);
            Content text4 = new Content("记事",R.drawable.jishi);
            textList.add(text4);
    }

}
