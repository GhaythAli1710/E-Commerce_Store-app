package com.ghaythali.e_commerce_store_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

public class InfoAppActivity extends AppCompatActivity {
    /****/
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);

        /****/
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentesContainerId, new AboutFragment())
                .commit();
    }
}