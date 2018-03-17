package com.example.mainpage.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainpage.R;
import com.example.mainpage.activities.NoteAvtivity;
import com.example.mainpage.beans.Content;

import java.util.List;



/**
 * Created by Administrator on 2017/11/24 0024.
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder>{

    private Context context;

    private List<Content> mTextList;

   static class ViewHolder extends RecyclerView.ViewHolder{
       View textView;
        TextView text;
        ImageView imageView;

        public ViewHolder(View view){
            super(view);
            textView = view;
            text=(TextView)view.findViewById(R.id.text);
            imageView=(ImageView) view.findViewById(R.id.image);
        }

        }
    public TextAdapter(List<Content> TextList){
        mTextList=TextList;

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.text_item,parent,false);

        final ViewHolder holder =new ViewHolder(view);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Content content = mTextList.get(position);


               // Toast.makeText(view.getContext(),"点击"+content.getText(),Toast.LENGTH_SHORT).show();

                switch (content.getImageId()){
                    case R.drawable.jishi:
                        Intent intent = new Intent(context, NoteAvtivity.class);
                        context.startActivity(intent);
                        break;
                    case R.drawable.kecheng:
                        Toast.makeText(view.getContext(),"点击"+content.getText(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.renwu:
                        Toast.makeText(view.getContext(),"点击"+content.getText(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.tongji:
                        Toast.makeText(view.getContext(),"点击"+content.getText(),Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        return  holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Content content = mTextList.get(position);
        holder.text.setText(content.getText());
        holder.imageView.setImageResource(content.getImageId());
    }

    @Override
    public int getItemCount() {
        return mTextList.size();
    }
}
