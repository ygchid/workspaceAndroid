package com.example.helloapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.ServiceManager;
import android.util.Log;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
 
public class MainActivity extends Activity {
 
    final IWindowManager windowManager = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
 
    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.setContentView(R.layout.activity_main);
        Button fill = (Button) this.findViewById(R.id.fill);
        fill.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View view) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_A));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_A));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_N));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_N));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_D));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_D));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_R));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_R));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_O));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_O));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_I));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_I));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_D));
                            sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_D));
                        } catch (Exception e) {
                            Log.e("HelloApp", e.getMessage(), e);
                        }
                    }
                });
                t.start();
            }
        });
    }
 
    public void sendKeySync(KeyEvent event) throws DeadObjectException {
        //windowManager.injectKeyEvent(event.getAction(), event.getKeyCode(), event.getRepeatCount(), event.getDownTime(), event.getEventTime(), true);
    }
}
