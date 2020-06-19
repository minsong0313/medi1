package com.example.medi1;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private static final String TAG = "Ma";

    //식별자 앞 edit 초기화


    private String choosecolor = null; // 선택한 색상 저장
    private String chooseshape = null; // 선택한 모양 저장
    private String choosetype = null; // 선택한 제형 저장
    private String searchmarkfront = null; // 식별자 검색 저장(앞)
    private String searchmarkback = null; // 식별자 검색 저장(뒤)



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //제형 버튼 초기화
        Button buttonTablet = (Button) findViewById(R.id.buttonTablet);
        buttonTablet.setOnClickListener(this);
        Button buttonHardcapsule = (Button) findViewById(R.id.buttonHardcapsule);
        buttonHardcapsule.setOnClickListener(this);
        Button buttonSoftcapsul = (Button) findViewById(R.id.buttonSoftcapsul);
        buttonSoftcapsul.setOnClickListener(this);
        Button buttonEct2 = (Button) findViewById(R.id.buttonEct2);
        buttonEct2.setOnClickListener(this);


    }

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ//

    //조건 버튼 누른 후 조건에 맞는 의약품 검색 실행

    //색상, 모양, 제형 버튼 클릭 함수
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
                chooseshape="타원";
                break;
            case R.id.buttonSemicircle:
                chooseshape="반원";
                break;
            case R.id.buttonTriangle:
                chooseshape="삼각형";
                break;
            case R.id.buttonSquare:
                chooseshape="사각형";
                break;
            case R.id.buttonDiamond:
                chooseshape="마름모";
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

            //////////제형//////////
            case R.id.buttonTablet:
                choosetype="나정, 필름코팅정, 서방정, 저작정, 구강붕해정, 장용성필름코팅정, 다층정";
                break;
            case R.id.buttonHardcapsule:
                choosetype="경질캡슐제|산제, 경질캡슐제|과립제, 경질캡슐제|장용성과립제";
                break;
            case R.id.buttonSoftcapsul:
                choosetype="연질캡슐제|현탁상, 연질캡슐제|액상";
                break;
            case R.id.buttonEct2:
                choosetype="껌제";
                break;

        }
    }

    //식별자 앞 edittext값 초기화, 저장
    public void takeMarkfront(){
        EditText markfront = (EditText) findViewById(R.id.mark_front);
        searchmarkfront = markfront.getText().toString();
        if(searchmarkfront.length() == 0){
            searchmarkfront = "- "; // 입력된 값이 없을때 '-'로 저장
        }else {
            searchmarkfront=this.searchmarkfront;
        }
    }

    //식별자 뒤 edittext값 초기화, 저장
    public void takeMarkBack(){
        EditText markback = (EditText) findViewById(R.id.mark_Back);
        searchmarkback = markback.getText().toString();
        if(searchmarkback.length() == 0){
            searchmarkback = "-";
        }else{
            searchmarkback = this.searchmarkback; // 입력된 값이 없을때 '-'로 저장
        }
    }


    //검색 결과 버튼 -> 식별자 앞, 뒤에 입력된 값 저장된 것 가져옴, SearchList 로 intent
    public void click_btn(View view) {

        takeMarkfront(); // 식별자 앞 edit에 입력한 텍스트값 가져오기
        takeMarkBack();

        //'색상' 카테고리만 입력됐을 때
        if(chooseshape == null && choosetype == null){
            chooseshape = "원형, 타원, 반원, 삼각형, 사각형, 마름모, 장방형, 오각형, 육각형, 팔각형, 기타";
            choosetype = "경질캡슐제|산제, 경질캡슐제|과립제, 경질캡슐제|장용성과립제, 나정, 필름코팅정, 서방정, 서방성필름코팅정, 저작정, 구강붕해정, 장용성필름코팅정";

        }
        //'모양' 카테고리만 입력됐을 때
        else if(choosecolor == null && choosetype == null){
            choosecolor = "하양, 노랑, 주황, 분홍, 빨강, 갈색, 연두, 초록, 청록, 파랑, 남색, 자주, 보라, 회색, 검정, 투명";
            choosetype = "경질캡슐제|산제, 경질캡슐제|과립제, 경질캡슐제|장용성과립제, 나정, 필름코팅정, 서방정, 서방성필름코팅정, 저작정, 구강붕해정, 장용성필름코팅정";

        }
        //'제형' 카테고리만 입력됐을 때
        else if(choosecolor == null && chooseshape == null){
            choosecolor = "하양, 노랑|투명, 주황, 분홍|투명, 빨강, 갈색, 연두, 초록, 청록, 파랑, 남색, 자주, 보라, 회색, 검정, 투명";
            chooseshape = "원형, 타원, 반원, 삼각형, 사각형, 마름모, 장방형, 오각형, 육각형, 팔각형, 기타";

        }


        Intent intent = new Intent(getApplicationContext(),SearchList.class);
        intent.putExtra("choosecolor",choosecolor);
        intent.putExtra("chooseshape",chooseshape);
        intent.putExtra("choosetype",choosetype);
        intent.putExtra("searchmarkfront",searchmarkfront);
        intent.putExtra("searchmarkback", searchmarkback);

        startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
    }



}