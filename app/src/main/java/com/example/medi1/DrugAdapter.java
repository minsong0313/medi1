package com.example.medi1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
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
                name = itemView.findViewById(R.id.name);
                image = itemView.findViewById(R.id.image);
                shape = itemView.findViewById(R.id.shape);
                color = itemView.findViewById(R.id.color);
        }

        public void setItem(Map<String, Object> item) {

            //Json파일에 저장되어 있던 key값
            String nameString = (String) item.get("품목명");
            String imagetring = (String) item.get("큰제품이미지");
            String shapeString = (String) item.get("의약품제형");
            String colorString = (String) item.get("색상앞");

                name.setText(nameString);
                image.setText(imagetring);
                shape.setText(shapeString);
                color.setText(colorString);
        }

    }

}