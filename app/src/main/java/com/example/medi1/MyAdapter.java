package com.example.medi1;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    String name;
    Bitmap image;
    String shape;
    String color;

    private ArrayList<Drug> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    MyAdapter(Context context, ArrayList<Drug> mList) {//생성자를 context와 배열로 초기화해줌
        this.mList = mList;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.list_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        //최초 view에 대한 list item에 대한 view를 생성함.
        //이 onBindViewHolder친구한테 실질적으로 매칭해주는 역할을 함.
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Glide.with(holder.itemView)
                  .load(mList.get(position).getImage())
                .into(holder.image);

        holder.name.setText(mList.get(position).getName());
        holder.shape.setText(mList.get(position).getShape());
        holder.color.setText(mList.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name;
        public TextView color;
        public TextView shape;
        public View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            name = itemView.findViewById(R.id.name);
            color = itemView.findViewById(R.id.color);
            image = itemView.findViewById(R.id.image);
            shape = itemView.findViewById(R.id.shape);
        }
    }
}
