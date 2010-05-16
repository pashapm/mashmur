package ru.hackday.mashmur;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private boolean mAnimate;
    private long mNextTime;
    public static float[] mValues;
    private int shiftAngle = 0;

    public CompassView(Context context) {
        super(context);
        // Construct a wedge-shaped path
    }

    public void setShiftAngle(int shiftAngle) {
        this.shiftAngle = shiftAngle;
    }

    public void init() {        
        mPath.moveTo(0, -20);
        mPath.lineTo(-10, 40);
        mPath.lineTo(0, 20);
        mPath.lineTo(10,40);
        mPath.close();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = mPaint;

        canvas.drawColor(Color.WHITE);

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

//        int w = canvas.getWidth();
//        int h = canvas.getHeight();
        canvas.translate(40, 40);
        if (mValues != null) {
            canvas.rotate(-mValues[0] + shiftAngle);
        }
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        mAnimate = true;
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mAnimate = false;
        super.onDetachedFromWindow();
    }
}
