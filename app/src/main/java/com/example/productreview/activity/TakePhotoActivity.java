package com.example.productreview.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.camerakit.CameraKitView;
import com.example.productreview.R;
import com.example.productreview.constants.ProductListState;
import com.example.productreview.di.ContextModule;
import com.example.productreview.di.DaggerAppComponent;
import com.example.productreview.models.Product;
import com.example.productreview.network.GateApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TakePhotoActivity extends Activity {

    private static final String TAG = "TakePhotoActivity";

    @BindView(R.id.camera)
    CameraKitView cameraKitView;

    @BindView(R.id.get_photo_btn)
    FloatingActionButton getPhotoBtn;

    @Inject
    GateApiService gateApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        ButterKnife.bind(this);
        DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build()
                .inject(this);
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("state")) {
            ProductListState state = (ProductListState) getIntent().getSerializableExtra("state");
            getPhotoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onImage(CameraKitView cameraKitView, byte[] capturedImage) {
                            MultipartBody.Part flePart = MultipartBody.Part.createFormData(
                                    "file",
                                    "file",
                                    RequestBody.create(MediaType.parse("multipart/form-data"), capturedImage));

                            //todo убрать константу beer
                            gateApiService.determinateProduct(flePart, state.getName())
                                    .enqueue(new Callback<Product>() {
                                        @Override
                                        public void onResponse(Call<Product> call, Response<Product> response) {
                                            setUp(response.body());
                                        }

                                        @Override
                                        public void onFailure(Call<Product> call, Throwable t) {
                                            setUp(new Product());
                                        }
                                    });
                            finish();
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }
    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }
    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void setUp(Product product) {
        Context context = getApplicationContext();
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("productId", product.getId());
        context.startActivity(intent);
    }
}
