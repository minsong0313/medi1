package com.example.medi1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.Toast;

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
    private ProgressDialog progressDialog; //로딩중 progressDialog
    //식별자 앞 edit 초기화

//제형 버튼
    private String choosecolor = null; // 선택한 색상 저장
    private String chooseshape = null; // 선택한 모양 저장
    private String choosetype = null; // 선택한 제형 저장
    private String searchmarkfront = null; // 식별자 검색 저장(앞)
    private String searchmarkback = null; // 식별자 검색 저장(뒤)

    private String buttonId1;
    private String thiscolor;

    Button result;
    Button [] colorBtn = new Button[16]; //색상 버튼 배열
    Button [] shapeBtn = new Button[11]; //모양 버튼 배열
    //Button [] typeBtn = new Button[16]; //제형 버튼 배열
    TextView textcolor, textshape, texttype;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //색상 버튼 눌린것 텍스트뷰로 띄워줄것
        textcolor = (TextView) findViewById(R.id.choosecolor);
        textshape = (TextView) findViewById(R.id.chooseshape);
        texttype = (TextView) findViewById(R.id.choosetype);
        //로딩중 progressdialog
        progressDialog = new ProgressDialog(this);

        thiscolor = textcolor.getText().toString();
        Log.e("지금 색:", thiscolor);

        settingColorbtn();
        settingShapebtn();

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




    public void settingColorbtn(){
        for(int i=0; i <colorBtn.length; i++){
            buttonId1 = "color_btn" + (i+1); //버튼 아이디값 저장
            colorBtn[i] = findViewById(getResources().getIdentifier(buttonId1, "id",getPackageName())); //버튼 초기화

        }

        for(Button buttonId : colorBtn){
            buttonId.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    result = findViewById(v.getId());
                    result.setBackgroundColor(Color.YELLOW); //해당아이디 버튼의 배경색을 하양으로 바꿈
                    choosecolor = result.getText().toString(); //선택 색상을 저장

                    //////여기서 for문으로 thiscolor랑 result.getText.toString()비교해서 배경색 다시 바꿔주기
                    Log.e("다음 클릭 후 : ", thiscolor);

                    for(int j=0; j<colorBtn.length; j++){
                        if(!colorBtn[j].getText().toString().equals(choosecolor)) {
                                    colorBtn[j].setBackgroundColor(Color.WHITE);

                        }if(colorBtn[j].getText().toString().equals(thiscolor)){
                            colorBtn[j].setBackgroundColor(Color.WHITE);
                        }
                    }

                  //  textcolor.setText(result.getText()); // 선택 색상을 보여줄 textview

                    thiscolor = textcolor.getText().toString();

                }
            });
        }


    } //색상 버튼 이벤트
    public void settingShapebtn(){
        for(int i=0; i <shapeBtn.length; i++){
            String buttonId = "shape_btn" + (i+1); //버튼 아이디값 저장
            shapeBtn[i] = findViewById(getResources().getIdentifier(buttonId, "id",getPackageName()));
        }

        for(Button buttonId : shapeBtn){
            buttonId.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Button result = findViewById(v.getId());
                    chooseshape = result.getText().toString();
                    textshape.setText(result.getText());
                }
            });
        }
    } // 모양 버튼 이벤트



    //색상, 모양, 제형 버튼 클릭 함수
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            
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
            searchmarkfront = null; // 입력된 값이 없을때 '-'로 저장
        }else {
            searchmarkfront=this.searchmarkfront;
        }
    }

    //식별자 뒤 edittext값 초기화, 저장
    public void takeMarkBack(){
        EditText markback = (EditText) findViewById(R.id.mark_Back);
        searchmarkback = markback.getText().toString();
        if(searchmarkback.length() == 0){
            searchmarkback = null;
        }else{
            searchmarkback = this.searchmarkback;
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
      //  progressDialog.setMessage("로딩중입니다.");
       // progressDialog.show();

       //takeMarkfront(); // 식별자 앞 edit에 입력한 텍스트값 가져오기
       //takeMarkBack();

        Intent intent = new Intent(getApplicationContext(),SearchList.class);
        intent.putExtra("choosecolor",choosecolor);
        intent.putExtra("chooseshape",chooseshape);
        intent.putExtra("choosetype",choosetype);
        //intent.putExtra("searchmarkfront",searchmarkfront);
        //intent.putExtra("searchmarkback", searchmarkback);

        startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
        //progressDialog.dismiss();
    }

    //식별자 검색 결과 버튼
    public void click_markresult(View view) {
        progressDialog.setMessage("로딩중입니다.");
        progressDialog.show();

        takeMarkfront(); // 식별자 앞 edit에 입력한 텍스트값 가져오기
        takeMarkBack();

        Intent intent = new Intent(getApplicationContext(),SearchList.class);

        intent.putExtra("searchmarkfront",searchmarkfront);
        intent.putExtra("searchmarkback", searchmarkback);


        startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
        //progressDialog.dismiss();
    }


    //초기화 버튼
    public void click_research(View view) {
        choosecolor = null;
        chooseshape = null;
        choosetype = null;
        textcolor.setText("");
        textshape.setText("");
        Toast myToast = Toast.makeText(this.getApplicationContext(),"선택이 초기화 되었습니다.", Toast.LENGTH_SHORT);
        myToast.show();

        for(int i=0; i <colorBtn.length; i++){
            colorBtn[i].setBackgroundColor(Color.WHITE);
        }

    }
}