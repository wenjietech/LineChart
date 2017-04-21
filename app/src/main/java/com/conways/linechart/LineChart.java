package com.conways.linechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Conways on 2017/4/19.
 */

public class LineChart extends View {

    private int bgColor;
    private int bgLeftColor;
    private int bgRightColor;
    private int bgTopColor;
    private int bgBottomColor;

    private int xTextColor;
    private int yTextColor;
    private int scaleColor;
    private int xLineColor;
    private int yLineColor;

    private int innerCircleColor;
    private int outCircleColor;

    private int chartLineColor;

    private boolean showGrid;
    private int gridColor;
    private float scaleLenth;

    private int vCount;
    private int hCount;

    private int xMax;
    private int xMin;

    private float leftWith;
    private float rightWith;
    private float bottomWith;
    private float topWith;

    private float xTextSize;
    private float yTextSize;

    private float innerCircleRadius;
    private float outCircleRadius;


    private Paint bgPaint;
    private Paint bgLeftPaint;
    private Paint bgRightPaint;
    private Paint bgTopPaint;
    private Paint bgBottomPaint;

    private Paint xTextPaint;
    private Paint yTextPaint;
    private Paint scalePaint;
    private Paint xLinePaint;
    private Paint yLinePaint;
    private Paint gridPaint;

    private Paint innerCirclePaint;
    private Paint outCirclePaint;

    private Paint chartLinePaint;


    private int mWith;
    private int mHeight;

    private int offSet;

    private Rect xTextBounds;

    private List<ChartModel> list;
    static String TAG = "zzzzzzz";


    public LineChart(Context context) {
        super(context);
        initPaint();
        initData();
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        initData();
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        initData();
    }


    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LineChart);
        bgColor = ta.getColor(R.styleable.LineChart_bgColor, 0xffffffff);
        bgLeftColor = ta.getColor(R.styleable.LineChart_bgLeftColor, 0xffffffff);
        bgRightColor = ta.getColor(R.styleable.LineChart_bgRightColor, 0xfffffff);
        bgTopColor = ta.getColor(R.styleable.LineChart_bgTopColor, 0xffffffff);
        bgBottomColor = ta.getColor(R.styleable.LineChart_bgBottomColor, 0xffffffff);
        xTextColor = ta.getColor(R.styleable.LineChart_xTextColor, 0xff000000);
        yTextColor = ta.getColor(R.styleable.LineChart_yTextColor, 0xff000000);
        scaleColor = ta.getColor(R.styleable.LineChart_scaleColor, 0xff000000);
        xLineColor = ta.getColor(R.styleable.LineChart_xLineColor, 0xff000000);
        yLineColor = ta.getColor(R.styleable.LineChart_yTextColor, 0xff000000);
        showGrid = ta.getBoolean(R.styleable.LineChart_showGrid, false);
        gridColor = ta.getColor(R.styleable.LineChart_gridColor, 0xffff00ff);
        vCount = ta.getInt(R.styleable.LineChart_vCount, 8);
        hCount = ta.getInt(R.styleable.LineChart_hCount, 5);
        xMax = ta.getInt(R.styleable.LineChart_xMax, 10);
        xMin = ta.getInt(R.styleable.LineChart_xMin, 0);
        xTextSize = ta.getDimension(R.styleable.LineChart_xTextSize, 8f);
        yTextSize = ta.getDimension(R.styleable.LineChart_yTextSize, 8f);
        leftWith = ta.getDimension(R.styleable.LineChart_leftWith, 16f);
        rightWith = ta.getDimension(R.styleable.LineChart_rightWith, 8f);
        bottomWith = ta.getDimension(R.styleable.LineChart_bottomWith, 16f);
        topWith = ta.getDimension(R.styleable.LineChart_topWith, 8f);
        scaleLenth = ta.getDimension(R.styleable.LineChart_scaleLenth, 4f);
        innerCircleColor = ta.getColor(R.styleable.LineChart_innerCircleColor, 0xffffffff);
        outCircleColor = ta.getColor(R.styleable.LineChart_outCircleColor, 0xff000000);
        innerCircleRadius = ta.getDimension(R.styleable.LineChart_innerCircleRadius, 2f);
        outCircleRadius = ta.getDimension(R.styleable.LineChart_outCircleRadius, 3f);
        chartLineColor = ta.getColor(R.styleable.LineChart_chartLineColor, 0xff000000);
        ta.recycle();
        initPaint();

    }

    private void initPaint() {
        bgPaint = new Paint();
        bgPaint.setColor(bgColor);

        bgLeftPaint = new Paint();
        bgLeftPaint.setColor(bgLeftColor);

        bgRightPaint = new Paint();
        bgRightPaint.setColor(bgRightColor);

        bgTopPaint = new Paint();
        bgTopPaint.setColor(bgTopColor);

        bgBottomPaint = new Paint();
        bgBottomPaint.setColor(bgBottomColor);

        xTextPaint = new Paint();
        xTextPaint.setColor(xTextColor);
        xTextPaint.setTextSize(xTextSize);
        xTextPaint.setAntiAlias(true);

        yTextPaint = new Paint();
        yTextPaint.setColor(yTextColor);
        yTextPaint.setTextSize(yTextSize);
        yTextPaint.setAntiAlias(true);

        scalePaint = new Paint();
        scalePaint.setColor(scaleColor);
        scalePaint.setStrokeWidth(dip2px(1));

        xLinePaint = new Paint();
        xLinePaint.setColor(xLineColor);
        xLinePaint.setStrokeWidth(dip2px(1));

        yLinePaint = new Paint();
        yLinePaint.setColor(yLineColor);
        yLinePaint.setStrokeWidth(dip2px(1f));

        gridPaint = new Paint();
        gridPaint.setColor(gridColor);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(innerCircleColor);
        innerCirclePaint.setAntiAlias(true);

        outCirclePaint = new Paint();
        outCirclePaint.setColor(outCircleColor);
        outCirclePaint.setAntiAlias(true);

        chartLinePaint = new Paint();
        chartLinePaint.setColor(chartLineColor);
        chartLinePaint.setAntiAlias(true);

        xTextBounds = new Rect();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ChartModel chartModel = new ChartModel();
            chartModel.setValue((int) (Math.random() * xMax - xMin));
            Log.d(TAG, "initData: " + chartModel.getValue() + "  " + xMax);
            list.add(chartModel);
        }
    }

    public void setList(List<ChartModel> list) {
        if (null == this.list) {
            list = new ArrayList<>();
        }
        this.list.clear();
        this.list.addAll(list);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMeasureMode == MeasureSpec.EXACTLY) {
            mWith = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            mWith = 500;
        }
        if (heightMeasureMode == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            mHeight = 800;
        }
        setMeasuredDimension(mWith, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawContent(canvas);
        drawTop(canvas);
        drawBottom(canvas);
        drawRight(canvas);
        drawLeft(canvas);
    }


    private void drawBg(Canvas canvas) {
        canvas.drawRect(0, 0, mWith, mHeight, bgPaint);
    }

    private void drawTop(Canvas canvas) {
        canvas.drawRect(0, 0, mWith, topWith, bgTopPaint);
    }

    private void drawRight(Canvas canvas) {
        canvas.drawRect(mWith - rightWith, 0, mWith, mHeight, bgRightPaint);
    }

    private void drawBottom(Canvas canvas) {
        canvas.drawRect(0, mHeight - bottomWith, mWith, mHeight, bgBottomPaint);
        canvas.drawLine(leftWith, mHeight - bottomWith, mWith - rightWith, mHeight - bottomWith,
                yLinePaint);
    }


    private void drawLeft(Canvas canvas) {
        canvas.drawRect(0, 0, leftWith, mHeight, bgLeftPaint);
        canvas.drawLine(leftWith, topWith, leftWith, mHeight - bottomWith, xLinePaint);
        float unitLenth = (mHeight - topWith - bottomWith) / vCount;
        for (int i = 1; i <= vCount; i++) {
            canvas.drawLine(leftWith, mHeight - bottomWith - i * unitLenth, ((xMin + i * (xMax - xMin) / vCount)) % 5 == 0 ? (leftWith + 2 * scaleLenth) :
                    (leftWith + scaleLenth), mHeight - bottomWith - i * unitLenth, scalePaint);
            String temp = (xMin + i * (xMax - xMin) / vCount) + "";
            xTextPaint.getTextBounds(temp, 0, temp.length(), xTextBounds);
            canvas.drawText(temp, leftWith - xTextBounds.width() - dip2px(4), mHeight - (bottomWith
                    + i * unitLenth - xTextBounds.height() / 2), xTextPaint);
        }
    }

    private void drawContent(Canvas canvas) {
        float unitVLenth = (mHeight - topWith - bottomWith) / vCount;
        drawGrid(canvas, unitVLenth);
        float unitHLenth = (mWith - leftWith - rightWith) / hCount;
        for (int i = 0; i < list.size(); i++) {
            float x = offSet + (mWith - leftWith - rightWith) / 2 + leftWith - i * unitHLenth;
            float y = mHeight - bottomWith - list.get(i).getValue() * (mHeight - topWith - bottomWith) / (xMax - xMin);
            if (i < list.size() - 1) {
                float x1 = offSet + (mWith - leftWith - rightWith) / 2 + leftWith - (i + 1) * unitHLenth;
                float y1 = mHeight - bottomWith - list.get(i + 1).getValue() * (mHeight - topWith - bottomWith) / (xMax - xMin);
                canvas.drawLine(x, y, x1, y1, chartLinePaint);
            }
            canvas.drawCircle(x, y, outCircleRadius, outCirclePaint);
            canvas.drawCircle(x, y, innerCircleRadius, innerCirclePaint);
        }

        if (offSet > 0 || offSet < -(list.size() - 1) * unitHLenth) {
            return;
        }
        int position = (int) (Math.abs(offSet) / unitHLenth);


    }

    private void drawGrid(Canvas canvas, float unitLenth) {
        if (!showGrid) {
            return;
        }
        for (int i = 1; i <= vCount; i++) {
            canvas.drawLine(leftWith, mHeight - bottomWith - i * unitLenth, mWith - rightWith,
                    mHeight - bottomWith - i * unitLenth, gridPaint);
        }
    }

    private float xDown;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                offSet += (int) (event.getX() - xDown);
                xDown = 0f;
                if (offSet < 0) {
                    offSet = 0;
                }
                float unitHLenth = (mWith - leftWith - rightWith) / hCount;
                if (offSet>(list.size()-1)*unitHLenth){
                    offSet= (int) ((list.size()-1)*unitHLenth);
                }
                invalidate();
                break;
            default:
                break;

        }
        return true;
    }


    private int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
