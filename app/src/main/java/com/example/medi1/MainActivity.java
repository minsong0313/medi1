package com.example.medi1;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private static final String TAG = "Ma";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    Gson gson = new Gson();

    ///////////////////////////색상 버튼////////////////////////////
    public String choosecolor = null;
    //public ArrayList arrayList = new ArrayList();
    String color;
    //public ArrayList<Map<String, Object>> items= new ArrayList<Map<String, Object>>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_recyclerview);

        AssetManager assetManager = getResources().getAssets();

        try{
            InputStream is = getAssets().open("druglist3.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);

         //   JSONObject jsonObject1 = new JSONObject(jsonValue);



            Map<String,Object> Drug= gson.fromJson( jsonObject.get("Drug").toString(),new TypeToken<Map<String, Object>>(){}.getType());


            ArrayList<Map<String, Object>> jsonList = (ArrayList) Drug.get("druglist");

            mAdapter = new DrugAdapter(jsonList);
            
            Log.e("",color);
            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
            Button buttonWhite = (Button)findViewById(R.id.buttonWhite);
            buttonWhite.setOnClickListener(this);
            Button buttonGreen = (Button)findViewById(R.id.buttonGreen);
            buttonGreen.setOnClickListener(this);
            Button buttonYellow = (Button)findViewById(R.id.buttonYellow);
            buttonYellow.setOnClickListener(this);

        }catch (Exception e){e.printStackTrace();}



        Button buttonWhite = (Button) findViewById(R.id.buttonWhite);
        buttonWhite.setOnClickListener(this);
        Button buttonGreen = (Button) findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(this);
        Button buttonYellow = (Button) findViewById(R.id.buttonYellow);
        buttonYellow.setOnClickListener(this);
    }

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//


    @Override
    public void onClick(View v) {
        TextView textView = (TextView) findViewById(R.id.result);
        switch (v.getId()){
            case R.id.buttonWhite:
                choosecolor="하양";
                textView.setText(choosecolor);
                break;
            case R.id.buttonGreen:
                choosecolor="초록";
                textView.setText(choosecolor);
                break;
            case R.id.buttonYellow:
                choosecolor="노랑";
                textView.setText(choosecolor);
                break;
            ////나머지 색 표시하기 성공하면.....
        }
    }


    public void click_btn(View view) {
        recyclerView.setAdapter(mAdapter);
    }



}