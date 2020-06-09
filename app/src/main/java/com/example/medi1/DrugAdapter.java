package com.example.medi1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.MyViewHolder> {
    private ArrayList<Map<String, Object>> items= new ArrayList<Map<String, Object>>();

    public DrugAdapter(ArrayList<Map<String, Object>> resultList){
       this.items=resultList;
    }

    @NonNull
    @Override
    public DrugAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item , parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Map<String, Object> item = (Map<String, Object>) items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, image, shape, color;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            image=itemView.findViewById(R.id.image);
            shape=itemView.findViewById(R.id.shape);
            color=itemView.findViewById(R.id.color);

        }
        public void setItem(Map<String, Object> item){

            //"rank", "movieNm", "openDt"은 Json파일에 저장되어 있던 key값
            name.setText(item.get("name").toString());
            image.setText(item.get("image").toString());
            shape.setText(item.get("shape").toString());
            color.setText(item.get("color").toString());

        }
    }
}