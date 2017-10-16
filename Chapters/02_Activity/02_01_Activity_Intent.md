# [Android] Android Studio SelfStudy

## 02.1. Activity_Intent
* Activity
  - 자체 출력 기능 없음 -> 생성(onCreate)시 호출되는 setContentView가 가시적인 View 배치

* Intent
  - Activity 호출 목적 : 작업을 요청하기 위함
  - Intent는 Activity를 포함한 안드로이드 Component 사이의 통신을 위한 **메시지 시스템**

  <br>

  - Activity 호출 메서드
  ```
  void startActivity(Intent intent)
  ```
  > 호출을 위해, 호출 대상과 작업 내용이 담긴 Intent 객체를 생성해야 함<br>
  > 주로 ACTION, URI, 클래스를 취하는 생성자 활용
  ```
  ...
  Intent(Context packageContext, Class<?> cls)
  Intent(String action, Uri uri, Context packageContext, Class<?> cls)
  ...
  ```

  * context, cls 인수로 받는 생성자
  ```
  Intent intent = new Intent(this, SubActivity.class);
  startActivity(intent);
  ```
  > 주로 서브 액티비티 호출에 활용하며, 호출대상이 분명한 **명시적 인텐트(Explicit Intent)**<br>
  > context는 호출자의 정보(주로 this), cls는 호출되는 액티비티의 클래스
