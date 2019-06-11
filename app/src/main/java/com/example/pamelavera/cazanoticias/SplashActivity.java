package com.example.pamelavera.cazanoticias;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    final static int SPLASH_SCREEN_DELAY = 2500;
    private ProgressBar pbCargando;
    private int progressStatus = 0;
    TextView tvCargandoSplash;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_splash);

        pbCargando = findViewById(R.id.pbCargando);
        tvCargandoSplash = findViewById(R.id.tvCargandoSplash);


        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 5;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            pbCargando.setProgress(progressStatus);
                            tvCargandoSplash.setText(progressStatus +"/"+ pbCargando.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","La actividad Splash se encuentra en estado resumida ");

        TimerTask task = new TimerTask() {

            @Override //Se sobrescribe el metodo de la clase TimerTask llamada run
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
