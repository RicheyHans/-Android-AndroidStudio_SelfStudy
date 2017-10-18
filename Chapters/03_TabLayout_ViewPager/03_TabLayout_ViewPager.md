# [Android] Android Studio SelfStudy

## 03. TabLayout / ViewPager

* ViewPager 기본 사용
  * ViewPager에 사용할 아이템 목록 xml 생성
    _item_viewpager.xml_
    ```
    <?xml version="1.0" encoding="utf-8"?>

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/textView"
        android:layout_width="127dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="TextView"
        android:textAlignment="center"
        android:textAppearance="@style/TextAppearance.AppCompat.Body1"
        android:textSize="18sp" />
    </LinearLayout>
    ```

    <br>

  * MainActivity xml에 ViewPager 위젯을 생성한다(xml에디터 활용)
    _activity_main.xml_
    ```
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.mcbud.viewpager_170928.MainActivity">

    <android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="368dp"
        android:layout_height="495dp"
        tools:layout_editor_absoluteX="8dp"
        tools:layout_editor_absoluteY="8dp" />

    </android.support.constraint.ConstraintLayout>
    ```

    <br>

  * MainActivity.java 내 ViewPager 객체 생성 / Data 생성
    ```
    public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        // 가상 데이터 작성(데이터 로드)
        List<String> data = new ArrayList<>();
        for(int i = 0; i<100; i++){
            data.add("Temp Data="+i);
        }
    ...
    ```

    <br>

  * PagerAdapter를 상속하는 CumstomAdapter 생성
    ```
    class CustomAdapter extends PagerAdapter{

      Context context;
      List<String> data;

      public CustomAdapter(Context context, List<String> data){
          this.context = context;
          this.data = data;
      }

      // 관계 정리를 위해 삽입하는 관계
      public void setView(ViewPager viewPager){
          viewPager.setAdapter(this);
      }

      // 전체 개수
      @Override
      public int getCount() {
          return data.size();
      }

      // getView와 동일한 역할(xml을 view로 만들고 data 삽입 후 리턴해준다)
      @Override
      public Object instantiateItem(ViewGroup container, int position) {
          // 1.Layout 파일을 inflate해서 view로 만든다.
          View view = LayoutInflater.from(context)
                  .inflate(R.layout.item_viewpager, null);

          // 2.data를 화면에 세팅
          String value = data.get(position);
          TextView textView = view.findViewById(R.id.textView);
          textView.setText(value);

          // 3.View Group에 만들어진 view를 add한다.
          container.addView(view);

          // 4.내가 만든 view를 return 한다.
          return view;
      }

      // InstantiateItem에서 return 받은 object가 View인지 검증
      @Override
      public boolean isViewFromObject(View view, Object object) {
          return view == object;
      }

      // 현재 사용하지 않는 View는 제거(container : 뷰 페이저, object : 뷰 페이저 안에 있는 item)
      @Override
      public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView( (View)object );
          // super.destroyItem(container, position, object);
      }

    }
    ```

    <br>

  * MainActivity.java 내에서 어댑터 생성 및 연결
    ```
    ...
    // adapter를 생성(View Pager는 생성과 함께 데이터를 넘겨야 한다)
        CustomAdapter adapter  = new CustomAdapter(this, data);
        adapter.setView(viewPager); // viewPager.setAdapter(this)

    // adapter 연결
        viewPager.setAdapter(adapter);
    ...
    ```
