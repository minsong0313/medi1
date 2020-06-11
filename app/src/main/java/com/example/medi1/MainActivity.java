package com.example.medi1;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private static final String TAG = "Ma";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    Gson gson = new Gson();

    ///////////////////////////색상 버튼////////////////////////////
    public String choosecolor = null;
    public ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_recyclerview);

        AssetManager assetManager = getResources().getAssets();
        try {
            AssetManager.AssetInputStream is = (AssetManager.AssetInputStream) assetManager.open("druglist3.json");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str = "";
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            String res = sb.toString();
            JSONObject obj = new JSONObject(res);
            String family = obj.getString("Drug"); // hong
            Toast.makeText(getApplicationContext(), "Drug = " + family, Toast.LENGTH_SHORT)
                    .show();

            JSONArray jsonArray = new JSONArray(obj.getString("druglist"));
            String co = null;

            for (int i = 0; i < jsonArray.length(); i++) {
                String name = jsonArray.getJSONObject(i).getString("품목명");
                String tel = jsonArray.getJSONObject(i).getString("색상앞");
                String s = "품목명=" + name + ",색상=" + tel;
                //Toast.makeText(getApplicationContext(), "s=>" + s,
                //      Toast.LENGTH_SHORT).show();

                if (tel.equals("하양")) {
                    arrayList.add(s);
                    Log.d(TAG, "dd=" + s);
                }
                mAdapter = new DrugAdapter(arrayList);
            }


        } catch (Exception e) {
            Log.d(TAG, "예외 발생 =" + e);
        }
        Button buttonWhite = (Button) findViewById(R.id.buttonWhite);
        buttonWhite.setOnClickListener(this);
        Button buttonGreen = (Button) findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(this);
        Button buttonYellow = (Button) findViewById(R.id.buttonYellow);
        buttonYellow.setOnClickListener(this);

    }

    // try{
    //   InputStream is = getAssets().open("druglist3.json");
    // byte[] buffer = new byte[is.available()];
    // is.read(buffer);
    // is.close();
    // String json = new String(buffer, "UTF-8");

    // JSONObject jsonObject = new JSONObject(json);

    //   String jsonValue = jsonObject.getString("색상앞");
    //    JSONObject jsonObject1 = new JSONObject(jsonValue);
    //    Iterator i = jsonObject1.keys();

    //   Map<String,Object> Drug= gson.fromJson( jsonObject.get("Drug").toString(),new TypeToken<Map<String, Object>>(){}.getType());


    //    ArrayList<Map<String, Object>> jsonList = (ArrayList) Drug.get("druglist");


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