package com.example.mainpage.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainpage.beans.Note;
import com.example.mainpage.R;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 刘青林 on 2018/3/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Viewholder> {
    private Context mContext;

    private List<Note> mNoteList;

    static class Viewholder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView content;
        TextView time;

        //内部类找到布局中的控件
        public Viewholder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            content = (TextView) itemView.findViewById(R.id.content_card_text);
            time = (TextView) itemView.findViewById(R.id.content_card_time);
        }
    }

    public NoteAdapter(List<Note> noteList) {
        mNoteList = noteList;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_view,parent,false);
        final Viewholder holder = new Viewholder(view);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder delete = new AlertDialog.Builder(mContext);
                delete.setTitle("删除活动");
                delete.setMessage("确定删除此活动？");
                delete.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteData(holder.getAdapterPosition());
                    }
                });
                delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                delete.show();
                return true;
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"item短点击",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Note note = mNoteList.get(position);
        holder.content.setText(note.getContent());
        holder.time.setText(note.getTimeStamp());
    }


    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void deleteData(int position) {
        DataSupport.deleteAll(Note.class,"content = ?",mNoteList.get(position).getContent());
        mNoteList.remove(position);
        notifyItemRemoved(position);
    }
}
