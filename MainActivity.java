package com.akhlaq.checkbtn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Resources mResources;
    private RelativeLayout mRelativeLayout;
    private Button mButton;
    private ImageView mImageView;
    private int height;
    private int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UsingSlope usingSlope = new UsingSlope(this,this);
      //  setContentView(R.layout.activity_main);
        setContentView(usingSlope);

        getSupportActionBar().hide();

       /*   Intent intent = new Intent(MainActivity.this, UsingFormula.class);
         startActivity(intent);*/


     /*   PixelGridView pixelGrid = new PixelGridView(this);
        pixelGrid.setNumColumns(6);
        pixelGrid.setNumRows(6*2);

        setContentView(pixelGrid);*/





     //   Toast.makeText(mContext, "Width "+width+"Height "+height, Toast.LENGTH_SHORT).show();





    }




}
