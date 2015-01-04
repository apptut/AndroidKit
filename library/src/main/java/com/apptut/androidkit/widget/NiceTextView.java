package com.apptut.androidkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.apptut.androidkit.R;
import com.apptut.androidkit.util.DebugUtil;

/**
 * @author LiangQi
 * @since 2015.01.03 23:55
 */
public class NiceTextView extends TextView {
    private int DEFAULT_COLOR = Color.BLACK;
    private int DEFAULT_HEIGHT = 0;
    private int DEFAULT_PADDING = 0;
    private int color = DEFAULT_COLOR; // 默认颜色
    private int height = DEFAULT_HEIGHT; // 默认宽度
    private int padding = DEFAULT_PADDING;
    private Mode defaultMode = Mode.Underline;
    private Paint paint;

    private enum Mode {
        Underline(0),
        LineThrough(1),
        Top(2);
        private  int index;

        Mode(int index) {
            this.index = index;
        }

        public static Mode getMode(int index) {
            for (Mode m : values()) {
                if (m.index == index) return m;
            }
            return null;
        }

        public int getIndex() {
            return index;
        }
    }

    public NiceTextView(Context context) {
        super(context);
        setup(context, null);
    }

    public NiceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public NiceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NiceTextView);
            height = a.getDimensionPixelSize(R.styleable.NiceTextView_ak_height, DEFAULT_HEIGHT);
            color = a.getColor(R.styleable.NiceTextView_ak_color, DEFAULT_COLOR);
            padding = a.getDimensionPixelSize(R.styleable.NiceTextView_ak_padding, DEFAULT_PADDING);
            defaultMode = Mode.getMode(a.getInt(R.styleable.NiceTextView_ak_mode,Mode.Underline.getIndex()));
            a.recycle();

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setDither(true);
            paint.setAntiAlias(true);
            paint.setColor(color);
            paint.setStrokeWidth(height);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (height > 0) {
            int textWidth = getMeasuredWidth();
            int textHeight = getMeasuredHeight();
            int top = 0;
            switch (defaultMode){
                case Top:
                    canvas.drawLine(0, -padding, textWidth, -padding, paint);
                    break;
                case LineThrough:
                    top = (textHeight - height)/2 + padding;
                    canvas.drawLine(0, top, textWidth, top, paint);
                    break;
                case Underline:
                    top = textHeight + 2;
                    canvas.drawLine(0,top,textWidth,top,paint);
                    break;
            }
        }

    }
}
