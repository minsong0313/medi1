>#### 2-2 약국 찾기
>>##### 2-2-1 위치 관련 퍼미션 허용과 GPS 활성화
>>##### 2-2-2 사용자의 현재 위치 확인 
>>##### 2-2-3 현재 위치를 기반으로 주변 약국 검색
>>##### 2-2-4 ‘동이름’으로 약국 검색


<hr/>


>### 2-2 약국 찾기
주변 약국을 찾기 위해 사용하는 지도와 주변 약국의 위치를 사용하기 위해 Google Map과 Google Place를 사용한다.   

1)Google Developers Console사이트(https://console.developers.google.com/apis/dashboard)에 접속하여 새 프로젝트를 생성한다.   
<img src="https://user-images.githubusercontent.com/57400913/86548778-967d5b80-bf78-11ea-874e-6af154fa64f7.png" width="70%">

2)사용자 인증 정보 만들기 - API 키 - 생성 완료
<img src="https://user-images.githubusercontent.com/57400913/86549992-21ac2080-bf7c-11ea-8031-e9bbc339137e.png" width="70%">   


3)API 라이브러리에서 Maps SDK for Android와 Place API을 사용설정 한다.
<img src="https://user-images.githubusercontent.com/57400913/86551257-887f0900-bf7f-11ea-8d94-6ea94830123a.png" width="70%">   
          
4)Google Service를 사용하기 위해서 Google Play services 라이브러리 패키지를 설치해줘야 한다.   
안드로이드 스튜디오 - Tools - SDK Manager - SDK Tools탭 클릭 - Google Play services체크 - Apply   
<img src="https://user-images.githubusercontent.com/57400913/86551723-e06a3f80-bf80-11ea-98ca-94354ef16e20.png" width="70%"> 

5)AndroidManifest.xml파일의 <application>태그의 하위 요소로 추가한다.   
  2)에서 발급받은 API키를 추가한다.
~~~jave
<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="발급받은 API키"/>
~~~   

6)Gradle Scripts - Module app의 build.gradle에 라이브러리를 사용하기 위해 추가한다.   
추가 후 - Sync Now 클릭
~~~java
dependencies {
    implementation 'com.google.android.gms:play-services-maps:17.0.0'
    implementation 'com.google.android.gms:play-services-location:17.0.0'
    implementation 'noman.placesapi:placesAPI:1.1.3'
    }
   ~~~   
   
7)MapMainActivity.java를 만들고 지도를 생성해준다.
~~~java
package com.example.androidlogin;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class MapMainActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback, PlacesListener {
    PharmParser parser = new PharmParser();
    String data;
    private GoogleMap mMap;
    private Marker currentMarker = null;
    Button handle_btn;
    EditText edit;
    String getedit; //약국 동이름으로 검색하는 edittext

    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 1000; //권한 설정을 한 activity에 request값으로 받아올 변수 설정
    private static final int UPDATE_INTERVAL_MS = 1000;  // 1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500; // 0.5초

    // onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;


    // 앱을 실행하기 위해 필요한 퍼미션 정의
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};  // 외부 저장소

    Location mCurrentLocatiion;
    LatLng currentPosition;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;

    private View mLayout;  // Snackbar 사용하기 위해서 View가 필요
    List<Marker> previous_marker = null; //google place에서 얻어온 약국 마커 표시

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.map_activity_main);

        previous_marker = new ArrayList<Marker>();

        handle_btn = (Button) findViewById(R.id.handle);
        edit = (EditText) findViewById(R.id.edit);

        //약국 찾기 버튼 눌렀을때(마커 생성)
        Button button = (Button)findViewById(R.id.pharm_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlaceInformaiton(currentPosition);
            }
        });

        mLayout = findViewById(R.id.layout_main);

        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_MS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        parser.edit = (EditText)findViewById(R.id.edit);
        parser.text = (TextView)findViewById(R.id.result);

        // 홈으로 이동하는 버튼 객체 생성
        ImageButton btn_home = findViewById(R.id.gohome);

        // 홈 버튼 onclicklistener 생성
        btn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 버튼을 누르면 메인화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

        });

    }
~~~   

>>##### 2-2-1 위치 관련 퍼미션 허용과 GPS 활성화   
##### 위치 관련 퍼미션 
~~~java
@Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 퍼미션 허용됐는지 체크
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            if ( check_result ) {
                // 퍼미션을 허용했다면 위치 업데이트를 시작
                startLocationUpdates();
            }

            else {

                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료함
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {


                    //사용자가 퍼미션을 거부했을때 뜨는 메시지
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    }).show();

                }else {

                    //사용자가 "다시 묻지 않음"을 누르고 퍼미션을 거부하면 설정-앱 정보에서 퍼미션을 허용해야 사용 가능함을 알림
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 활성화하려면 설정-앱 정보 에서 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();
                }
            }

        }
    }
 ~~~   
 
##### GPS활성화 여부 검사 
~~~java
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MapMainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
 ~~~
