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

  * MainActivity.xml 내 RecyclerView 생성
    ```
    ...
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

    <br>

  * item_list.xml 내 CardView > Text View 생성
    ```
    ...
    <android.support.v7.widget.CardView
     android:layout_width="match_parent"
     android:layout_height="50dp"
     android:layout_margin="0dp"
     android:padding="0dp"
     app:cardCornerRadius="15dp">

     <TextView
         android:id="@+id/textTitle"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:layout_margin="5dp"
         android:gravity="center_vertical"
         android:padding="5dp"
         android:text="TextView" />
     </android.support.v7.widget.CardView>
    </LinearLayout>
    ```

    <br>

  * 데이터 생성 (case by case)

    <br>

  * RecyclerView.Adapter<CustomAdapter.Holder> 상속 커스텀 어댑터 생성

    ```
    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
      // 1. 데이터 저장소
      private List<PicNote> data;

      public void setData(List<PicNote> data){
          this.data = data;
      }
      // 2. 개수
      @Override
      public int getItemCount() { // 목록의 전체 길이를 결정
          return data.size();
      }
      // 3. 홀더 생성
      @Override
      public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
          // 1. 만들어둔 layout 파일을 inflate 한다
          View view = LayoutInflater.from(parent.getContext())
                  .inflate(R.layout.item_list, parent, false);
          // 2. inflate 된 View 를 Holder의 생성자에 담는다
          Holder holder = new Holder(view);
          // 3. 생성된 Holder를 리턴한다.
          return holder;
      }
      // 4. 홀더 사용
      @Override
      public void onBindViewHolder(Holder holder, int position) {
          // 1. 데이터저장소에 객체단위로 꺼내둔다
          PicNote picNote = data.get(position);
          // 2. 홀더에 있는 위젯에 값을 입력한다.
          holder.setTitle(picNote.getTitle());
          holder.setFilename(picNote.getBitmap());
      }

      // 0. 홀더 만들기
      public class Holder extends RecyclerView.ViewHolder{
          private String filename;
          private TextView textTitle;
          public Holder(View itemView) {
              super(itemView);
              textTitle = itemView.findViewById(R.id.textTitle);
              itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent intent = new Intent(view.getContext(), DetailActivity.class);
                      intent.putExtra("title", textTitle.getText());
                      intent.putExtra("filename", filename);
                      view.getContext().startActivity(intent);
                  }
              });
          }

          public void setTitle(String title){
              textTitle.setText(title);
          }
          public void setFilename(String filename){
              this.filename = filename;
          }
        }
    }
    ```

  <br>

  * MainActivity.java 내에서 데이터 관련 정의 및 어댑터 설정
    ```
    ...
    private void init(){
      PicNoteDAO dao = new PicNoteDAO(this);
      //* 1. 데이터를 정의
      // DB에 테스트 데이터 넣기
          /*
          for(int i=0; i<1000 ; i++){
              PicNote picNote = new PicNote();
              picNote.setTitle("안녕하세요 "+i);
              picNote.setDatetime(System.currentTimeMillis());
              // db 에다가 넣은후
              dao.create(picNote);
          }
          */
      // db에서 읽어온다.
      List<PicNote> data = dao.readAll();

      //* 3. 재정의한 아답터를 생성하면서 데이터를 담는다
      CustomAdapter adapter = new CustomAdapter();
      adapter.setData(data);
      //* 4. 아답터와 RecyclerView 컨테이너를 연결
      RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
      recyclerView.setAdapter(adapter);
      //* 5. RecyclerView 에 레이아웃매니저를 설정
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    ```

    <br>

  * 참고 : 레이아웃 매니저의 종류
    1. LinearLayoutManager
        - 리사이클러 뷰에서 가장 많이 쓰이는 레이아웃으로 수평, 수직 스크롤을 제공하는 리스트를 만들 수 있다.
    2. StaggeredGridLayoutManager
        - 이 레이아웃을 통해 뷰마다 크기가 다른 레이아웃을 만들 수 있다. 마치 Pinterest 같은 레이아웃 구성가능.
    3. GridLayoutManager
        - 갤러리(GridView) 같은 격자형 리스트를 만들 수 있습니다.
        - 사용예시 // StaggeredGrid 레이아웃을 사용한다.<br>
            RecyclerView.LayoutManager lm
                = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            lm = new LinearLayoutManager(this);
            lm = new GridLayoutManager(this,3);
