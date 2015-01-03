package com.apptut.androidkit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author LiangQi
 * @since 2015.01.03 23:55
 */
public class NiceTextView extends TextView {
    public NiceTextView(Context context) {
        super(context);
    }

    public NiceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NiceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        
    }
}
