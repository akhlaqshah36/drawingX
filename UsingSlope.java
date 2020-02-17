package com.akhlaq.checkbtn;

import androidx.appcompat.widget.ActionBarContainer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UsingSlope extends View {


    private static final int CIRCLE_RADIUS_DP = 20;
    Paint p;
    int height;
    int width;
    Activity windowActivity;
    private Paint paint = new Paint();
    Paint mPaint;
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
    int counts = 0;

    int counter = 0;
    int counter2 = 0;


    Canvas my_canvas;
    private int MAX_ANGLE = 80;
    // private List<Point> pointer = new ArrayList<>();//point

    ArrayList<Boolean> ListBoolean = new ArrayList<Boolean>();
    ArrayList<Boolean> ListBoolean2 = new ArrayList<Boolean>();
    boolean MultiTouch = false;//Single-touch & multi-touch

    private int circleRadius;
    int stopAfterPass = 0;
    float d;
    float t, x_1, y_1, x_2, y_2;
    float circle_diameter;
    int new_x, new_y;
    float n_points, gape;


    public UsingSlope(Context context, Activity attachActivity) {
        super(context);


        attachActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        attachActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        attachActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        height = displayMetrics.heightPixels;

        width = displayMetrics.widthPixels;

        windowActivity = attachActivity;

     //   windowActivity.getSupportActionbar().hide();

        // hideSystemUI();

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


        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        my_canvas = new Canvas();

        // radius = pxToDp(250); //circle radius

        x_center = (width) - (width / 2);
        y_center = (height) - (height / 2);

        center_point = new Point(width / 2, height / 2);

        init();//Points to be used



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


        int width_limit = width;
        int height_limit = height - (circleRadius * 2);
        double radius_increment = -5;


        //from center point to bottom right
        int x1 = (x_center + circleRadius);
        int y1 = y_center + (circleRadius + (circleRadius/2));


        //from center to top left
        int x2 = x_center - circleRadius;
        int y2 = y_center - (circleRadius +(circleRadius/2));

        //from center point to top right
        int x3 = x_center + circleRadius;
        int y3 = y_center - (circleRadius +(circleRadius/2));

        //from center to bottom left

        int x4 = x_center - circleRadius;
        int y4 = y_center + (circleRadius +(circleRadius/2));

        //Top left to top right
        int xTop = circleRadius ;  // X will be increasing
        int yTop =  (circleRadius );

        //Top right to bottom right
        int xRight = width - circleRadius;
        int yRight = (int) (circleRadius *3); // Y will be increasing

        //bottom left to bottom right
        int xBottom = circleRadius ; // X will be increasing
        int yBottom = (int) (height - (circleRadius));

        //top left to bottom left
        int xLeft =  (circleRadius );
        int yLeft = (int) (circleRadius *3); // Y will be increasing

        int second_list_start;

        int left_height = height - circleRadius;
        int right_height = height - circleRadius;

        for(int i =0  ; i <= n_points; i++){

            new_x = (int) (x_1 + (x_2 - x_1) *t);

            new_y = (int) (y_1 + (y_2 - y_1) *t);

            Log.d("NEW_X", "NEW_X : "+new_x+ " NEW_Y : "+new_y);

           // canvas.drawCircle(new_x, new_y, circleRadius, p);
            all_points_list.add(i, new Point(new_x, new_y));

            t= t - gape;


        }

        int List_move_to = all_points_list.size()-1;
        t =1;


        for(int i =List_move_to  ; i <= n_points + List_move_to; i++){

            x_1 = width - circleRadius*3;
            y_1 = circleRadius*3;

            new_x = (int) (x_1 + (x_2 - x_1) *t);

            new_y = (int) (y_1 + (y_2 - y_1) *t);

            all_points_list.add(i, new Point(new_x, new_y));


            // Log.d("NEW_X2", "NEW_X : "+new_x+ " NEW_Y : "+new_y);


           // canvas.drawCircle(new_x, new_y, circleRadius, p);

            t = t- gape;


        }



        List_move_to = all_points_list.size()-1;

        t =1;

        for(int i =List_move_to  ; i <= n_points + List_move_to; i++){

            x_1 =  circleRadius*3;
            y_1 = height - circleRadius*3;

            new_x = (int) (x_1 + (x_2 - x_1) *t);

            new_y = (int) (y_1 + (y_2 - y_1) *t);

            all_points_list.add(i, new Point(new_x, new_y));


            // Log.d("NEW_X2", "NEW_X : "+new_x+ " NEW_Y : "+new_y);


           // canvas.drawCircle(new_x, new_y, circleRadius, p);

            t = t- gape;


        }



        List_move_to = all_points_list.size() -1;
        t =1;


        for(int i = List_move_to  ; i <= n_points+List_move_to; i++){

            x_1 = width - circleRadius*3;
            y_1 = height- circleRadius*3;

            new_x = (int) (x_1 + (x_2 - x_1) *t);

            new_y = (int) (y_1 + (y_2 - y_1) *t);


            all_points_list.add(i, new Point(new_x, new_y));
            // Log.d("NEW_X2", "NEW_X : "+new_x+ " NEW_Y : "+new_y);


          //  canvas.drawCircle(new_x, new_y, circleRadius, p);

            t = t- gape;


        }

        int top_to_bottom = height  - yRight;
        int left_to_right = width;

        for (int i = 0 ; i < 50; i++) {

            if (top_to_bottom >= circleRadius*4) {


                point_coords_right = new Point(xRight, yRight);
                point_coords_left = new Point(xLeft, yLeft);


                //  pointY2_list.add(i, point_coords_right);
                //  pointY4_list.add(i, point_coords_left);
                top_to_bottom = height - yRight;

                yRight = yRight + circleRadius*2;
                yLeft  = yLeft + circleRadius*2;



                // add all pointers to main List

                all_points_list_square.add(i, point_coords_right);
                all_points_list_square.add(1+i, point_coords_left);

                counts++;




            }




        }


        second_list_start = all_points_list_square.size()-1;


        for(int j =0; j <50; j++){

            //  Log.d("SECOND_LIST_START"," Last index "+second_list_start);

            if (left_to_right >= circleRadius) {


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
    }


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
                Toast.makeText(getContext(), "Test Complete", Toast.LENGTH_SHORT).show();

                //  startActivity(intentPass);
                MultiTouch = true;//Change more points
                stopAfterPass = 1;
                //carry out


            }


            invalidate();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int action = event.getActionMasked();
        int numTouches = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
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
        View decorView = windowActivity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        //  | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        //   | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


}