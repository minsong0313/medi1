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
    
##### 색상 버튼 이벤트
choosecolor는 사용자가 선택한 버튼에 맞는 검색 결과를 보여주기 위한 값을 저장하는 변수이며, thiscolor는 사용자가 누른 버튼의 배경색만 변경해주기 위한 값을 저장하는 변수이다.   

1)사용자가 누른 버튼의 배경색을 하양색으로 변경하고 choosecolor와 thiscolor에 버튼의 text값을 저장한다.   
2)사용자가 버튼을 누를때마다 choosecolor와 thiscolor의 값이 바뀐다.   
3)사용자가 누른 버튼의 색만 변경해주기 위해서 버튼을 누를 때마다 반복문을 이용해서 버튼의 수만큼 각 버튼의 text값과 현재 선택한 값이    choosecolor를 비교해서 값이 다르다면 원래의 색으로 변경해준다.    
4)또, 바로 이 전에 누른 버튼의 text값과 thiscolr의 값을 비교해주어 같다면 배경색을 원래의 색으로 변경해준다.   
~~~java
public void settingColorbtn(){
        for(int i=0; i <colorBtn.length; i++){
            colorbtn_id = "color_btn" + (i+1); //버튼 아이디값 저장
            colorBtn[i] = findViewById(getResources().getIdentifier(colorbtn_id, "id",getPackageName())); //버튼 초기화

        }

        for(Button buttonId : colorBtn){
            buttonId.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    result_colorbtn = findViewById(v.getId());
                    result_colorbtn.setBackgroundResource(R.drawable.choose_btton); //해당아이디 버튼의 배경색을 바꿈
                    result_colorbtn.setTextColor(Color.WHITE);
                    choosecolor = result_colorbtn.getText().toString(); //선택 색상을 저장

                    //////여기서 for문으로 thiscolor랑 result.getText.toString()비교해서 배경색 다시 바꿔주기
                    Log.e("다음 클릭 후 : ", thiscolor);

                    for(int j=0; j<colorBtn.length; j++){
                        if(!colorBtn[j].getText().toString().equals(choosecolor)) {
                            colorBtn[j].setBackgroundResource(R.drawable.basic_button);
                            colorBtn[j].setTextColor(Color.BLACK);
                        }if(colorBtn[j].getText().toString().equals(thiscolor)){
                            colorBtn[j].setBackgroundResource(R.drawable.basic_button);
                            colorBtn[j].setTextColor(Color.BLACK);
                        }
                    }

                    thiscolor = textcolor.getText().toString();

                }
            });
        }

    }
~~~    
##### 모양 버튼 이벤트   
색상 버튼 이벤트와 동일한 방식으로 버튼의 배경색 처리를 한다.   
~~~java
public void settingShapebtn(){
        for(int i=0; i <shapeBtn.length; i++){
            shapebtn_id = "shape_btn" + (i+1); //버튼 아이디값 저장
            shapeBtn[i] = findViewById(getResources().getIdentifier(shapebtn_id, "id",getPackageName()));
        }

        for(Button buttonId : shapeBtn){
            buttonId.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    result_shapebtn = findViewById(v.getId());
                    result_shapebtn.setBackgroundResource(R.drawable.choose_btton); //해당아이디 버튼의 배경색을 하양으로 바꿈
                    result_shapebtn.setTextColor(Color.WHITE);
                    chooseshape = result_shapebtn.getText().toString();


                    Log.e("다음 클릭 후 : ", thisshape);

                    for(int j=0; j<shapeBtn.length; j++){
                        if(!shapeBtn[j].getText().toString().equals(chooseshape)) {
                            shapeBtn[j].setBackgroundResource(R.drawable.basic_button);
                            shapeBtn[j].setTextColor(Color.BLACK);
                        }if(shapeBtn[j].getText().toString().equals(thisshape)){
                            shapeBtn[j].setBackgroundResource(R.drawable.basic_button);
                            shapeBtn[j].setTextColor(Color.BLACK);
                        }
                    }

                    //  textcolor.setText(result.getText()); // 선택 색상을 보여줄 textview

                    thisshape = textshape.getText().toString();
                }
            });
        }
    }
~~~   
##### 제형 버튼 이벤트
색상과 모양 버튼의 버튼의 text값과 동일하기 때문에 클릭한 버튼의 text값을 바로 변수에 저장해주었지만   
제형 버튼은 공공데이터에서 제공하는 파일의 형식이 맞추려면 과정이 복잡해진다   
(ex.정제류 - 나정, 필름코팅정, 서방정, 저작정, 추어블정(저작정), 구강붕해정, 서방성필름코팅정, 장용성필름코팅정, 다층정, 분산정(현탁정))     
1)제형 버튼 중 클릭한 버튼의 text값을 choosetype에 저장한다.
2)버튼의 text값과 json파일에 저장되어있는 제형의 종류를 공통으로 포함된 문자열을 비교한 후에 다시 choosetype에 모든 종류를 저장한다.
3)이후에 사용자가 선택한 버튼의 배경색만 변경하는 부분은 위의 색상 이벤트에서 설명한것과 동일하다.
~~~java
public void settingTypebtn(){
        for(int i=0; i <typeBtn.length; i++){
            typebtn_id = "type_btn" + (i+1); //버튼 아이디값 저장
            typeBtn[i] = findViewById(getResources().getIdentifier(typebtn_id, "id",getPackageName())); //초기화
        }

        for(Button buttonId : typeBtn){
            buttonId.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    result_typebtn = findViewById(v.getId());
                    result_typebtn.setBackgroundResource(R.drawable.choose_btton); //해당아이디 버튼의 배경색을 하양으로 바꿈
                    result_typebtn.setTextColor(Color.WHITE);
                    choosetype = result_typebtn.getText().toString();

                    if(choosetype.contains("정")){
                        choosetype = "나정, 필름코팅정, 서방정, 저작정, 추어블정(저작정), 구강붕해정, 서방성필름코팅정, 장용성필름코팅정, 다층정, 분산정(현탁정), 정제";
                    }else if(choosetype.contains("경질")){
                        choosetype = "경질캡슐제|산제, 경질캡슐제|과립제, 경질캡슐제|장용성과립제, 스팬슐, 서방성캡슐제|펠렛";
                    }else if(choosetype.contains("연질")){
                        choosetype ="연질캡슐제|현탁상, 연질캡슐제|액상";
                    } else if(choosetype.contains("기타")){
                        choosetype = "껌제, 트로키제";
                    }

                    //texttype.setText(choosetype);

                    Log.e("choosetype ?????", choosetype);
                    Log.e("다음 클릭 후 : ", thistype);

                    for(int j=0; j<typeBtn.length; j++){

                        if(typeBtn[j].getText().toString().contains("정")){
                            if(!choosetype.contains("정")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                            if(thisshape.contains("정")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                        }else if(typeBtn[j].getText().toString().contains("경질")){
                            if(!choosetype.contains("경질")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                            if(thisshape.contains("경질")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                        }else if(typeBtn[j].getText().toString().contains("연질")){
                            if(!choosetype.contains("연질")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                            if(thisshape.contains("연질")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                        }else {
                            if(!choosetype.contains("껌제")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                            if(thisshape.contains("제")) {
                                typeBtn[j].setBackgroundResource(R.drawable.basic_button);
                                typeBtn[j].setTextColor(Color.BLACK);
                            }
                        }

                    }

                    //  textcolor.setText(result.getText()); // 선택 색상을 보여줄 textview

                    thistype = texttype.getText().toString();
                }
            });
        }
    }
~~~
