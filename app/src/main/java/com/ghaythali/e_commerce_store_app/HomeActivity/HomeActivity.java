package com.ghaythali.e_commerce_store_app.HomeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ghaythali.e_commerce_store_app.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    /**/
    private ChipNavigationBar chipNavigationBar;
    private SNavigationDrawer sNavigationDrawer;
    private Fragment fragment = new HomeFragment();

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setStatusBarColor(R.color.black);
        getWindow().setColorMode(ActivityInfo.COLOR_MODE_HDR);

        chipNavigationBar = findViewById(R.id.bottomBarId);
        chipNavigationBar.setItemSelected(R.id.itemHomeBottomBar, true);

        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List<MenuItem> itemList = new ArrayList<>();
        itemList.add(new MenuItem("HOME",R.drawable.ic_home));
        itemList.add(new MenuItem("CAMERA",R.drawable.ic_camera));
        itemList.add(new MenuItem("SEARCH",R.drawable.ic_search));

        sNavigationDrawer.setMenuItemList(itemList);
        sNavigationDrawer.setAppbarTitleTV("HOME");
        /**/
        getSupportFragmentManager()
                .beginTransaction().replace(
                        R.id.fragmentsContainerId,
                        fragment
                ).commit();

        //
        bottomBarMenu();
        //
        fab();
        //
        navBar();
    }

    @SuppressLint("SetTextI18n")
    private void fab() {
        TextView textView;
        textView = findViewById(R.id.fabCounter);
        ImageButton imageButton;
        imageButton = findViewById(R.id.fabBtn);
        imageButton.setOnClickListener(v -> {
            fragment = new ShoppingBasketFragment();
            getSupportFragmentManager()
                    .beginTransaction().replace(
                            R.id.fragmentsContainerId,
                            fragment
                    ).commit();
            textView.setVisibility(View.VISIBLE);
            textView.setText("30");
            chipNavigationBar.setItemSelected(R.id.itemHomeBottomBar, false);
            chipNavigationBar.setItemSelected(R.id.itemCameraBottomBar, false);
            chipNavigationBar.setItemSelected(R.id.itemSearchBottomBar, false);
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void bottomBarMenu() {
        chipNavigationBar.setOnItemSelectedListener(i -> {
            if(i==R.id.itemHomeBottomBar) {
                fragment = new HomeFragment();
            } else if (i==R.id.itemCameraBottomBar) {
                fragment = new CameraFragment();
            } else if (i==R.id.itemSearchBottomBar) {
                fragment = new SearchFragment();
            }
            getSupportFragmentManager()
                    .beginTransaction().replace(
                            R.id.fragmentsContainerId,
                            Objects.requireNonNull(fragment)
                    ).commit();
        });
    }
    //
    @SuppressLint("NonConstantResourceId")
    public void menuSelector(){
        sNavigationDrawer.setOnMenuItemClickListener(position -> {
            switch (position){
                case 0:
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
    //
    public void navBar(){
        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {
                menuSelector();
            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}