package com.akhlaq.checkbtn;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CompleteX extends AppCompatActivity {

    private static final int CIRCLE_RADIUS_DP = 20;
    Paint p;
    int height;
    int width;
    Activity windowActivity;
    float slope;
    float y2_formula;
    Path path;
    Paint path_paint;
    private Paint paint = new Paint();
    boolean TouchUP = false;//Whether your finger is off the screen
    Path mPath;
    Paint mPaint;
    Paint mBitmapPaint;
    Context context;
    Paint circlePaint;
    Path circlePath;
    private int totalTouches;
    int Touch = 4;//Number of trigger points
    private List<Point> pointerLocations = new ArrayList<>();//point
    List<Point> all_points_list = new ArrayList<>();
    List<Point> all_points_list_square = new ArrayList<>();
    List<Point> pointX1_list = new ArrayList<>();

    List<Point> pointX2_list = new ArrayList<>();

    List<Point> pointX3_list = new ArrayList<>();

    List<Point> pointX4_list = new ArrayList<>();

    List<Point> pointY1_list = new ArrayList<>();
    List<Point> pointY2_list = new ArrayList<>();
    List<Point> pointY3_list = new ArrayList<>();
    List<Point> pointY4_list = new ArrayList<>();


    List<Point> drawing_points = new ArrayList<>();

    Point center_point;

    //int radius,
    int x_center, y_center;
    Point point_coords_l1;
    Point point_coords_l2;
    Point point_coords_l3;
    Point point_coords_l4;
    Point point_coords_top;
    Point point_coords_right;
    Point point_coords_bottom;
    Point point_coords_left;
    int increment = 0;
    int counts = 0;

    int counter = 0;
    int counter2 = 0;


    Canvas my_canvas;
    private int TouchTime;
    private int Time = 20;
    private int MAX_ANGLE = 80;
    // private List<Point> pointer = new ArrayList<>();//point

    ArrayList<Boolean> ListBoolean = new ArrayList<Boolean>();
    ArrayList<Boolean> ListBoolean2 = new ArrayList<Boolean>();
    boolean MultiTouch = false;//Single-touch & multi-touch

    private int circleRadius;
    private int TestQuantity = 0;
    int stopAfterPass = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  UsingSlope usingSlope = new UsingSlope(this, this);

        setContentView(R.layout.activity_get_grid_view);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        height = displayMetrics.heightPixels;

        width = displayMetrics.widthPixels;

        // hideSystemUI();

        getSupportActionBar().hide();

/*

        if (checkDeviceHasNavigationBar(this)) {
            width = displayMetrics.widthPixels + getNavigationBarHeight(this);

        } else {
*/


        p = new Paint();
        p.setStyle(Paint.Style.FILL);
       // p.setColor(0xff229bc6);
        p.setColor(Color.CYAN);
       // p.setStrokeWidth(5);
      //  p.setAntiAlias(true);

        path = new Path();

        path_paint = new Paint();
        path_paint.setColor(Color.WHITE);
        path_paint.setStyle(Paint.Style.FILL);
       // path_paint.setAntiAlias(true);
      //  path_paint.setStrokeWidth(100);


        /*height = get_height;
        width = get_width;*/
        // windowActivity = attachActivity;
        // slope = findSlope(0, width, 0, height);
        Log.d("Find Height ", "Result " + height);
        // y2_formula = find_y2(slope, width);
        Log.d("Y2", " Result : " + y2_formula);

        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        circlePaint = new Paint();
        circlePath = new Path();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(4f);

        mPaint = new Paint();

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
      //  mPaint.setStrokeJoin(Paint.Join.ROUND);
       // mPaint.setStrokeCap(Paint.Cap.ROUND);
      //  mPaint.setStrokeWidth(100);
        my_canvas = new Canvas();

        // radius = pxToDp(250); //circle radius

        Log.d("Radius Dpi", " radius value" + circleRadius);
        x_center = (width) - (width / 2);
        y_center = (height) - (height / 2);

        center_point = new Point(width / 2, height / 2);

        //  Toast.makeText(context, "X "+x_center+ " Y "+y_center, Toast.LENGTH_SHORT).show();
        init();//Points to be used


        int width_limit = width - (circleRadius / 2);
        int height_limit = height - (circleRadius * 4);
        double radius_increment = 5;


        //from center point to bottom right
        int x1 = x_center ;
        int y1 = y_center ;


        //from center to top left
        int x2 = x_center ;
        int y2 = y_center ;

        //from center point to top right
        int x3 = x_center ;
        int y3 = y_center ;

        //from center to bottom left

        int x4 = x_center ;
        int y4 = y_center ;

        //Top left to top right
        int xTop = 5 + (circleRadius / 2);  // X will be increasing
        int yTop = 5 + (circleRadius / 2);

        //Top right to bottom right
        int xRight = width - circleRadius/2;
        int yRight = 5 + (circleRadius / 2); // Y will be increasing

        //bottom left to bottom right
        int xBottom = 5 + (circleRadius); // X will be increasing
        int yBottom = (int) (height- (circleRadius/2));

        //top left to bottom left
        int xLeft = 5 + (circleRadius / 2);
        int yLeft = 5 + (circleRadius / 2); // Y will be increasing

        int second_list_start ;


        for (int i = 0; i <= 10; i++) {


            if (width_limit >= circleRadius *4 && height_limit > circleRadius *4) {


                Log.d("WIDTH_LIMIT", "Width Limit " + width_limit + " RADIUS " + circleRadius * 2);
                point_coords_l1 = new Point(x1, y1);
                point_coords_l2 = new Point(x2, y2);
                point_coords_l3 = new Point(x3, y3);
                point_coords_l4 = new Point(x4, y4);
                point_coords_top = new Point(x4, y4);
                point_coords_right = new Point(x4, y4);
                point_coords_bottom = new Point(x4, y4);
                point_coords_left = new Point(x4, y4);

                //  drawing_points.add(0,point_coords_l1);
                pointX1_list.add(i, point_coords_l1);
                pointX2_list.add(i, point_coords_l2);
                pointX3_list.add(i, point_coords_l3);
                pointX4_list.add(i, point_coords_l4);

                //  for(int j = 0; j <= 1; j++){

                all_points_list.add(i * 4, point_coords_l1);
                all_points_list.add(i * 4 + 1, point_coords_l2);
                all_points_list.add(i * 4 + 2, point_coords_l3);
                all_points_list.add(i * 4 + 3, point_coords_l4);

                Log.d("MULTIPLICATIONS", " 0 :  " + (i * 4) + "\n 1 : " + (i * 4 + 1) + "\n 2 : " + (i * 4 + 2) + "\n 3 : " + (i * 4 + 3));
                // }
                //  pointY1_list.add((float) y1);


                width_limit = (int) ((width_limit) - (circleRadius *1.6 ));
                height_limit = height_limit - (circleRadius * 2);

                x1 = (int) (x1 + (circleRadius *.8));
                y1 = (int) (y1 + (circleRadius * 1.8));

                x2 = (int) (x2 - (circleRadius *.8));
                y2 = (int) (y2 - (circleRadius * 1.8));

                x3 = (int) (x3 + (circleRadius *.8));
                y3 = (int) (y3 - (circleRadius * 1.8));

                x4 = (int) (x4 - (circleRadius * .8));
                y4 = (int) (y4 + (circleRadius * 1.8));

                counts++;



            }

        }





        int top_to_bottom = height;
        int left_to_right = width;

        for (int i = 0 ; i < 50; i++) {

            if (top_to_bottom >= circleRadius*2) {


                point_coords_right = new Point(xRight, yRight);
                point_coords_left = new Point(xLeft, yLeft);


                //  pointY2_list.add(i, point_coords_right);
                //  pointY4_list.add(i, point_coords_left);
                top_to_bottom = top_to_bottom - (circleRadius * 2);

                yRight = yRight + circleRadius*2;
                yLeft  = yLeft + circleRadius*2;



                // add all pointers to main List

                all_points_list_square.add(i, point_coords_right);
                all_points_list_square.add(i+1, point_coords_left);

                counts++;




            }




        }


        second_list_start = all_points_list_square.size()-1;


        for(int j =0; j <10; j++){

          //  Log.d("SECOND_LIST_START"," Last index "+second_list_start);

            if (left_to_right >= circleRadius*2) {


                point_coords_top = new Point(xTop, yTop);

                point_coords_bottom = new Point(xBottom, yBottom);


               /* pointY3_list.add(j, point_coords_bottom);
                pointY1_list.add(j, point_coords_top);*/

                all_points_list_square.add(second_list_start + (j * 2 + 1), point_coords_top);
                all_points_list_square.add(second_list_start + (j * 2 + 2), point_coords_bottom);

                xTop = (xTop + circleRadius*2);
                xBottom = (xBottom + circleRadius*2);

                left_to_right = left_to_right - (circleRadius*2);


                Log.d("SECOND_LIST_SIZE"," LOOP 2 Level 2"+all_points_list.size());
            }

        }

        all_points_list.add(all_points_list.size() - 1, center_point);


        //add Booleans for Cross
        for (int i = 0; i < all_points_list.size(); i++) {

            ListBoolean.add(i, false);
            counter++;

        }

        //add Booleans for square box
        for (int i = 0; i < all_points_list_square.size(); i++) {

            ListBoolean2.add(i, false);
            counter2++;

        }


        setContentView(new View(this) {

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                canvas.drawColor(Color.WHITE);

                if (stopAfterPass == 0) {


                    for (int j = 0; j < all_points_list.size(); j++) {


                        canvas.drawCircle(all_points_list.get(j).x, all_points_list.get(j).y, circleRadius, p);

                        if (ListBoolean.get(j)) {

                            canvas.drawCircle(all_points_list.get(j).x, all_points_list.get(j).y, circleRadius, mPaint);
                        }

                    }


                    for (int j = 0; j < all_points_list_square.size(); j++) {


                        canvas.drawCircle(all_points_list_square.get(j).x, all_points_list_square.get(j).y, circleRadius, p);

                        if (ListBoolean2.get(j)) {

                            canvas.drawCircle(all_points_list_square.get(j).x, all_points_list_square.get(j).y, circleRadius, mPaint);
                        }

                        Log.d("AFTER_DRAWN", "DRAWN SUCCESSFULLY");

                    }


                    int passX = 0; // For cross
                    int passX2 = 0; // for square box

                    for (int i = 0; i < counter; i++) {//Check if every point passed ListBoolean for cross
                        if (ListBoolean.get(i)) {
                            passX++;//Passing point
                        }
                    }

                    for (int i = 0; i < counter2; i++) {//Check if every point passed ListBoolean for square box
                        if (ListBoolean2.get(i)) {
                            passX2++;//Passing point
                        }
                    }

                    if (passX >= counter && passX2 >= counter2) {//Points passed
                        Toast.makeText(CompleteX.this, "Test Complete", Toast.LENGTH_SHORT).show();
                        stopAfterPass = 1;

                        //  startActivity(intentPass);
                        MultiTouch = true;//Change more points
                        //carry out


                    }


                    invalidate();
                }
            }

        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int action = event.getActionMasked();
        int numTouches = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                totalTouches = numTouches;//Check the number of multi-touch
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                //   TouchUP = true;//Finger away
            }
            case MotionEvent.ACTION_CANCEL: {
                //Move the index down and increase the last index
                if (pointerIndex < numTouches - 1) {
                    Point p = pointerLocations.get(pointerIndex);
                    pointerLocations.get(numTouches - 1).x = p.x;
                    pointerLocations.get(numTouches - 1).y = p.y;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:
                pointerLocations.get(0).x = (int) event.getX(0);
                pointerLocations.get(0).y = (int) event.getY(0);
                int x = (int) event.getX(0);
                int y = (int) event.getY(0);
                //Checkpoint meets conditions
                for (int i = 0; i < counter; i++) {
                 /*   Log.v(TAG, "\n");
                    Log.v(TAG, "X: " + i + " " + (MAX_ANGLE + all_points_list.get(i).x + "..>.." + x + "..<.." + all_points_list.get(i).x));
                    Log.v(TAG, "\n");
                    Log.v(TAG, "Y: " + i + " " + (MAX_ANGLE + all_points_list.get(i).y + "..>.." + y + "..<.." + all_points_list.get(i).y));
                    Log.v(TAG, "\n");*/
                    if (MAX_ANGLE + all_points_list.get(i).x >= x && x >= all_points_list.get(i).x - MAX_ANGLE && MAX_ANGLE + all_points_list.get(i).y >= y && y >= all_points_list.get(i).y - MAX_ANGLE) {

                    /*If the coordinates and the center point coordinates are
                    smaller than the radius of the touch point trigger limit*/
                        ListBoolean.set(i, true);
                    }


                }


                for (int i = 0; i < counter2; i++) {
                   /* Log.v(TAG, "\n");
                    Log.v(TAG, "X: " + i + " " + (MAX_ANGLE + all_points_list_square.get(i).x + "..>.." + x + "..<.." + all_points_list_square.get(i).x));
                    Log.v(TAG, "\n");
                    Log.v(TAG, "Y: " + i + " " + (MAX_ANGLE + all_points_list_square.get(i).y + "..>.." + y + "..<.." + all_points_list_square.get(i).y));
                    Log.v(TAG, "\n");*/
                    if (MAX_ANGLE + all_points_list_square.get(i).x >= x && x >= all_points_list_square.get(i).x -
                            MAX_ANGLE && MAX_ANGLE + all_points_list_square.get(i).y >= y && y >= all_points_list_square.get(i).y - MAX_ANGLE) {

                    /*If the coordinates and the center point coordinates are
                    smaller than the radius of the touch point trigger limit*/
                        ListBoolean2.set(i, true);
                    }


                }
                break;
            default:
                break;
        }


        return true;
    }

    private void init() {
        circleRadius = (int) (CIRCLE_RADIUS_DP * getResources().getDisplayMetrics().density);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        final int maxPointers = 100;
        for (int i = 0; i < maxPointers; i++) {
            pointerLocations.add(new Point());
            Log.d("Locations", "Pointer Location added" + pointerLocations.get(i));
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = this.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                      //  | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                      //  | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
/*
    private int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }*/
}