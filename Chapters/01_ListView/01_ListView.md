# [Android] Android Studio SelfStudy

## 01. ListView

* AdapterView
 : 항목의 집합을 표시하는 위젯의 집합(ViewGroup 하위)
 : 550p. 클래스 계층도
<br>
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
