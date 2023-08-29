package com.ghaythali.e_commerce_store_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.ghaythali.e_commerce_store_app.HomeActivity.HomeActivity;
import com.ghaythali.e_commerce_store_app.InfoAppActivity.InfoAppActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    /****/
    private static final int SPLASH_SCREEN = 2100;

    /****/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**/
        Initialize();
        /**/
        Action();
    }

    /****/
    private void Initialize(){
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**/
    }

    /****/
    private void Action(){
        /**/
        new Handler().postDelayed(() -> {
//                if(checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
//                    startActivity(new Intent(SplashScreenActivity.this,PermissionActivity.class));
//                }
//                else{
//                    startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
//                }
            startActivity(new Intent(SplashScreenActivity.this, InfoAppActivity.class));
//            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));

            finish();
        },SPLASH_SCREEN);
    }
}