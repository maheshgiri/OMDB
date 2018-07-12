package com.android.omdbapp.omdb.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.android.omdbapp.omdb.utils.FontCache;

public class CustomEditTextView extends android.support.v7.widget.AppCompatEditText {
    public CustomEditTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.setTypeface(FontCache.getTypeface(context,"fonts/Montserrat-Regular.ttf"));
    }
}
