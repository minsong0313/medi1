package com.example.medi1;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private static final String TAG = "Ma";
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    ArrayList<Drug> list = null;

    Gson gson = new Gson();

    public String choosecolor = null; // 선택한 색상 저장
    public String chooseshape = null; // 선택한 모양 저장
    public String chooosetype = null; // 선택한 제형 저장
    public String choosespline = null; // 선택한 분할선 저장

    public String name;
    public String shape;
    public String type;
    public String spline;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_recyclerview);

        //색상 버튼 초기화
        Button buttonWhite = (Button) findViewById(R.id.buttonWhite);
        buttonWhite.setOnClickListener(this);
        Button buttonGreen = (Button) findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(this);
        Button buttonYellow = (Button) findViewById(R.id.buttonYellow);
        buttonYellow.setOnClickListener(this);
        Button buttonOrange = (Button) findViewById(R.id.buttonOrange);
        buttonOrange.setOnClickListener(this);
        Button buttonPink = (Button) findViewById(R.id.buttonPink);
        buttonPink.setOnClickListener(this);
        Button buttonRed = (Button) findViewById(R.id.buttonRed);
        buttonRed.setOnClickListener(this);
        Button buttonBrown = (Button) findViewById(R.id.buttonBrown);
        buttonBrown.setOnClickListener(this);
        Button buttonYellowgreen = (Button) findViewById(R.id.buttonYellowgreen);
        buttonYellowgreen.setOnClickListener(this);
        Button buttonBluishgreen = (Button) findViewById(R.id.buttonBluishgreen);
        buttonBluishgreen.setOnClickListener(this);
        Button buttonBlue = (Button) findViewById(R.id.buttonBlue);
        buttonBlue.setOnClickListener(this);
        Button buttonNavy = (Button) findViewById(R.id.buttonNavy);
        buttonNavy.setOnClickListener(this);
        Button buttonPurple = (Button) findViewById(R.id.buttonPurple);
        buttonPurple.setOnClickListener(this);
        Button buttonViolet = (Button) findViewById(R.id.buttonViolet);
        buttonViolet.setOnClickListener(this);
        Button buttonGray = (Button) findViewById(R.id.buttonGray);
        buttonGray.setOnClickListener(this);
        Button buttonBlack = (Button) findViewById(R.id.buttonBlack);
        buttonBlack.setOnClickListener(this);
        Button buttonTransparente = (Button) findViewById(R.id.buttonTransparente);
        buttonTransparente.setOnClickListener(this);

        //모양 버튼 초기화
        Button buttonCircle = (Button) findViewById(R.id.buttonCircle);
        buttonCircle.setOnClickListener(this);
        Button buttonOval = (Button) findViewById(R.id.buttonOval);
        buttonOval.setOnClickListener(this);
        Button buttonSemicircle = (Button) findViewById(R.id.buttonSemicircle);
        buttonSemicircle.setOnClickListener(this);
        Button buttonTriangle = (Button) findViewById(R.id.buttonTriangle);
        buttonTriangle.setOnClickListener(this);
        Button buttonSquare = (Button) findViewById(R.id.buttonSquare);
        buttonSquare.setOnClickListener(this);
        Button buttonDiamond = (Button) findViewById(R.id.buttonDiamond);
        buttonDiamond.setOnClickListener(this);
        Button buttonRectangle = (Button) findViewById(R.id.buttonRectangle);
        buttonRectangle.setOnClickListener(this);
        Button buttonPentagon = (Button) findViewById(R.id.buttonPentagon);
        buttonPentagon.setOnClickListener(this);
        Button buttonHexagon = (Button) findViewById(R.id.buttonHexagon);
        buttonHexagon.setOnClickListener(this);
        Button buttonOctagon = (Button) findViewById(R.id.buttonOctagon);
        buttonOctagon.setOnClickListener(this);
        Button buttonEct = (Button) findViewById(R.id.buttonEct);
        buttonEct.setOnClickListener(this);
    }

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//

    //조건 버튼 누른 후 조건에 맞는 의약품 검색 실행
    public void openJson(){
        try{
            InputStream is = getAssets().open("druglist_final.json"); //assests파일에 저장된 druglist_final.json 파일 열기
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("druglist"); //json파일에서 의약품리스트의 배열명, jsonArray로 저장

            list = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                // list.add(jsonObject.getString("품목명")+jsonObject.getString("색상앞")+jsonObject.getString("의약품제형")+jsonObject.getString("큰제품이미지"));
                if(jsonObject.getString("색상앞").equals(choosecolor) && jsonObject.getString("의약품제형").equals(chooseshape)){
                    Drug drug = new Drug();
                    Log.e("druglist : ", jsonObject.getString("품목명")+jsonObject.getString("색상앞")+jsonObject.getString("의약품제형")+jsonObject.getString("큰제품이미지"));
                    drug.setColor(jsonObject.getString("색상앞"));
                    drug.setImage(jsonObject.getString("큰제품이미지"));
                    drug.setName(jsonObject.getString("품목명"));
                    drug.setShape(jsonObject.getString("의약품제형"));

                    list.add(drug);
                }

            }

            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//
        }catch (Exception e){e.printStackTrace();}

    }

    //조건 버튼 함수
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //////////색상///////////
            case R.id.buttonWhite:
                choosecolor="하양";
                break;
            case R.id.buttonYellow:
                choosecolor="노랑";
                break;
            case R.id.buttonOrange:
                choosecolor="주황";
                break;
            case R.id.buttonPink:
                choosecolor="분홍";
                break;
            case R.id.buttonRed:
                choosecolor="빨강";
                break;
            case R.id.buttonBrown:
                choosecolor="갈색";
                break;
            case R.id.buttonYellowgreen:
                choosecolor="연두";
                break;
            case R.id.buttonGreen:
                choosecolor="초록";
                break;
            case R.id.buttonBluishgreen:
                choosecolor="청록";
                break;
            case R.id.buttonBlue:
                choosecolor="파랑";
                break;
            case R.id.buttonNavy:
                choosecolor="남색";
                break;
            case R.id.buttonPurple:
                choosecolor="자주";
                break;
            case R.id.buttonViolet:
                choosecolor="보라";
                break;
            case R.id.buttonGray:
                choosecolor="회색";
                break;
            case R.id.buttonBlack:
                choosecolor="검정";
                break;
            case R.id.buttonTransparente:
                choosecolor="투명";
                break;

            //////////모양//////////
            case R.id.buttonCircle:
                chooseshape="원형";
                break;
            case R.id.buttonOval:
                chooseshape="타원형";
                break;
            case R.id.buttonSemicircle:
                chooseshape="반원형";
                break;
            case R.id.buttonTriangle:
                chooseshape="삼각형";
                break;
            case R.id.buttonSquare:
                chooseshape="사각형";
                break;
            case R.id.buttonDiamond:
                chooseshape="마름모형";
                break;
            case R.id.buttonRectangle:
                chooseshape="장방형";
                break;
            case R.id.buttonPentagon:
                chooseshape="오각형";
                break;
            case R.id.buttonHexagon:
                chooseshape="육각형";
                break;
            case R.id.buttonOctagon:
                chooseshape="팔각형";
                break;
            case R.id.buttonEct:
                chooseshape="기타";
                break;

        }
    }




    public void click_btn(View view) {
        openJson();
        mAdapter = new MyAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }



}