package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.dialog.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.hm.convertlab_mvp_dagger_rxjava.R;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;

public class CustomView extends View {
    private float firstCur;
    private Banks banks;
    private int paintForBankName = 1;
    private int paintForAllRows = 2;
    private int paintForCurName =3;
    private float mCurrenciesSize;
    private float mBankNameSize;
    private float mAllRowsSize;
    private int mCurrenciesColor;
    private float MARGIN_LEFT;  //50dp
    private float MARGIN_LEFT_CUR; // 100
    private float MARGIN_TOP; // 100
    private float MARGIN_LEFT_CUR_NUM;  //450
    private float DISTANCE_BETWEEN_ROWS;  //70
    private float DISTANCE_BETWEEN_CUR;  //150
    private float FIRS_LINE_CUR; // 320;
    private float DEFAULT_COMPONENT_FOR_CANVAS_SIZE; // 350
    private float DEFAULT_CUR_SIZE = 60f;
    private float DEFAULT_BANK_NAME_SIZE = 40f;
    private float DEFAULT_ALL_ROWS_SIZE = 34f;

    private Paint firstP, secondP, thirdP;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSizeAndCurForXML(context, attrs);

        MARGIN_LEFT = convertDpToPixel(20f, context);
        MARGIN_LEFT_CUR =convertDpToPixel(40f, context);
        MARGIN_TOP = convertDpToPixel(45f, context);
        MARGIN_LEFT_CUR_NUM = convertDpToPixel(160f, context);
        DISTANCE_BETWEEN_ROWS = convertDpToPixel(28f, context);
        DISTANCE_BETWEEN_CUR = convertDpToPixel(60f, context);
        FIRS_LINE_CUR = convertDpToPixel(145f, context);
        DEFAULT_COMPONENT_FOR_CANVAS_SIZE = convertDpToPixel(155f, context);

        initPaint();
    }

    public void setDialogBanks(Banks _banks){
        banks = _banks;
    }

    public void initPaint() {
        firstP = new Paint();
        firstP.setAntiAlias(true);
        firstP.setColor(Color.BLACK);
        firstP.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        firstP.setTextSize(mBankNameSize);

        secondP = new Paint();
        secondP.setAntiAlias(true);
        secondP.setColor(Color.BLACK);
        secondP.setTextSize(mAllRowsSize);

        thirdP = new Paint();
        thirdP.setColor(mCurrenciesColor);
        thirdP.setTextSize(mCurrenciesSize);
    }

    public void initSizeAndCurForXML (Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Options, 0, 0);
        mCurrenciesSize = a.getDimension(R.styleable.Options_currencies_size, DEFAULT_CUR_SIZE);
        mBankNameSize = a.getDimension(R.styleable.Options_bank_name_size, DEFAULT_BANK_NAME_SIZE);
        mAllRowsSize = a.getDimension(R.styleable.Options_all_rows_size, DEFAULT_ALL_ROWS_SIZE);
        mCurrenciesColor = a.getColor(R.styleable.Options_currencies_color,
                ContextCompat.getColor(context, R.color.currencies_color));
        a.recycle();
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final  float height = DEFAULT_COMPONENT_FOR_CANVAS_SIZE + banks.mCurrencies.size() * DISTANCE_BETWEEN_CUR;
        final  int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width,(int)height );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawText(banks.mBankName, MARGIN_LEFT, MARGIN_TOP, firstP);
        canvas.drawText(banks.mRegion, MARGIN_LEFT, MARGIN_TOP + DISTANCE_BETWEEN_ROWS, secondP);
        canvas.drawText(banks.mCity, MARGIN_LEFT, MARGIN_TOP + DISTANCE_BETWEEN_ROWS *2, secondP);

        for(int i = 0; i < banks.mCurrencies.size();i++){
            firstCur = FIRS_LINE_CUR+((i+1)* DISTANCE_BETWEEN_CUR);

            canvas.drawText(banks.mCurrencies.get(i).CurrenciesName,MARGIN_LEFT_CUR, firstCur,thirdP);
            canvas.drawText(banks.mCurrencies.get(i).getCanvasAsk()
                    + " / " + banks.mCurrencies.get(i).getCanvasBid(), MARGIN_LEFT_CUR_NUM, firstCur, secondP);
        }
    }

}
