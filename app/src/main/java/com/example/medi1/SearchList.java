package com.example.medi1;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class SearchList extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<Drug> list = null;
    MyAdapter mAdapter;

    String choosecolor;
    String chooseshape;
    String choosetype;
    String searchmarkfront;
    String searchmarkback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_search_list);

        choosecolor = getIntent().getStringExtra("choosecolor");
        chooseshape = getIntent().getStringExtra("chooseshape");
        choosetype = getIntent().getStringExtra("choosetype");
        searchmarkfront = getIntent().getStringExtra("searchmarkfront");
        searchmarkback = getIntent().getStringExtra("searchmarkback");
        Log.e("result : ", choosecolor + "/ " + chooseshape + "/ " + choosetype + "/" + searchmarkfront + "/" + searchmarkback);

        if (choosecolor == null && chooseshape == null && choosetype ==null) {

            marksearchJson();
            Log.e("dg","식별자");
        }
        else {
            searchJson();
            Log.e("dg","컬러");
        }
        recyclerView = (RecyclerView)findViewById(R.id.rv_recyclerview);//리사이클러뷰 초기화
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존 성능 강화

        mAdapter = new MyAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


    //json에서 조건에 맞는 것 검색(색상, 모양, 제형)
    public void searchJson(){
        try{
            InputStream is = getAssets().open("druglist.json"); //assests파일에 저장된 druglist_final.json 파일 열기
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("druglist"); //json파일에서 의약품리스트의 배열명, jsonArray로 저장

            list = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                //'색상, 모양, 제형' 선택하고 검색하기(3개의 카테고리 중 하나만 선택 가능)
                    if (choosecolor.contains(jsonObject.getString("색상앞")) && chooseshape.contains(jsonObject.getString("의약품제형")) && choosetype.contains(jsonObject.getString("제형코드명"))) {
                        Drug drug = new Drug();
                        Log.e("표시앞 : ", jsonObject.getString("품목명") + jsonObject.getString("색상앞") + jsonObject.getString("의약품제형") + jsonObject.getString("제형코드명") + jsonObject.getString("표시앞") + jsonObject.getString("표시뒤"));
                        drug.setColor(jsonObject.getString("색상앞"));
                        drug.setImage(jsonObject.getString("큰제품이미지"));
                        drug.setName(jsonObject.getString("품목명"));
                        drug.setShape(jsonObject.getString("의약품제형"));
                        drug.setType(jsonObject.getString("제형코드명"));

                        list.add(drug);
                    }
                }

            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
        }catch (Exception e){e.printStackTrace();}

    }

    //json에서 조건에 맞는 것 검색(식별자)
    public void marksearchJson(){
        try{
            InputStream is = getAssets().open("druglist.json"); //assests파일에 저장된 druglist_final.json 파일 열기
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("druglist"); //json파일에서 의약품리스트의 배열명, jsonArray로 저장

            list = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                //'색상, 모양, 제형' 선택하고 검색하기(3개의 카테고리 중 하나만 선택 가능)
                if(!searchmarkfront.equals("-")) {
                    if (searchmarkfront.equals(jsonObject.getString("표시앞")))
                    {
                        Drug drug = new Drug();
                        Log.e("표시앞 : ", jsonObject.getString("품목명") + jsonObject.getString("색상앞") + jsonObject.getString("의약품제형") + jsonObject.getString("제형코드명") + jsonObject.getString("표시앞") + jsonObject.getString("표시뒤"));
                        drug.setColor(jsonObject.getString("색상앞"));
                        drug.setImage(jsonObject.getString("큰제품이미지"));
                        drug.setName(jsonObject.getString("품목명"));
                        drug.setShape(jsonObject.getString("의약품제형"));
                        drug.setType(jsonObject.getString("제형코드명"));

                        list.add(drug);
                    }
                }
                else  {
                    if (searchmarkback.equals(jsonObject.getString("표시뒤")))
                    {
                        Drug drug = new Drug();
                        Log.e("표시뒤 : ", jsonObject.getString("품목명") + jsonObject.getString("색상앞") + jsonObject.getString("의약품제형") + jsonObject.getString("제형코드명") + jsonObject.getString("표시앞") + jsonObject.getString("표시뒤"));
                        drug.setColor(jsonObject.getString("색상앞"));
                        drug.setImage(jsonObject.getString("큰제품이미지"));
                        drug.setName(jsonObject.getString("품목명"));
                        drug.setShape(jsonObject.getString("의약품제형"));
                        drug.setType(jsonObject.getString("제형코드명"));

                        list.add(drug);
                    }
                }
            }

            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
        }catch (Exception e){e.printStackTrace();}
    }


}
