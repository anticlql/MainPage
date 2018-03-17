package com.example.mainpage.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mainpage.R;
import com.example.mainpage.adapters.NoteAdapter;
import com.example.mainpage.beans.Note;
import com.example.mainpage.utils.TimeUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

public class NoteAvtivity extends AppCompatActivity {

    private List<Note> noteList;
    private RecyclerView noteRecycler;
    private NoteAdapter noteAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_avtivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.note_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final FloatingActionButton add = (FloatingActionButton) findViewById(R.id.note_fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });

        //搜索框设置
        searchView = (SearchView) findViewById(R.id.note_search);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("请输入搜索内容");

        getData();
        noteRecycler = (RecyclerView) findViewById(R.id.note_recycler);
        setDoingRecycler();

    }

    //添加活动
    public void addNote() {
        //新建Dialog添加活动
        final AlertDialog.Builder add = new AlertDialog.Builder(NoteAvtivity.this);
        final View dialogView = LayoutInflater.from(NoteAvtivity.this).inflate(R.layout.note_add_dialog,null);
        add.setTitle("添加活动");
        add.setView(dialogView);
        add.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText content = (EditText) dialogView.findViewById(R.id.content);
                Note note = new Note();
                note.setContent(content.getText().toString());
                note.setTimeStamp(TimeUtil.getCurrentDate());
                note.save();

                //刷新视图
                refreshData();
            }
        });
        add.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        add.show();
    }



    //从数据库中获取数据
    public void getData() {
        List<Note> notes = DataSupport.findAll(Note.class);
        noteList = notes;
    }

    //设置RecycleyView显示布局
    public void setDoingRecycler() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager (2,StaggeredGridLayoutManager.VERTICAL);
        noteRecycler.setLayoutManager(manager);
        noteAdapter = new NoteAdapter(noteList);
        noteRecycler.setAdapter(noteAdapter);
    }

    //添加或者删除时更新
    public void refreshData() {
        noteList.clear();
        noteList.addAll(DataSupport.findAll(Note.class));
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:                 //点击返回
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
