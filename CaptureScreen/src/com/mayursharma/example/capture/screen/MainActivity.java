package com.mayursharma.example.capture.screen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
    
	ImageView imageViewCapture, imageViewPreview;
	Bitmap bitmap;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        imageViewPreview = (ImageView) findViewById(R.id.ImageViewPreview);
        imageViewCapture = (ImageView) findViewById(R.id.ImageViewCapture);
        imageViewCapture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bitmap = getBitmapOfView(imageViewCapture);	
				imageViewPreview.setImageBitmap(bitmap);
				createImageFromBitmap(bitmap);
			}
		});
 
    }//onCreate Ends
    
    
    /*
     * @author : Mayur Sharma
     * This method is used to create the bitmap of the current activity
     * This method accepts any child view of the current view
     * You can even pass the parent container like RelativeLayout or LinearLayout as a param
     * @param : View v
     */
    public Bitmap getBitmapOfView(View v)
    {
       	View rootview = v.getRootView();
    	rootview.setDrawingCacheEnabled(true);
    	Bitmap bmp = rootview.getDrawingCache();
    	return bmp;
    }
    
    
    /*
     * @author : Mayur Sharma
     * This method is used to create an image file using the bitmap
     * This method accepts an object of Bitmap class
     * Currently we are passing the bitmap of the root view of current activity
     * The image file will be created by the name capturedscreen.jpg
     * @param : Bitmap bmp
     */
    public void createImageFromBitmap(Bitmap bmp)
    {
    	ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 1024, bytes);
		File file = new File( Environment.getExternalStorageDirectory() + "/capturedscreen.jpg");
		try 
		{
			file.createNewFile();
			FileOutputStream ostream = new FileOutputStream(file);
			ostream.write(bytes.toByteArray());        
			ostream.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}    
    }
    
    
}//Class Ends