>>#### 2-4-2 모양으로 약 검색   
1)'의약품안전나라'사이트에서 csv형식으로 제공하는 '의약품 낱알식별' 파일을 다운로드 받는다.
<img src="https://user-images.githubusercontent.com/57400913/86558535-9c813580-bf94-11ea-8dac-a6032270ccf8.png" width="70%">   

2)csv형식을 안드로이드 스튜디오에서 사용하기 좋게 json형식으로 변환한다.     
<div>
<img src="https://user-images.githubusercontent.com/57400913/86558548-a2771680-bf94-11ea-9fb8-a8ce03f54e16.png" width="40%">
<img src="https://user-images.githubusercontent.com/57400913/86558552-a4d97080-bf94-11ea-89b7-8f1752c71524.png" width="40%">
</div>
   
3)app폴더 아래에 assets폴더를 생성한 후에 json으로 변환한 파일을 넣어준다.
<img src="https://user-images.githubusercontent.com/57400913/86558778-4234a480-bf95-11ea-82fb-facc8f9ec789.png" width="70%">

4)FormMainActivity.java 폴더를 생성한다.
##### 색상, 모양, 제형 버튼을 클릭하기 위한 버튼들 배열로 생성, 초기화
~~~java
public class FormMainActivity extends AppCompatActivity {
    private static final String TAG = "Ma";

    // 각각의 카테고리에서 최종적으로 선택한 것 저장
    private String choosecolor = null; // 선택한 색상 저장
    private String chooseshape = null; // 선택한 모양 저장
    private String choosetype = null; // 선택한 제형 저장
    private String searchmarkfront = null; // 식별자 검색 저장(앞)
    private String searchmarkback = null; // 식별자 검색 저장(뒤)

    //색상 버튼과 관련
    Button[] colorBtn = new Button[16]; //색상 버튼 배열
    Button result_colorbtn; //버튼의 id값 저장
    private String colorbtn_id; //버튼의 id값
    private String thiscolor; // 비교할 색상 값

    //모양 버튼과 관련
    Button[] shapeBtn = new Button[11]; //모양 버튼 배열
    Button result_shapebtn; //버튼의 id값 저장
    private String shapebtn_id; //버튼의 id값
    private String thisshape; // 비교할 색상 값

    //제형 버튼과 관련
    Button[] typeBtn = new Button[4]; //모양 버튼 배열
    Button result_typebtn; //버튼의 id값 저장
    private String typebtn_id; //버튼의 id값
    private String thistype; // 비교할 색상 값
    ~~~
    
