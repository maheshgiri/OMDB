package com.android.omdbapp.omdb.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.android.omdbapp.omdb.utils.FontCache;

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.setTypeface(FontCache.getTypeface(context,"fonts/Montserrat-Regular.ttf"));
    }
}
