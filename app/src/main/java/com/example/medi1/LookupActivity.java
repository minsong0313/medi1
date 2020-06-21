package com.example.medi1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class LookupActivity extends SearchList{
    TextView textView;
    TextView detailStr;
    ImageView imageView;

    String drugString;
    String str_detailStr;
    String image;


    @Override
    public void onBackPressed() {
        //startActivity(new Intent(getApplication(),SearchList.class));
        super.onBackPressed();
        //finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);
        textView = findViewById(R.id.textView);
        detailStr = findViewById(R.id.detailStr);
        imageView = findViewById(R.id.image);


        //Drug라는 key값으로 NameMyAdapter에서 intent해줄때 넘겨준 값을 가져옴.
        drugString = getIntent().getStringExtra("Drug");//String값으로 받아옴. 이것은 약의 이름을 받아오는것.


        str_detailStr = getIntent().getStringExtra("data");

        //NameMyAdapter.java파일에서 intent로 넘겨준 image를 받아와 byte배열에 저장 후 decode하여 imageview에 보여줌.
        image = getIntent().getStringExtra("image");
        //Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);

        Glide.with(this)
                .load(image)
                .into(imageView);

        //textView와 imageView에 받아온 값들을각각 저장해줌.
        textView.setText(drugString);
        detailStr.setText(str_detailStr);
        //imageView.setImageBitmap(image);

    }
}
