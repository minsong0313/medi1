package com.example.medi1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import static com.example.medi1.R.id.buttonYellow;
import static com.example.medi1.R.id.choosecolor;
import static com.example.medi1.R.id.text;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private static final String TAG = "Ma";

    //식별자 앞 edit 초기화

    Button buttonWhite, buttonYellow, buttonOrange, buttonPink,buttonGreen,buttonRed,buttonBrown,buttonBlack;
    Button buttonYellowgreen,buttonBluishgreen,buttonBlue,buttonNavy,buttonPurple,buttonViolet,buttonGray,buttonTransparente;
    private String choosecolor = null; // 선택한 색상 저장
    private String chooseshape = null; // 선택한 모양 저장
    private String choosetype = null; // 선택한 제형 저장
    private String searchmarkfront = null; // 식별자 검색 저장(앞)
    private String searchmarkback = null; // 식별자 검색 저장(뒤)

    TextView textco;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            textco = (TextView) findViewById(R.id.choosecolor);
           // textco = new TextView(this);
    //    Log.e("아아", coocolor);

        //색상 버튼 초기화
        buttonWhite = (Button) findViewById(R.id.buttonWhite);
        buttonWhite.setOnClickListener(this);
        buttonGreen = (Button) findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(this);
        buttonYellow = (Button) findViewById(R.id.buttonYellow);
        buttonYellow.setOnClickListener(this);
        buttonOrange = (Button) findViewById(R.id.buttonOrange);
        buttonOrange.setOnClickListener(this);
        buttonPink = (Button) findViewById(R.id.buttonPink);
        buttonPink.setOnClickListener(this);
        buttonRed = (Button) findViewById(R.id.buttonRed);
        buttonRed.setOnClickListener(this);
        buttonBrown = (Button) findViewById(R.id.buttonBrown);
        buttonBrown.setOnClickListener(this);
        buttonYellowgreen = (Button) findViewById(R.id.buttonYellowgreen);
        buttonYellowgreen.setOnClickListener(this);
        buttonBluishgreen = (Button) findViewById(R.id.buttonBluishgreen);
        buttonBluishgreen.setOnClickListener(this);
        buttonBlue = (Button) findViewById(R.id.buttonBlue);
        buttonBlue.setOnClickListener(this);
        buttonNavy = (Button) findViewById(R.id.buttonNavy);
        buttonNavy.setOnClickListener(this);
        buttonPurple = (Button) findViewById(R.id.buttonPurple);
        buttonPurple.setOnClickListener(this);
        buttonViolet = (Button) findViewById(R.id.buttonViolet);
        buttonViolet.setOnClickListener(this);
        buttonGray = (Button) findViewById(R.id.buttonGray);
        buttonGray.setOnClickListener(this);
        buttonBlack = (Button) findViewById(R.id.buttonBlack);
        buttonBlack.setOnClickListener(this);
        buttonTransparente = (Button) findViewById(R.id.buttonTransparente);
        buttonTransparente.setOnClickListener(this);


        //모양 버튼 초
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


    //색상, 모양, 제형 버튼 클릭 함수
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //////////색상///////////
            case R.id.buttonWhite:
                //Log.e("dd", String.valueOf(count));
                choosecolor="하양";
                textco.setText(buttonWhite.getText());
                break;
            case R.id.buttonYellow:
                choosecolor="노랑";
                textco.setText(buttonYellow.getText());
                break;
            case R.id.buttonOrange:
                choosecolor="주황";
                textco.setText(buttonOrange.getText());
                break;
            case R.id.buttonPink:
                choosecolor="분홍";
                textco.setText(buttonPink.getText());
                break;
            case R.id.buttonRed:
                choosecolor="빨강";
                textco.setText(buttonRed.getText());
                break;
            case R.id.buttonBrown:
                choosecolor="갈색";
                textco.setText(buttonBrown.getText());
                break;
            case R.id.buttonYellowgreen:
                choosecolor="연두";
                textco.setText(buttonYellowgreen.getText());
                break;
            case R.id.buttonGreen:
                choosecolor="초록";
                textco.setText(buttonGreen.getText());
                break;
            case R.id.buttonBluishgreen:
                choosecolor="청록";
                textco.setText(buttonBluishgreen.getText());
                break;
            case R.id.buttonBlue:
                choosecolor="파랑";
                textco.setText(buttonBlue.getText());
                break;
            case R.id.buttonNavy:
                choosecolor="남색";
                textco.setText(buttonNavy.getText());
                break;
            case R.id.buttonPurple:
                choosecolor="자주";
                textco.setText(buttonPurple.getText());
                break;
            case R.id.buttonViolet:
                choosecolor="보라";
                textco.setText(buttonViolet.getText());
                break;
            case R.id.buttonGray:
                choosecolor="회색";
                textco.setText(buttonGray.getText());
                break;
            case R.id.buttonBlack:
                choosecolor="검정";
                textco.setText(buttonBlack.getText());
                break;
            case R.id.buttonTransparente:
                textco.setText(buttonTransparente.getText());
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
            searchmarkfront = "-"; // 입력된 값이 없을때 '-'로 저장
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


    //검색 결과 버튼
    public void click_result(View view) {

        /*
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
            choosecolor = "하양, 노랑, 주황, 분홍, 빨강, 갈색, 연두, 초록, 청록, 파랑, 남색, 자주, 보라, 회색, 검정, 투명";
            chooseshape = "원형, 타원, 반원, 삼각형, 사각형, 마름모, 장방형, 오각형, 육각형, 팔각형, 기타";

        }


         */

        Intent intent = new Intent(getApplicationContext(),SearchList.class);
        intent.putExtra("choosecolor",choosecolor);
        intent.putExtra("chooseshape",chooseshape);
        intent.putExtra("choosetype",choosetype);

        startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
    }

    //식별자 검색 결과 버튼
    public void click_markresult(View view) {
        takeMarkfront(); // 식별자 앞 edit에 입력한 텍스트값 가져오기
        takeMarkBack();

        Intent intent = new Intent(getApplicationContext(),SearchList.class);
        intent.putExtra("searchmarkfront",searchmarkfront);
        intent.putExtra("searchmarkback", searchmarkback);

        startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
    }


    //초기화 버튼
    public void click_research(View view) {
        choosecolor = null;
        chooseshape = null;
        choosetype = null;
        textco.setText("");
    }
}