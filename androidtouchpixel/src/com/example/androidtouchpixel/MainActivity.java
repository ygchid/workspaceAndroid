package com.example.androidtouchpixel;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
 
 TextView touchedXY, invertedXY, imgSize, colorRGB;
 ImageView imgSource1, imgSource2;
 View v;
 Thread ti;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_LEFT_ICON);
    	setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        touchedXY = (TextView)findViewById(R.id.xy);
        invertedXY = (TextView)findViewById(R.id.invertedxy);
        imgSize = (TextView)findViewById(R.id.size);
        colorRGB = (TextView)findViewById(R.id.colorrgb);
     imgSource1 = (ImageView)findViewById(R.id.source1);
     imgSource2 = (ImageView)findViewById(R.id.source2);
     imgSource1.setOnTouchListener(imgSourceOnTouchListener);
     imgSource2.setOnTouchListener(imgSourceOnTouchListener);
     
    
     ti =new Thread(){	 
         @Override
	        public void run() {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            runOnUiThread(new Runnable() {
	                @Override
	                public void run() {
			    	    Bitmap bmp = getBitmapOfView(imgSource2);
			    	    imgSource2.setImageBitmap(bmp);
	                }
	            });
	        }
	    };
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                Thread.sleep(100);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            runOnUiThread(new Runnable() {
	                @Override
	                public void run() {
	                    ti.start();
	                }
	            });
	        }
	    }).start();
    }
//    OnTouchListener imgSourceOnTouchListener1= new OnTouchListener(){
//    	 @Override
//    	  public boolean onTouch(View v, MotionEvent event) {
//    		 ;
//		     return true;
//    	 }
//    };
    
  OnTouchListener imgSourceOnTouchListener= new OnTouchListener(){
  @Override
  public boolean onTouch(View view, MotionEvent event) {
   
   float eventX = event.getX();
   float eventY = event.getY();
   float[] eventXY = new float[] {eventX, eventY};
   
   Matrix invertMatrix = new Matrix();
   ((ImageView)view).getImageMatrix().invert(invertMatrix);
   
   invertMatrix.mapPoints(eventXY);
   int x = Integer.valueOf((int)eventXY[0]);
   int y = Integer.valueOf((int)eventXY[1]);
   
   touchedXY.setText(
     "touched position: "
     + String.valueOf(eventX) + " / " 
     + String.valueOf(eventY));
   invertedXY.setText(
     "touched position: "
     + String.valueOf(x) + " / " 
     + String.valueOf(y));

   Drawable imgDrawable = ((ImageView)view).getDrawable();
   Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();
   
   imgSize.setText(
     "drawable size: "
     + String.valueOf(bitmap.getWidth()) + " / " 
     + String.valueOf(bitmap.getHeight()));
   
   //Limit x, y range within bitmap
   if(x < 0){
    x = 0;
   }else if(x > bitmap.getWidth()-1){
    x = bitmap.getWidth()-1;
   }
   
   if(y < 0){
    y = 0;
   }else if(y > bitmap.getHeight()-1){
    y = bitmap.getHeight()-1;
   }

   int touchedRGB = bitmap.getPixel(x, y);
   
   colorRGB.setText("touched color: " + "#" + Integer.toHexString(touchedRGB));
   colorRGB.setTextColor(touchedRGB);
   
   return true;
  }};

	public Bitmap getBitmapOfView(View v)
	{
	    View rootview = v.getRootView();
		rootview.setDrawingCacheEnabled(true);
		Bitmap bmp = rootview.getDrawingCache();
		return bmp;
	}
}
