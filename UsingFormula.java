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

public class UsingFormula extends AppCompatActivity {

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
    float d;
    float t, x_1, y_1, x_2, y_2;
    float circle_diameter;
    float new_x, new_y;
    float n_points, gape;




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



        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        // p.setColor(0xff229bc6);
        p.setColor(Color.CYAN);

        path = new Path();

        path_paint = new Paint();
        path_paint.setColor(Color.WHITE);
        path_paint.setStyle(Paint.Style.FILL);

        mPaint = new Paint();

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        //  mPaint.setStrokeJoin(Paint.Join.ROUND);
        // mPaint.setStrokeCap(Paint.Cap.ROUND);
        //  mPaint.setStrokeWidth(100);
        my_canvas = new Canvas();

        // radius = pxToDp(250); //circle radius


        init();//Points to be used


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


        /* Starting Point Formula */



        center_point = new Point(width / 2, height / 2);

        y_2 = height /2;
        x_2 = width /2;

        circle_diameter = circleRadius*2;

        x_1 = circleRadius * 3;
        y_1 = circleRadius * 3 ;


        t= 1;


        d = (float) Math.sqrt(Math.pow((x_2 - x_1), 2) + Math.pow((y_2 - y_1),2));


        n_points = d / (circleRadius*2.5F);
        gape = 1/n_points;




        Log.d("Found d "," DISTANCE "+d+ " X_2 "+x_2+ " Y_2 "+y_2 +" G "+gape);

        Log.d("NEW POINTS "," POINTS: X  "+new_x+ " Y  "+new_y+ " N_POINTS "+ n_points);

        /* End Point Formula */





        setContentView(new View(this){

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);



            //    canvas.drawCircle(x_2, y_2,circleRadius,p);


                for(int i =0  ; i <= n_points; i++){

                    new_x = x_1 + (x_2 - x_1) *t;

                    new_y = y_1 + (y_2 - y_1) *t;

                    Log.d("NEW_X", "NEW_X : "+new_x+ " NEW_Y : "+new_y);

                    canvas.drawCircle(new_x, new_y, circleRadius, p);

                    t= t - gape;


                }



                t =1;


                for(int i =0  ; i <= n_points; i++){

                    x_1 = width - circleRadius*3;
                    y_1 = circleRadius*3;

                    new_x = x_1 + (x_2 - x_1) *t;

                    new_y = y_1 + (y_2 - y_1) *t;


                   // Log.d("NEW_X2", "NEW_X : "+new_x+ " NEW_Y : "+new_y);


                    canvas.drawCircle(new_x, new_y, circleRadius, p);

                    t = t- gape;


                }


                t =1;

                for(int i =0  ; i <= n_points; i++){

                    x_1 =  circleRadius*3;
                    y_1 = height - circleRadius*3;

                    new_x = x_1 + (x_2 - x_1) *t;

                    new_y = y_1 + (y_2 - y_1) *t;


                    // Log.d("NEW_X2", "NEW_X : "+new_x+ " NEW_Y : "+new_y);


                    canvas.drawCircle(new_x, new_y, circleRadius, p);

                    t = t- gape;


                }


                t =1;


                for(int i =0  ; i <= n_points; i++){

                    x_1 = width - circleRadius*3;
                    y_1 = height- circleRadius*3;

                    new_x = x_1 + (x_2 - x_1) *t;

                    new_y = y_1 + (y_2 - y_1) *t;


                    // Log.d("NEW_X2", "NEW_X : "+new_x+ " NEW_Y : "+new_y);


                    canvas.drawCircle(new_x, new_y, circleRadius, p);

                    t = t- gape;


                }








            }
        });

    }

/*

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
                 */
/*   Log.v(TAG, "\n");
                    Log.v(TAG, "X: " + i + " " + (MAX_ANGLE + all_points_list.get(i).x + "..>.." + x + "..<.." + all_points_list.get(i).x));
                    Log.v(TAG, "\n");
                    Log.v(TAG, "Y: " + i + " " + (MAX_ANGLE + all_points_list.get(i).y + "..>.." + y + "..<.." + all_points_list.get(i).y));
                    Log.v(TAG, "\n");*//*

                    if (MAX_ANGLE + all_points_list.get(i).x >= x && x >= all_points_list.get(i).x - MAX_ANGLE && MAX_ANGLE + all_points_list.get(i).y >= y && y >= all_points_list.get(i).y - MAX_ANGLE) {

                    */
/*If the coordinates and the center point coordinates are
                    smaller than the radius of the touch point trigger limit*//*

                        ListBoolean.set(i, true);
                    }


                }


                for (int i = 0; i < counter2; i++) {
                   */
/* Log.v(TAG, "\n");
                    Log.v(TAG, "X: " + i + " " + (MAX_ANGLE + all_points_list_square.get(i).x + "..>.." + x + "..<.." + all_points_list_square.get(i).x));
                    Log.v(TAG, "\n");
                    Log.v(TAG, "Y: " + i + " " + (MAX_ANGLE + all_points_list_square.get(i).y + "..>.." + y + "..<.." + all_points_list_square.get(i).y));
                    Log.v(TAG, "\n");*//*

                    if (MAX_ANGLE + all_points_list_square.get(i).x >= x && x >= all_points_list_square.get(i).x -
                            MAX_ANGLE && MAX_ANGLE + all_points_list_square.get(i).y >= y && y >= all_points_list_square.get(i).y - MAX_ANGLE) {

                    */
/*If the coordinates and the center point coordinates are
                    smaller than the radius of the touch point trigger limit*//*

                        ListBoolean2.set(i, true);
                    }


                }
                break;
            default:
                break;
        }


        return true;
    }
*/

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