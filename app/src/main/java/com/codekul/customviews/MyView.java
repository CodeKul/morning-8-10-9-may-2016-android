package com.codekul.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by aniruddha on 18/5/16.
 */
public class MyView extends TextView {

    private Paint paint;
    private float cx, cy, radius;

    public MyView(Context context) {
        super(context);

        initPaint();
        // used when view is created via code
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initPaint();
        // used when view is created view xml
    }

    private void initPaint(){
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx,cy,radius,paint);
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void changeCircle(float cx, float cy, float rad) {
        setCx(cx);setCy(cy);setRadius(rad);
        invalidate();
    }
}
