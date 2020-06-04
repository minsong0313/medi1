package com.example.medi1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recylerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Data> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recylerView = findViewById(R.id.recyclerView); //id연결
        recylerView.setHasFixedSize(true);//리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recylerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //user 객체 담을 array list(어뎁터 쪽으로 날리려고함)

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Druglist"); //DB테이블을 연결하는 작업
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear();//arraylist:유저 객체를 담을것. add한적도 없는데 clear로 초기화?
                //기존 배열리스트가 존재하지 않게 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){//반복문으로 데이터 List를 추출해냄
                    Data data = snapshot.getValue(Data.class);//만들어뒀던 User 객체에 데이터를 담는다
                    //user클래스 안에다가 firebase db로부터 가져온 애를 userclass로 박아주고 걔를 arraylist에 담아다가 adapt로 쏘는
                    arrayList.add(data);//실질적으로 넣고 arraylist에 추가해주면 완성. 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비를 가져오던중 에러 발생시
                Log.e("MainActivity", String.valueOf(databaseError.toException()));//에러문 출력
            }//입력을 안해도됨. 구현하는데 큰 문제 없음
        });
        adapter = new DrugAdapter(arrayList, this);//adapter를 생성하고 그것을 arraylist에서 context를 넘겨줄게.
        recylerView.setAdapter(adapter);//리사이클러뷰에 어뎁터연결
    }

}

