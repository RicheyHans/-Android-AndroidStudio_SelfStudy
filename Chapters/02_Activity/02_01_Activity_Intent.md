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

  <br>

  * context, cls 인수로 받는 생성자
  ```
  Intent intent = new Intent(this, SubActivity.class);
  startActivity(intent);
  ```
  > 주로 서브 액티비티 호출에 활용하며, 호출대상이 분명한 **명시적 인텐트(Explicit Intent)**<br>
  > context는 호출자의 정보(주로 this), cls는 호출되는 액티비티의 클래스

<br>

* Intent 보유 가능 정보



  1. Action
      - **실행할 동작** 이며, 어떤 작업을 수행할 것인지 지정
      - ACTION_CALL, ACTION_EDIT, ACTION_VIEW, ACTION_HEADSET_PLUG...
      - getAction, setAction 메서드 사용

  <br>

  2. DATA
      - Action에 필요한 **상세 데이터** 를 제공(작업거리)
      - 액션의 목적 대상 종류는 광범위하므로, 임의의 대상을 특정지을 수 있는 **URI** 타입
        (url, 파일 경로, 주소록 레코드 등 지정 가능)
      - getData, setData 사용

  <br>

  3. Type
      - 대상의 타입. 주로 자동 판정(http://, tel:~)
      - 필요시 getType, setType 사용해 MIME타입 지정

  <br>

  4. Category

  <br>

  5. Component
      - 인텐트를 처리할 컴포넌트 명시적 지정
      - 이 속성이 지정되면 명시적 인텐트이므로 다른 정보는 참조하지 않는다.

  <br>

  6. **Extras**
      - **key와 value값이 쌍으로 저장되어 전달되며, 리턴도 가능하다.**
      - putExtra 메서드 오버로딩 / getIntExtra, getStringExtra 등 사용

  <br>

  7. Flags

<br>

* Activity사이의 통신
  * Intent : Activity간의 **인수와 리턴 값을 전달하는 도구**
  * **Extras** : Intent 내부의 정보 저장 공간(Bundle 타입)

  <br>

  * 저장 - putExtra()
    ```
    Intent putExtra(String name, int value)
    Intent putExtra(String name, String value)
    Intent putExtra(String name, boolean value)
    ...
    ```
    > name은 자유롭게 선언하되 중복 불가

    <br>

  * 읽어오기 - getxxxExtra()
    ```
    int getIntExtra(String name, int defaultValue)
    String getStringExtra(String name)
    boolean getBooleanExtra(String name, boolean defaultValue)
    ```
    > 값이 전달되지 않았을 경우 디폴트값(string의 경우 null) 리턴

    <br>

  * 액티비티 호출 ~ 리턴 - startActivityForResult
    ```
    startActivityForResult(Intent intent, int requestCode)
    ```
    > intent는 호출 대상이 보유 <br>
    > requestCode는 호출한 대상을 식별하기 위한 변수로, 리턴 값을 받을 대상을 구분

    
