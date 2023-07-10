package com.ghaythali.e_commerce_store_app.RegisterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ghaythali.e_commerce_store_app.InfoAppActivity.AboutFragment;
import com.ghaythali.e_commerce_store_app.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /****/
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentesRegisterContainerId, new LoginFragment())
                .commit();
    }
}