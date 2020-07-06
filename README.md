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

4)사용자가 검색한 의약품의 정보들을 저장하기 위한 FormDrug.java 폴더를 생성한다.   
검색한 의약품에 해당하는 품목명, 업소명, 이미지, 분류명, 전문/일반 구분을 사용자에게 제공하기 위해서 getter, setter로 받아오고 불러오기 위함이다.  
~~~java
public class FormDrug {
    //리스트에 띄울 목록
    private String drugName; //품목명
    private String company; // 업소명
    private String image;//이미지 주소
    private String className; //분류명
    private String etcOtcName; // 전문일반구분

    //검색할때 사용, 리스트에 띄우지 않음
    private String shape; //모양
    private String color; //색상
    private String type; //제형
    private String markfront; // 식별 표시 앞
    private String markback; // 식별 표시 뒤

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEtcOtcName() {
        return etcOtcName;
    }

    public void setEtcOtcName(String etcOtcName) {
        this.etcOtcName = etcOtcName;
    }



    //////////////모양 검색할때 사용//////////////

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarkfront() {
        return markfront;
    }

    public void setMarkfront(String markfront) {
        this.markfront = markfront;
    }

    public String getMarkback() {
        return markback;
    }

    public void setMarkback(String markback) {
        this.markback = markback;
    }

}
~~~    

5)FormMainActivity.java 폴더를 생성한다.   
색상, 모양, 제형 카테고리에서 사용자가 선택한 것을 처리한다.     
식별자로 검색할 때, 사용자가 검색하기 위해 Edittext에 입력한 값을 처리한다.      
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
제형 버튼은 공공데이터에서 제공하는 파일의 형식이 맞추려면 과정이 복잡해진다.    
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

##### 색상, 모양, 제형 버튼 선택 초기화
1)사용자가 선택한 버튼을 초기화하기 위해서 클릭하면 choosecolor, chooseshape, choosetype에 모두 null값이 저장된다.   
2)사용자가 선택해서 하양색으로 변한 배경색 또한 원래의 배경색으로 돌아온다.   
3)초기화 되었다는 Toast가 뜬다.   
~~~java
//초기화 버튼
    public void click_research(View view) {
        choosecolor = null;
        chooseshape = null;
        choosetype = null;

        Toast myToast = Toast.makeText(this.getApplicationContext(),"선택이 초기화 되었습니다.", Toast.LENGTH_SHORT);
        myToast.show();

        for(int i=0; i <colorBtn.length; i++){
            colorBtn[i].setBackgroundColor(Color.WHITE);
            colorBtn[i].setBackgroundResource(R.drawable.basic_button);
            colorBtn[i].setTextColor(Color.BLACK);
        }
        for(int i=0; i <shapeBtn.length; i++){
            shapeBtn[i].setBackgroundColor(Color.WHITE);
            shapeBtn[i].setBackgroundResource(R.drawable.basic_button);
            shapeBtn[i].setTextColor(Color.BLACK);
        }
        for(int i=0; i <typeBtn.length; i++){
            typeBtn[i].setBackgroundColor(Color.WHITE);
            typeBtn[i].setBackgroundResource(R.drawable.basic_button);
            typeBtn[i].setTextColor(Color.BLACK);
        }

    }
}
~~~     
<img src="https://user-images.githubusercontent.com/57400913/86567020-a3647400-bfa5-11ea-8dee-0fdac8278d3d.png" width="40%">   

##### 의약품의 앞, 뒤에 쓰여있는 식별 표시로 검색하기    
1)공공데이터로 제공한 파일에서 식별 표시에 없는 의약품의 경우에는 '-'로 저장되어있다.   
2)사용자가 앞이나 뒤 한 곳만 입력했을때도 올바른 결과를 나오게 하기 위해서 입력된 값의 길이를 체크한 후에 공백이면 searchamarkfront와 serachmarkback에 '-'를 저장해준다.   

~~~java
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
~~~

##### 사용자가 선택 또는 입력한 값을 Intent로 넘겨주기
1)최종적으로 choosecolor, chooseshape, choosetype과 searchmarkfront, searchmarkback에 저장된 값을 FormSearchActivity.java폴더에 Intent로 넘겨준다.    
~~~java
//검색 결과 버튼
    public void click_result(View view) {

        Intent intent = new Intent(getApplicationContext(), FormSearchActivity.class);

        intent.putExtra("choosecolor",choosecolor);
        intent.putExtra("chooseshape",chooseshape);
        intent.putExtra("choosetype",choosetype);


        startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
    }

    //식별자 검색 결과 버튼
    public void click_markresult(View view) {

        takeMarkfront(); // 식별자 앞 edit에 입력한 텍스트값 가져오기
        takeMarkBack();

        Intent intent = new Intent(getApplicationContext(), FormSearchActivity.class);
        intent.putExtra("searchmarkfront",searchmarkfront);
        intent.putExtra("searchmarkback", searchmarkback);


        startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
    }
~~~
2)FormMainActivity.java 폴더에서 Intent로 넘어온 값과 일치하는 조건들을 Json파일에서 찾아 배열로 저장한 후에 어댑터로 결과를 넘겨주는 과정을 처리할 FormSearchActivity.java 폴더를 생성한다.   
##### 색상, 모양, 제형 버튼으로 검색한 것인지, 식별 표시로 검색한 것인지 구분
구분하여 서로 다른 메서드를 실행해준다.   
~~~java
        if (choosecolor == null && chooseshape == null && choosetype ==null) {
            marksearchJson();
            Log.e("dg","식별자");
        }
        else {
            searchJson();
            Log.e("dg","컬러");
        }
        recyclerView = (RecyclerView)findViewById(R.id.rv_recyclerview);//리사이클러뷰 초기화
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존 성능 강화

        //리니어레이아웃을 사용하여 리사이클러뷰에 넣어줄것임
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new FormMyAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
~~~
##### 색상, 모양, 제형 버튼으로 검색한 경우
1)세 개의 카테고리 중 한 카테고리에서만 선택해도 올바른 검색 결과를 나오게 하기 위해서 총 7가지 경우로 나누었다.   
2)json파일은 key와 value로 구성되어있는데 사용자가 선택한 값과 일치하는 value값을 찾아 품목명, 제품 이미지, 업소명, 분류명, 전문일반구문 key에 해당하는 value값을 사용자에게 보여주기 위해서 setter에 저장한다.   
 ~~~java
 //json에서 조건에 맞는 것 검색(색상, 모양, 제형) 7가지.
    public void searchJson(){
        try{
            InputStream is = getAssets().open("druglist.json"); //assests파일에 저장된 druglist_final.json 파일 열기
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("druglist"); //json파일에서 의약품리스트의 배열명, jsonArray로 저장

            list = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                //'색상, 모양, 제형' 선택하고 검색하기(3개의 카테고리 중 하나만 선택 하고 검색 가능)
                //1. 색상만 선택된 경우
                if(choosecolor != null && chooseshape == null && choosetype == null){
                    if ((jsonObject.getString("색상앞").contains(choosecolor))) {
                        FormDrug formDrug = new FormDrug();
                        Log.e("1번 : ", jsonObject.getString("품목명") + jsonObject.getString("색상앞") + jsonObject.getString("의약품제형"));

                        formDrug.setImage(jsonObject.getString("큰제품이미지"));
                        formDrug.setDrugName(jsonObject.getString("품목명"));
                        formDrug.setCompany(jsonObject.getString("업소명"));
                        formDrug.setClassName(jsonObject.getString("분류명"));
                        formDrug.setEtcOtcName(jsonObject.getString("전문일반구분"));

                        list.add(formDrug);
                    }
                }
                //2. 색상 & 모양
                else if(choosecolor != null && chooseshape != null && choosetype == null){
                    if ((jsonObject.getString("색상앞").contains(choosecolor)) && (jsonObject.getString("의약품제형").equals(chooseshape))) {
                        FormDrug formDrug = new FormDrug();
                        Log.e("2번 : ", jsonObject.getString("품목명") + jsonObject.getString("색상앞") + jsonObject.getString("의약품제형") + jsonObject.getString("제형코드명") + jsonObject.getString("표시앞") + jsonObject.getString("표시뒤"));

                        formDrug.setImage(jsonObject.getString("큰제품이미지"));
                        formDrug.setDrugName(jsonObject.getString("품목명"));
                        formDrug.setCompany(jsonObject.getString("업소명"));
                        formDrug.setClassName(jsonObject.getString("분류명"));
                        formDrug.setEtcOtcName(jsonObject.getString("전문일반구분"));
                        list.add(formDrug);
                    }
                } ...
 ~~~   
 <div>
<img src="https://user-images.githubusercontent.com/57400913/86566889-64362300-bfa5-11ea-887c-20be6eb94caf.png" width="40%">
<img src="https://user-images.githubusercontent.com/57400913/86567239-fb02df80-bfa5-11ea-8ea5-f20b52c8ff2b.png" width="40%">
</div>    

 ##### 식별 표시로 검색한 경우
 1)식별 표시 앞, 뒤 중 하나만 입력해도 올바른 검색 결과를 나오게 하기 위해서 3가지 경우로 나누었다.   
 2)해당하는 의약품의 정보를 보여주기 위한 json파싱 방법은 위와 동일하다.   
 ~~~java
 //json에서 조건에 맞는 것 검색(식별자) 3가지.
    public void marksearchJson(){
        try{
            InputStream is = getAssets().open("druglist.json"); //assests파일에 저장된 druglist_final.json 파일 열기
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("druglist"); //json파일에서 의약품리스트의 배열명, jsonArray로 저장

            list = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                //8. 표시앞만
                if(searchmarkfront != null && searchmarkback == null) { //식별자 앞이 입력됐을 경우
                    if (searchmarkfront.equals(jsonObject.getString("표시앞")))
                    {
                        FormDrug formDrug = new FormDrug();
                        Log.e("8번째 : ", jsonObject.getString("품목명") + jsonObject.getString("색상앞") + jsonObject.getString("의약품제형") + jsonObject.getString("제형코드명") + jsonObject.getString("표시앞") + jsonObject.getString("표시뒤"));
                        formDrug.setImage(jsonObject.getString("큰제품이미지"));
                        formDrug.setDrugName(jsonObject.getString("품목명"));
                        formDrug.setCompany(jsonObject.getString("업소명"));
                        formDrug.setClassName(jsonObject.getString("분류명"));
                        formDrug.setEtcOtcName(jsonObject.getString("전문일반구분"));
                        list.add(formDrug);
                    }

                } //9. 표시 앞 뒤 둘 다 입력
                else if(searchmarkfront != null){ //두개 다 입력
                    if (searchmarkfront.equals(jsonObject.getString("표시앞")) && searchmarkback.equals(jsonObject.getString("표시뒤")))
                    {
                        FormDrug formDrug = new FormDrug();
                        Log.e("9번째 : ", jsonObject.getString("품목명") + jsonObject.getString("색상앞") + jsonObject.getString("의약품제형") + jsonObject.getString("제형코드명") + jsonObject.getString("표시앞") + jsonObject.getString("표시뒤"));
                        formDrug.setImage(jsonObject.getString("큰제품이미지"));
                        formDrug.setDrugName(jsonObject.getString("품목명"));
                        formDrug.setCompany(jsonObject.getString("업소명"));
                        formDrug.setClassName(jsonObject.getString("분류명"));
                        formDrug.setEtcOtcName(jsonObject.getString("전문일반구분"));
                        list.add(formDrug);
                    }
                }//10. 표시뒤만
~~~
 3)list에 배열로 결과를 저장하고 FormMyAdapter.java 폴더를 생성한 후에 넘겨준다.   
 <div>
<img src="https://user-images.githubusercontent.com/57400913/86567351-33a2b900-bfa6-11ea-83b4-d91234e0b060.png" width="40%">
<img src="https://user-images.githubusercontent.com/57400913/86567379-3e5d4e00-bfa6-11ea-95b0-eda7e9152b70.png" width="40%">  
 </div>   
 <div>
<img src="https://user-images.githubusercontent.com/57400913/86567395-43220200-bfa6-11ea-8364-33dfff8e0fdf.png" width="40%">
<img src="https://user-images.githubusercontent.com/57400913/86567408-46b58900-bfa6-11ea-8548-b4da6b0c02c3.png" width="40%">  
</div>   
 ##### 검색 결과 Recyclerview로 띄어주기
 1)비트맵 방식으로 이미지를 띄워주었던 2-4-1 약 이름으로 검색 기능의 !!!!!!!!!!!!!!!!와 다르게 Glide로 이미지를 변환한다. 다른 부분만 다르고 동일하다.     
 2)출력된 리스트 중에 상세보기를 원하는 의약품을 클릭했을 시 보여지는 페이지는 2-4-3 약 상세보기 기능에서 설명한다.   
~~~java
public class FormMyAdapter extends RecyclerView.Adapter<FormMyAdapter.MyViewHolder>{
    private static final String sort = "form";

    private String drugString;
    private ArrayList<FormDrug> mList;
    private LayoutInflater mInflate;
    private Context mContext;
    private String data = null;
    private Intent intent;
    private String searchString;

    FormMyAdapter(Context context, ArrayList<FormDrug> mList) {//생성자를 context와 배열로 초기화해줌
        this.mList = mList;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.list_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        //최초 view에 대한 list item에 대한 view를 생성함.
        //이 onBindViewHolder친구한테 실질적으로 매칭해주는 역할을 함.
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Glide.with(holder.itemView)
                .load(mList.get(position).getImage())
                .into(holder.list_image);

        holder.tv_name.setText(mList.get(position).getDrugName());
        holder.tv_company.setText(mList.get(position).getCompany());
        holder.tv_className.setText(mList.get(position).getClassName());
        holder.tv_etcOtcName.setText(mList.get(position).getEtcOtcName());

        //해당하는 holder를 눌렀을 때 intent를 이용해서 상세정보 페이지로 넘겨줌
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() { //파싱을 이용했기 때문에 스레드가 필요하다. 오래 걸리기 때문에 background에서 처리해줘야함
                    @Override
                    public void run() {

                        // TODO Auto-generated method stub
                        //알고싶은 약의 상세정보를 누르면 그 약의 이름을 받아와 다시 파싱을 시작함
                        //그렇기 때문에 약의 이름을 drugString에 저장해준 후 그 이름을 getXmlData()의 메서드로 넘겨줌
                        drugString = mList.get(position).getDrugName();
                        data = getXmlData(drugString);//drugString에 해당하는 데이터를 string형식으로 가져와 data변수에 저장해줌

                        intent = new Intent(mContext, LookupActivity.class);//intent를 초기화해주는 코드


                        //앞에는 key값, 뒤에는 실제 값
                        intent.putExtra("Drug", drugString);//drug의 이름을 넘겨줌
                        intent.putExtra("data", data);//파싱한 데이터들을 "data"의 키로 넘겨줌
                        intent.putExtra("image", mList.get(position).getImage());
                        intent.putExtra("sort", sort);


                        //전체의 intent를 실제로 넘겨주는 코드.
                        mContext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                    }
                }).start();
            }

        });

    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView list_image;
        public TextView tv_name;
        public TextView tv_company;
        public TextView tv_etcOtcName;
        public TextView tv_className;
        public View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            list_image = itemView.findViewById(R.id.list_image);  // 이름 list_image??
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_company = itemView.findViewById(R.id.tv_company);
            tv_etcOtcName = itemView.findViewById(R.id.tv_etcOtcName);
            tv_className = itemView.findViewById(R.id.tv_className);
        }
    }
~~~
  
    

