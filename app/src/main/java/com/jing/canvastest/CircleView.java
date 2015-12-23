package com.jing.canvastest;

import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

/**
 * Created by sks on 2015/12/22.
 */
public class CircleView extends View {

    private Paint paint;

    private Paint maskPaint;

    private int maxCricleSize;
    private static final int START_COLOR = 0xFFFF5722;
    private static final int END_COLOR = 0xFFFFC107;
    private float circleRadiusProgress = 0f;
    private float innerCircleRadiusProgress = 0f;

    private Bitmap tempBitmap;
    private Canvas tempCanvas;




    public float getInnerCircleRadiusProgress() {
        return innerCircleRadiusProgress;
    }

    public void setInnerCircleRadiusProgress(float innerCircleRadiusProgress) {
        this.innerCircleRadiusProgress = innerCircleRadiusProgress;
    }

    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public float getCircleRadiusProgress() {
        return circleRadiusProgress;
    }

    public void setCircleRadiusProgress(float circleRadiusProgress) {
        this.circleRadiusProgress = circleRadiusProgress;

        postInvalidate();
    }

    public static final Property<CircleView, Float> CIRCLE_RADIUS_PROGRESS = new Property<CircleView, Float>(Float.class, "circleRadiusProgress") {
        @Override
        public Float get(CircleView object) {
            return object.getCircleRadiusProgress();
        }

        @Override
        public void set(CircleView object, Float value) {
            object.setCircleRadiusProgress(value);
        }
    };


    public static final Property<CircleView,Float> INNER_CIRCLE_RADIUS_PROGRESS = new Property<CircleView, Float>(Float.class,"innerCircleRadiusProgress") {
        @Override
        public Float get(CircleView object) {
          return    object.getInnerCircleRadiusProgress();
        }

        @Override
        public void set(CircleView object, Float value) {

            object.setInnerCircleRadiusProgress(value);
        }
    };


    public CircleView(Context context) {
        super(context);
        init();
    }


    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));




    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxCricleSize = w / 2;
        tempBitmap = Bitmap.createBitmap(getWidth(),getWidth(),Bitmap.Config.ARGB_8888);
        tempCanvas = new Canvas(tempBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor((Integer) argbEvaluator.evaluate(circleRadiusProgress, START_COLOR, END_COLOR));
        tempCanvas.drawColor(0xffffff, PorterDuff.Mode.CLEAR);
        tempCanvas.drawCircle(getWidth()/ 2, getHeight() / 2, circleRadiusProgress * maxCricleSize, paint);
        tempCanvas.drawCircle(getWidth()/2,getHeight()/2,innerCircleRadiusProgress*maxCricleSize,maskPaint);

        canvas.drawBitmap(tempBitmap,0,0,null);



    }
}
