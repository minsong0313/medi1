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


4)AndroidManifest.xml파일의 <application>태그의 하위 요소로 추가한다.   
~~~jave
<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="발급받은 API키"/>
~~~
