package com.bwie.asus.deblocking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ASUS on 2017/9/29.
 */

public class MyView extends View{

    private Bitmap jiesuo_bg;
    private Bitmap jiesuo_button;
    private int width;
    private int height;
    private int width1;
    private int measuredWidth;
    private int measuredHeight;
    private float x;
    private float y;
    private int left;
    private int right;
    private float eventX;
    private float eventY;
    private boolean block;
    private OnUnlock onunlock;

    public MyView(Context context) {
        super(context);

        init();

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();

    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        jiesuo_bg = BitmapFactory.decodeResource(getResources(), R.mipmap.jiesuo_bg);
        jiesuo_button = BitmapFactory.decodeResource(getResources(), R.mipmap.jiesuo_button);

        width = jiesuo_bg.getWidth();
        height = jiesuo_bg.getHeight();
        width1 = jiesuo_button.getWidth();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();

        x = measuredWidth / 2 - width / 2;
        y = measuredHeight / 2 - height / 2;

        left = measuredWidth / 2 - width / 2;
        right = measuredWidth / 2 + width / 2 - width1;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(jiesuo_bg,measuredWidth / 2 - width / 2,measuredHeight / 2 - height / 2,null);

        if (x < left){
            x = left;
        }else  if (x > right){
            x = right;
        }

        canvas.drawBitmap(jiesuo_button,x,y,null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                eventX = event.getX();
                eventY = event.getY();
                block = isBlock(eventX, eventY);
                if (block){
                    Toast.makeText(getContext(),"按下了",Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (block){
                    float moveX = event.getX();
                    x = moveX - width1 / 2;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                block = false;
                if (x < right - 5){
                    x = left;
                }else{
                    if (onunlock != null){
                        Toast.makeText(getContext(),"松开了",Toast.LENGTH_SHORT).show();
                        onunlock.setUnlock(true);
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;

    }

    private boolean isBlock(float eventX,float eventY){
        float rx = x + width1 / 2;
        float ry = y + width1 / 2;

        double sqrt = Math.sqrt((eventX - rx) * (eventX - rx) + (eventY - ry) * (eventY - ry));
        if (sqrt < width1 / 2){
            return true;
        }
        return false;
    }

    public void setOnUnlock(OnUnlock onunlock){
        this.onunlock = onunlock;
    }

}
