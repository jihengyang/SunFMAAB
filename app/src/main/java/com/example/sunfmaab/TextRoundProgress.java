package com.example.sunfmaab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * 配文字环形进度条，来自github
 */
public class TextRoundProgress extends View {
    private final Paint paint; // 画笔对象的引用
    private final int roundColor; // 圆环的颜色
    private final float roundWidth; // 圆环的宽度
    private final int progressColor; // 圆环进度的颜色
    private final float progressWidth; // 圆环进度的宽度
    private final int textColor; // 中间进度百分比的字符串的颜色
    private final float textSize; // 中间进度百分比的字符串的字体大小
    private final float numSize; // 中间进度文本大小
    private int max; // 最大进度
    private final int startAngle; // 进度条起始角度
    private final boolean textShow; // 是否显示中间的进度
    private int progress; // 当前进度

    public TextRoundProgress(Context context) {
        this(context, null);
    }

    public TextRoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextRoundProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint();

        // 读取自定义属性的值
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.TextRoundProgress);

        // 获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.TextRoundProgress_trp_roundColor, Color.RED);
        roundWidth = mTypedArray.getDimension(R.styleable.TextRoundProgress_trp_roundWidth, 5);
        progressColor = mTypedArray.getColor(R.styleable.TextRoundProgress_trp_progressColor, Color.GREEN);
        progressWidth = mTypedArray.getDimension(R.styleable.TextRoundProgress_trp_progressWidth, roundWidth);
        textColor = mTypedArray.getColor(R.styleable.TextRoundProgress_trp_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.TextRoundProgress_trp_textSize, 11);
        numSize = mTypedArray.getDimension(R.styleable.TextRoundProgress_trp_numSize, 14);
        max = mTypedArray.getInteger(R.styleable.TextRoundProgress_trp_max, 100);
        startAngle = mTypedArray.getInt(R.styleable.TextRoundProgress_trp_startAngle, 90);
        textShow = mTypedArray.getBoolean(R.styleable.TextRoundProgress_trp_textShow, true);
        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2; // 获取圆心的x坐标
        int radius = (int) (center - roundWidth / 2); // 圆环的半径

        // step1 画最外层的大圆环
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setColor(roundColor); // 设置圆环的颜色
        paint.setAntiAlias(true); // 消除锯齿
        // 设置画笔样式
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawCircle(center, center, radius, paint); // 画出圆环

        // step2 画圆弧-画圆环的进度
        paint.setStrokeWidth(progressWidth); // 设置画笔的宽度使用进度条的宽度
        paint.setColor(progressColor); // 设置进度的颜色
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的界限

        int sweepAngle = 360 * progress / max; // 计算进度值在圆环所占的角度
        // 根据进度画圆弧
        canvas.drawArc(oval, startAngle, sweepAngle, false, paint);

        // step3 画文字指示
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.FILL);
        // 计算百分比
        int percent = (int) (((float) progress / (float) max) * 100);

        if (textShow && percent >= 0) {
            // 3.2 画百分比
            paint.setTextSize(numSize);
            float numWidth = paint.measureText(percent + "%"); // 测量字体宽度，我们需要根据字体的宽度设置在圆环中间
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float distance= (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            canvas.drawText(percent + "%", center - numWidth / 2, center + distance, paint); // 画出进度百分比
        }
    }

    /**
     * 设置进度的最大值
     * <p>根据需要，最大值一般设置为100，也可以设置为1000、10000等</p>
     *
     * @param max int最大值
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度
     *
     * @return int 当前进度值
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件
     *
     * @param progress 进度值
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        this.progress = progress;
        // 刷新界面调用postInvalidate()能在非UI线程刷新
        postInvalidate();
    }
}
