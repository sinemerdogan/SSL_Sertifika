package com.gri.adminandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.gri.adminandroid.R;

/**
 * Created by sinem erdoğan on 14.10.2016.
 */

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException e) {


                } finally {

                    finish();

                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);

                }

            }
        };

        thread.start();

    }
}

