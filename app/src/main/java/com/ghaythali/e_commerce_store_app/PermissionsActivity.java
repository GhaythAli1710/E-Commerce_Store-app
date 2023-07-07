package com.ghaythali.e_commerce_store_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;
import java.util.ArrayList;
import java.util.List;

public class PermissionsActivity extends AppCompatActivity {

    /****/
    private LottieAnimationView perInternet, perCamera, perReadStorage, submitBtn;
    private Boolean internetPerChecked = false, cameraPerChecked = false, readStoragePerChecked = false;

    /****/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**/
        Initialize();
        /**/
        perInternetCheck();
        /**/
        perCameraCheck();
        /**/
        perReadStorageCheck();
        /**/
        submitBtn.setOnClickListener(v -> {
            submitBtn.setMinAndMaxProgress(0.5f,1.0f);
            submitBtn.playAnimation();
            //
            new Handler().postDelayed(() -> {
                grantedPermissions();
//                startActivity(new Intent(PermissionActivity.this, LoginActivity.class));
//                finish();
            },1500);
        });
    }

    /****/
    private void Initialize(){
        setContentView(R.layout.activity_permissions);
        perInternet = findViewById(R.id.checkPerInternet);
        perCamera = findViewById(R.id.checkPerCamera);
        perReadStorage = findViewById(R.id.checkPerStorage);
        submitBtn = findViewById(R.id.submitBtn);
    }

    /****/
    private void perInternetCheck(){
        perInternet.setOnClickListener(v -> {
            if(internetPerChecked){
//                perInternet.setMinAndMaxProgress(0.5f,1.0f);
                perInternet.setSpeed(-2);
                perInternet.playAnimation();
                internetPerChecked = false;
            }else{
//                perInternet.setMinAndMaxProgress(0.0f,0.5f);
                perInternet.setSpeed(2);
                perInternet.playAnimation();
                internetPerChecked = true;
            }
        });
    }

    /****/
    private void perCameraCheck(){
        perCamera.setOnClickListener(v -> {
            if(cameraPerChecked){
//                perCamera.setMinAndMaxProgress(0.5f,1.0f);
                perCamera.setSpeed(-2);
                perCamera.playAnimation();
                cameraPerChecked = false;
            }else{
//                perCamera.setMinAndMaxProgress(0.0f,0.5f);
                perCamera.setSpeed(2);
                perCamera.playAnimation();
                cameraPerChecked = true;
            }
        });
    }

    /****/
    private void perReadStorageCheck(){
        perReadStorage.setOnClickListener(v -> {
            if(readStoragePerChecked){
//                perReadStorage.setMinAndMaxProgress(0.5f,1.0f);
                perReadStorage.setSpeed(-2);
                perReadStorage.playAnimation();
                readStoragePerChecked = false;
            }else{
//                perReadStorage.setMinAndMaxProgress(0.0f,0.5f);
                perReadStorage.setSpeed(2);
                perReadStorage.playAnimation();
                readStoragePerChecked = true;
            }
        });
    }

    /****/
    private void grantedPermissions(){
        List<String> listPermissionsNeeded = new ArrayList<>();
        /**/
        if (internetPerChecked){
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (cameraPerChecked){
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (readStoragePerChecked){
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(PermissionsActivity.this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    101);
        }
    }
}