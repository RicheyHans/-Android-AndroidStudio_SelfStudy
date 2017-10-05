package com.example.mcbud.chapter0502_00;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static android.R.attr.action;

/**
 * Created by mcbud on 2017-10-05.
 */

public class BitmapButton  extends AppCompatButton{

    // 아이콘 리소스 정의(idle/clicked, drawble폴더 내 이미지 리소스)
    int iconNormal = R.drawable.bitmap_button_normal;
    int iconClicked = R.drawable.bitmap_button_clicked;

    // 아이콘 상태 상수 정의
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_CLICKED = 1;

    // 아이콘 상태 초기화
    int iconStatus = STATUS_NORMAL;

    // 소스에서 해당 버튼 객체 생성시 호출되는 생성자
    public BitmapButton(Context context) {
        super(context);

        init();
    }

    // XML에 추가된 버튼이 인플레이션 될 때 호출되는 생성자
    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    // 버튼 객체 초기화(생성자 실행)
    public void init(){
        setBackgroundResource(iconNormal);

        int defaultTextColor = Color.WHITE;
        float defaultTextSize = getResources().getDimension(R.dimen.text_size);
        Typeface defaultTypeface = Typeface.DEFAULT_BOLD;

        setTextColor(defaultTextColor);
        setTextSize(defaultTextSize);
        setTypeface(defaultTypeface);
    }

    public void setIcon(int iconNormal, int iconClicked){
        this.iconNormal = iconNormal;
        this.iconClicked = iconClicked;
    }

    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);

        switch(action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(this.iconClicked);

                iconStatus = STATUS_CLICKED;

                break;

            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundResource(this.iconNormal);

                iconStatus = STATUS_NORMAL;

                break;
        }

        // 다시 그리기
        invalidate();

        return true;
    }

}
