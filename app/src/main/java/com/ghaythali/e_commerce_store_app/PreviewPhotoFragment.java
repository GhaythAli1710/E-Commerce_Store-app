package com.ghaythali.e_commerce_store_app;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviewPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviewPhotoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Uri imageUri;

    private Bitmap bitmap;


    public PreviewPhotoFragment() {
        // Required empty public constructor
    }

    public PreviewPhotoFragment(Uri data) {
        // Required empty public constructor
        imageUri = data;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreviewPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreviewPhotoFragment newInstance(String param1, String param2) {
        PreviewPhotoFragment fragment = new PreviewPhotoFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preview_photo, container, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView image = view.findViewById(R.id.productImageViewId);
        image.setImageURI(imageUri);
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        bitmap = drawable.getBitmap();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button submitBtn = view.findViewById(R.id.submitBtnId);
        submitBtn.setOnClickListener(v -> testAPI());
        return view;
    }

    private void testAPI(){
        //
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.43.161:8000/receive-image/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                // Do you fancy stuff
                                String imgStr = jo.getString("image");
//                                imgBytes = base64.b64decode(imgStr);
//                                img = Image.open(io.BytesIO(imgBytes));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show()
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("image", encodeBitmapImage());
                Log.d("gh", encodeBitmapImage()+"");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private String encodeBitmapImage(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        String encodeImageString = Base64.encodeToString(bytesofimage, Base64.DEFAULT);
        return encodeImageString;
    }

}