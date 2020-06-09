package com.example.medi1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="Ma";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    Gson gson = new Gson();
    ///////////////////////////색상 버튼////////////////////////////
    public String color = null; //색상 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_recyclerview);

        try{
            InputStream is = getAssets().open("druglist3.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);

            Map<String,Object> boxOfficeResult= gson.fromJson( jsonObject.get("boxOfficeResult").toString(),new TypeToken<Map<String, Object>>(){}.getType());

            ArrayList<Map<String, Object>> jsonList = (ArrayList) boxOfficeResult.get("Drug");

            mAdapter=new DrugAdapter(jsonList);

        }catch (Exception e){e.printStackTrace();}

    }//onCreate()..


    public void click_btn(View view) {
        recyclerView.setAdapter(mAdapter);
    }

    //하양색 버튼을 눌러 색상이 하양인것을 출력하기
    public void whiteclick(View view) {
        color = "하양";
        
    }
}