package com.ghaythali.e_commerce_store_app.InfoAppActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

import com.ghaythali.e_commerce_store_app.R;

public class InfoAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);

        /****/
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentesInfoAppContainerId, new AboutFragment())
                .commit();
    }
}