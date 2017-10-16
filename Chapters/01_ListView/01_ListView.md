# [Android] Android Studio SelfStudy

## 01. ListView

* AdapterView
 : 항목의 집합을 표시하는 위젯의 집합(ViewGroup 하위)
 : 550p. 클래스 계층도

* Adapter
 : AdapterVIew들은 표시 항목 데이터를 직접 관리하지 않고 'Adapter'를 통해서 관리
  (데이터의 종류 / 개수의 다양성 감안 + 관리의 효용성)
 : 551p. 클래스 계층도
- - -
* 구현 절차
1. MainActivity의 xml 상위 레이아웃에 **'ListView' 생성**
```
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.mcbud.basiclist_170919.MainActivity">

    <ListView
        android:id="@+id/listView"
        android:layout_width="368dp"
        android:layout_height="495dp"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="8dp"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintVertical_bias="0.0" />
</android.support.constraint.ConstraintLayout>
```

2. MainActivity.java 내에서 **데이터 원본** 정의
```
public class MainActivity extends AppCompatActivity {

    // 데이터, 리스트뷰 선언
    List<String> data = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      // 데이터 정의(100개 더미 값)
      for(int i = 0; i < 100; i++){
          data.add("임시값"+i);
      }
}
```
3. MainActivity.java 내에서 **데이터 원본과 ListView를 연결해 줄 Adapter를 생성**
```
  for(int i = 0; i < 100; i++){
    data.add("임시값"+i);
  }
  // 데이터와 리스트뷰를 연결하는 아답터 생성
  CustomAdapter adapter = new CustomAdapter(this, data);
  // 아답터와 리스트뷰를 연결
  listView = (ListView)findViewById(R.id.listView);
  listView.setAdapter(adapter);
```

* 기본 아답터 클래스 상속받아 CustomAdapter 구현
```
class CustomAdapter extends BaseAdapter{
  // 데이터 저장소를 아답터 내부에 두는 것이 컨트롤에 용이함
      List<String> data;
      Context context;
  // 아답터 생성 시 context, data 생성
  public CustomAdapter(Context context, List<String> data){
      this.context = context;
      this.data = data;
  }

  // 자동으로 호출되는 메서드 3개
  // 현재 데이터의 총 개수(ListView 가 어댑터에게 항목의 개수를 물어보는 메서드)
  @Override
  public int getCount() {
      return data.size();
  }

  // 현재 뿌려줄 데이터를 리턴
  @Override
  public Object getItem(int position) {   // 호출되는 목록 아이템의 데이터 순서
      return data.get(position);
  }

  // 뷰의 아이디를 리턴
  @Override
  public long getItemId(int position) {
      return position;
  }

  // getView 메서드는 '항목 하나'를 출력하기 위한 View 를 생성해 리턴한다. 하나의 항목을 출력할 때 마다 getView 를 호출해 리턴된 View 를 리스트 뷰의 해당 위치에 배치

  @Override
  public View getView(int position, View view, ViewGroup viewGroup) {
    // 파라미터 position : 생성할 항목의 순서
    // 파라미터 view : convertView이며, 이전에 생성된 차일드 뷰를 의미. 최초 호출 시 null 이며 이 경우 커스텀 뷰를 새로 생성한다.
    // 파라미터 viewGroup : parent 이며, 생성되는 뷰가 담길 레이아웃(부모)를 의미. 여기서는 ListView.
    Holder holder = null;
    if(view == null) {
      // 인플레이터로 list_item을 View 객체로 전환한다.
      view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
      // 일일이 findViewById 사용하는 낭비를 막기 위해 Holder를 별도로 생성한다.
      holder = new Holder(view);
      view.setTag(holder);
    } else {
        holder = (Holder)view.getTag();
    }
    holder.textview.setText(data.get(position));
    return view;
}
```

* Holder 클래스의 생성
```
TextView textview;

public Holder(View view){
    // Holder에 담긴 view는 인플레이터로 캐스팅 된 list_item 뷰 객체
    textview = (TextView)view.findViewById(R.id.textView);
    setClickListener();
}

public void setClickListener(){
    textview.setOnClickListener(new View.OnClickListener() {
        // 화면에 보여지는 View는 기본적으로 자신이 속한 컴포넌트의 context를 그대로 가지고 있다.
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("valueKey", textview.getText());
            view.getContext().startActivity(intent);
        }
    });
}
```

4. DetailActivity 를 생성해, ListView 클릭 시 전달 받은 내용을 출력한다.
```
public class DetailActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    // intent를 통해 넘어온 값 추출
    Intent intent = getIntent();    // startActivity를 통해 넘어온 intent를 꺼낸다.
    /*
    // 인텐트에서 키 밸류의 번들을 추출하고
    // 그 번들에서 목적 값을 추출한다.
    Bundle bundle = intent.getExtras();
    String result = bundle.getString("valueKey");
    */

    // intent에서 번들을 따로 추출하지 않고 값을 추출하는 방법
    String result = intent.getStringExtra("valueKey");

    TextView textView = (TextView)findViewById(R.id.textView);
    textView.setText(result);

    // putExtra를 하면 키와 밸류가 함께 들어간다. 이 경우, entry와 비슷한 개념인 buundle로
    // 키와 밸류가 들어간다. 그래서 번들을 먼저 꺼내는 형태가 된다.
  }
}
```
