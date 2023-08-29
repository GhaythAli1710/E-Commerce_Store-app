package com.ghaythali.e_commerce_store_app.InfoAppActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.ghaythali.e_commerce_store_app.HomeActivity.HomeActivity;
import com.ghaythali.e_commerce_store_app.R;
import com.ghaythali.e_commerce_store_app.RegisterActivity.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PermissionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PermissionsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /****/
    private LottieAnimationView perInternet, perCamera, perReadStorage, submitBtn;
    private Boolean internetPerChecked = false, cameraPerChecked = false, readStoragePerChecked = false;


    public PermissionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PermissionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PermissionsFragment newInstance(String param1, String param2) {
        PermissionsFragment fragment = new PermissionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissions, container, false);
        /**/
        Initialize(view);
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
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            },1500);
        });
        return view;
    }

    /****/
    private void Initialize(View view){
        perInternet = view.findViewById(R.id.checkPerInternet);
        perCamera = view.findViewById(R.id.checkPerCamera);
        perReadStorage = view.findViewById(R.id.checkPerStorage);
        submitBtn = view.findViewById(R.id.submitBtn);
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
            listPermissionsNeeded.add(android.Manifest.permission.INTERNET);
        }
        if (cameraPerChecked){
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if (readStoragePerChecked){
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(),
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    101);
        }
    }
}