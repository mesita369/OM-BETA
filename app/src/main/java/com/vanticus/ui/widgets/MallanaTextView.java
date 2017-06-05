package com.vanticus.ui.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Raghuram on 28-05-2017.
 */

public class MallanaTextView extends android.support.v7.widget.AppCompatTextView {
    public MallanaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    private void setFont(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/mallanna.ttf");
        setTypeface(font);
    }
}
