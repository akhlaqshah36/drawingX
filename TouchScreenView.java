package com.akhlaq.checkbtn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TouchScreenView extends View {

    private static final int LINE_THICK = 3;
    private int width, height, eltW, eltH;
    private int awidth, aheight;
    float left, right, bottom, top;
    private int x1, y1;
    private int totalboxes;
    ArrayList<String> xvalues = new ArrayList<String>();
    private int noboxes = 0, noboxes2 = 0;

    private int withofsquare = 160;
    boolean touched = false;
    private ArrayList<Rect> rectangles = new ArrayList<Rect>();
    Canvas canvas;
    private Paint gridPaint, drawPaint, paint;
    private Path path = new Path();
    public static int touchmsg;

    public TouchScreenView(Context context) {
        super(context);
    }

    public TouchScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);

        gridPaint = new Paint();
        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setColor(Color.BLACK);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(5);
        gridPaint = new Paint(gridPaint);

    }

    int totalheightofBoxes;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        aheight = height;
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        awidth = width;
        noboxes = awidth / 7;

        noboxes2 = (height * 7 / width) + 1;
        withofsquare = noboxes;

        totalheightofBoxes = withofsquare * noboxes2;
        if (height + (withofsquare / 2) < totalheightofBoxes) {

            noboxes2--;
        }
        totalboxes = 7 * noboxes2;
        eltW = (width - LINE_THICK) / 14;
        eltH = (height - LINE_THICK) / 14;

        setMeasuredDimension(width, height);
    }

    public void markthespot(int x, int y) {
        int foundx = 1;
        int tempx = 0, tempy = 0;
        int p, w;
        for (w = 0; w < 20; w++) {
            p = w * withofsquare;
            if (x > p && x < (p + withofsquare)) {
                //foundx=2;
                tempx = p;
                break;
                // rectangles.add(new Rect(p, 0, p+withofsquare, withofsquare));
            }
        }
        for (w = 0; w < 20; w++) {
            p = w * withofsquare;
            if (y > p && y < (p + withofsquare)) {
                foundx = 2;
                tempy = p;
                break;
                // rectangles.add(new Rect(p, 0, p+withofsquare, withofsquare));
            }
        }

        int already = 1;
        for (String xval : xvalues) {

            if (xval.equals(tempx + "-" + tempy)) {

                already = 2;
                break;
            }
        }
        if (already == 1 && foundx != 1) {
            rectangles.add(new Rect(tempx, tempy, tempx + withofsquare, tempy + withofsquare));
            xvalues.add(tempx + "-" + tempy);

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int i = 1;
        drawGrid(canvas);

        //"#A52A2A" , "#DAA520" , "#800080" , "#FF00FF" , "#00FFFF" , "#E9AF0D" , "#f50057", "#000000", "#0039cb", "#64dd17", "#d50000","#462500","#460031", "#C71585" ,"#a50000","#ffd600","#64dd17","#4a148c","#5b36e2"
        paint = new Paint();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint = new Paint(paint);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        Log.d("ConditionCheck","True1");

        String[] colors = {"#ffffff"};
        int u = 0;
        for (Rect rect : rectangles) {

            paint.setColor(Color.parseColor(colors[u]));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, paint);
            invalidate();
            u++;
            if (u == colors.length) {
                u = 0;
            }
        }
        if (rectangles.size() == totalboxes) {
            //move to next screen
            Log.d("ConditionCheck","True2");

            Log.d(TAG, "game over" + totalboxes);
            touchmsg = 5;
            Log.d(TAG, "touchtotal" + touchmsg);

            //canvas.drawRect(left, top, right, bottom, gridPaint);
//            Intent intent = new Intent(getContext(), testCompleted.class);
//            getContext().startActivity(intent);
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                    //move to next screen
//                    Log.d(TAG,"TOUCH_Failed "+totalboxes);
//                FRPActivity.failedArr.add("Touch Screen Test");
//                    touchmsg = 5;
//                    Log.d(TAG,"TOUCH_Failed"+touchmsg);
//            }
//        },10000);

        i++;
    }

    private void drawGrid(Canvas canvas) {
        Paint p = new Paint();
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        b = Bitmap.createScaledBitmap(b, withofsquare, withofsquare, false);

        // p.setColor(Color.RED);
        top = 0;
        bottom = 0;
        for (int rows = 0; rows < noboxes2; rows++) {

            left = 0;
            right = 0;

            bottom += withofsquare;
            for (int i = 0; i < noboxes; i++) {
                // vertical lines
                //if (left==top) {
                right += withofsquare;
                // Log.d("Left&Top", "Left: " + left + "Top: " + top);
//                canvas.drawOval(left);
                canvas.drawBitmap(b, left, top, p);
                // canvas.drawOval(left, top, right, bottom, gridPaint);
                left = right;

            }
            top = bottom;

        }

    }

    //
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float inputsize = event.getSize();
        Log.d("InputSizeValue", "" + inputsize);
        int index = event.getActionIndex();
        int inputid = event.getPointerId(index);
        boolean isStylus = false;
        int stylusid = -1;
        width = (int) event.getX();
        x1 = (int) event.getX();
        y1 = (int) event.getY();
        height = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (inputsize>0.01) {
                    touched = false;
                }
                break;

            case MotionEvent.ACTION_DOWN:
                if (inputsize>0.01) {
                    path.moveTo(width, height);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (inputsize>0.01) {
                    markthespot(x1, y1);
                    touched = true;
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                break;

            default:
                return false;
        }
        invalidate();
        return true;
    }


}
