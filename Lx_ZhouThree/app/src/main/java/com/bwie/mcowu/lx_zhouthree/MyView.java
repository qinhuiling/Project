package com.bwie.mcowu.lx_zhouthree;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 08:25.
 * 本类作用:
 */

public class MyView extends View {
    //创建画笔
    private Paint paint;

    //从xml中获取的颜色
    private int circleBoundColor;
    private int circleBoundColor2;
    private float circleRadius;

    //当前画笔画圆的颜色
    private int CurrenCircleBoundColor;
    private int CurrenCircleBoundColor2;
    private float CurrenCircleRadius;
    public MyView(Context context) {
        super(context);
        initVeiw(context);

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVeiw(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            //就是我们自定义的属性的资源id
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyView_color:
                    circleBoundColor = typedArray.getColor(attr, Color.BLACK);
                    CurrenCircleBoundColor = circleBoundColor;
                    break;
                    //改变后的颜色
                case R.styleable.MyView_color_fover:
                    circleBoundColor=typedArray.getColor(attr, 3);
                    CurrenCircleBoundColor2 =circleBoundColor;
                    break;
                case R.styleable.MyView_rudius:
                    circleRadius=typedArray.getDimension(attr,30);
                    CurrenCircleRadius=circleRadius;
                    break;
            }
        }
    }
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //chushihua
    private void initVeiw(Context context) {
        paint = new Paint();
    }
    public void setColor(int color) {
        if (CurrenCircleBoundColor != color) {
            CurrenCircleBoundColor = color;
        } else {
            CurrenCircleBoundColor = circleBoundColor;
        }
    }
    //设置圆心位置
    private int pivotX;
    private int pivotY;

    private float currentDegree = 0;//旋转的度数
    private float radius=130;//半径

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(CurrenCircleBoundColor);//给画笔设置颜色
        paint.setStrokeWidth(circleRadius);//获取圆的宽度
        paint.setStyle(Paint.Style.STROKE);
        pivotX = getWidth()/2;//获取屏幕的宽
        pivotY = getHeight()/2;//获取屏幕的高
        canvas.drawCircle(pivotX,pivotY,radius,paint);//画圆
        //创建
        canvas.save();
        //旋转画布 , 如果旋转的的度数大的话,视觉上看着是旋转快的
        canvas.rotate(currentDegree, pivotX, pivotY);

        //提供了一些api可以用来画线(画路径)
        Path path = new Path();

        //从哪开始画 从A开始画
        path.moveTo(pivotX + radius, pivotY);

        //从A点画一个直线到D点
        path.lineTo(pivotX + radius - 20, pivotY - 20);

        //从D点画一个直线到B点
        path.lineTo(pivotX + radius, pivotY + 20);

        //从B点画一个直线到C点
        path.lineTo(pivotX + radius + 20, pivotY - 20);

        //闭合  --  从C点画一个直线到A点
        path.close();
        paint.setStyle(Paint.Style.FILL);//图的样式
        paint.setColor(Color.BLACK);//图的背景色
        canvas.drawPath(path, paint);//使用画笔画圆
        canvas.restore();

        //旋转的度数一个一个度数增加,  如果乘以一个速度的话,按一个速度速度增加
        currentDegree += 1 * currentSpeed;

        if (!isPause) {
            invalidate();//重置
        }
    }
    private int currentSpeed = 1;
    private boolean isPause = false;
    public void speed() {
        ++currentSpeed;
        if (currentSpeed >= 10) {
            currentSpeed = 10;
            Toast.makeText(getContext(), "我比闪电还快", Toast.LENGTH_SHORT).show();
        }
    }

    public void slowDown() {
        --currentSpeed;
        if (currentSpeed <=1) {
            currentSpeed = 1;
        }
    }
    public void pauseOrStart() {

        //如果是开始状态的话去重新绘制
        if (isPause) {
            isPause = !isPause;
            invalidate();
        } else {
            isPause = !isPause;
        }
    }
    }

