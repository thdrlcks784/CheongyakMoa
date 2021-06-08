package com.cheongyakmoa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {
    private int mAvailableWidth = 0;
    private Paint mPaint;
    private List<String> mCutStr = new ArrayList<String>();

    public CustomTextView(Context context){
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    private int setTextInfo(String text, int textWidth, int textHight){
        mPaint = getPaint();
        mPaint.setColor((getTextColors().getDefaultColor()));
        mPaint.setTextSize(getTextSize());

        int mTextHeight = textHight;

        if(textWidth>0){
            mAvailableWidth = textWidth - this.getPaddingLeft()-this.getPaddingRight();

            mCutStr.clear();
            int end =0;

            do{
                end = mPaint.breakText(text,true,mAvailableWidth,null);
                if(end>0){
                    mCutStr.add(text.substring(0,end));
                    text = text.substring(end);
                    if(textHight == 0){mTextHeight +=getLineHeight();}
                }
            }while(end>0);
        }
        mTextHeight += getPaddingTop() + getPaddingBottom();
        return mTextHeight;
    }

    @Override
    protected void onDraw(Canvas canvas){
        float heigth = getPaddingTop() + getLineHeight();
        for(String text : mCutStr){
            canvas.drawText(text, getPaddingLeft(),heigth,mPaint);
            heigth += getLineHeight();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        int height = setTextInfo(this.getText().toString(), parentWidth, parentHeight);

        if(parentHeight == 0)
            parentHeight = height;
        this.setMeasuredDimension(parentWidth,parentHeight);
    }

    @Override
    protected void onTextChanged(final  CharSequence text, final int start, final int before, final int after){
        setTextInfo(text.toString(),this.getWidth(), this.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        if(w != oldw){
            setTextInfo(this.getText().toString(),w,h);
        }
    }


}