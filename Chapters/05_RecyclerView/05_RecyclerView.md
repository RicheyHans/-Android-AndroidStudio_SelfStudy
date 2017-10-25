# [Android] Android Studio SelfStudy

## 05. RecyclerView

* RecyclerView 기본 사용

  1. MainActivity RecyclerView 생성
  2. item_list.xml 내 CardView에서 개별 아이템 생성
  3. 데이터 정의
  4. 아답터 재정의 ~ 아답터 생성 후 데이터 보관
  5. 아답터와 RecyclerView 연결
  6. RecyclerView에 레이아웃 매니저 설정

  <br>

  * MainActivity.java 내 RecyclerView 생성
    ```
    <android.support.v7.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="0dp"
    android:layout_height="453dp"
    android:layout_marginLeft="8dp"
    android:layout_marginRight="8dp"
    android:layout_marginTop="8dp"
    app:layout_constraintHorizontal_bias="0.0"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    tools:listitem="@layout/item_list"
    android:layout_marginStart="8dp"
    android:layout_marginEnd="8dp" />
    ```

  *
