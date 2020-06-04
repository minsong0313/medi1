package com.example.medi1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> {
    private ArrayList<Data> arrayList;
    private Context context;

    public DrugAdapter(ArrayList<Data> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //실제 listview가 adapter에 연결된 다음에 이쪽에서 view holer를 최초로 만들어냄
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        DrugViewHolder holder = new DrugViewHolder(view);
        return holder;
        //최초 view에 대한 list item에 대한 view를 생성함.
        //이 onBindViewHolder친구한테 실질적으로 매칭해주는 역할을 함.
    }

    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {
        //각 item에 대한 매칭을함.
        //이 세줄만 적어두면 이미지 view 안에 서버로부터 이미지를 받아와
        //bind view holder될 때 넣어줄것임. 삽입될것임
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.image);
        //현재 position에 있는것을 가져와서 id를 그대로 입력해줘.
        holder.name.setText(arrayList.get(position).getName());
        holder.color.setText(String.valueOf(arrayList.get(position).getColor()));
        holder.shape.setText(arrayList.get(position).getShape());

        //arrayList가 User에 연결해놓았음. Mainactivity에서 firebase 데이터를 받아옴
        //User객체가 있는 arrayList에다 담아서 adapter쪽으로 쏨
        // 그러면 onBindViewHolder여기서 그 친구를 받아 glide로 load하는 그런 로직이됨

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class DrugViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView color;
        TextView shape;

        public DrugViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.name = itemView.findViewById(R.id.tv_name);
            this.color = itemView.findViewById(R.id.tv_color);
            this.shape = itemView.findViewById(R.id.tv_shape);
        }

    }
}
