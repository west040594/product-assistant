package com.example.productreview.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.productreview.R;
import com.example.productreview.adapters.ReviewAdapter;
import com.example.productreview.di.ContextModule;
import com.example.productreview.di.DaggerAppComponent;
import com.example.productreview.models.Product;
import com.example.productreview.network.GateApiService;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";


    @BindView(R.id.reviews_recycler_view)
    RecyclerView mReviewsRecyclerView;
    @BindView(R.id.product_name)
    TextView mName;
    @BindView(R.id.product_description)
    TextView mDescription;
    @BindView(R.id.product_average_price)
    TextView mAveragePrice;
    @BindView(R.id.product_average_price_title)
    TextView mAveragePriceTitle;
    @BindView(R.id.product_image_view)
    ImageView mImageView;
    @BindView(R.id.product_rating)
    TextView mRating;
    @BindView(R.id.product_rating_title)
    TextView mRatingTitle;
    @BindView(R.id.product_create_by)
    TextView mCreateBy;
    @BindView(R.id.product_create_by_title)
    TextView mCreateByTitle;
    @BindView(R.id.product_last_modified_by)
    TextView mLastModifiedBy;
    @BindView(R.id.product_last_modified_by_title)
    TextView mLastModifiedByTitle;
    @BindView(R.id.product_create_time)
    TextView mCreateTime;
    @BindView(R.id.product_create_time_title)
    TextView mCreateTimeTitle;
    @BindView(R.id.product_last_modify_time)
    TextView mLastModifyTime;
    @BindView(R.id.product_last_modify_time_title)
    TextView mLastModifyTimeTitle;
    @BindView(R.id.product_reviews_title)
    TextView mReviewsTitle;
    @BindView(R.id.loadingProductPanel)
    ProgressBar productLoadingProgressBar;

    LinearLayoutManager mLayoutManager;

    @Inject
    GateApiService gateApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build()
                .inject(this);
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("productId")) {
            Long productId = getIntent().getLongExtra("productId", 0);
            Log.d(TAG, "product id: " + productId);
            gateApiService
                    .fetchProductById(productId)
                    .enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            setUp(response.body());
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
        }
    }

    private void setUp(Product product) {
        productLoadingProgressBar.setVisibility(View.GONE);
        mName.setText(product.getName());
        mRating.setText(product.getRating());
        Glide.with(this).load(product.getImageUrl()).into(mImageView);
        mDescription.setText(product.getDescription());
        mAveragePrice.setText(product.getAveragePrice());
        mCreateBy.setText(product.getCreatedBy());
        mLastModifiedBy.setText(product.getLastModifiedBy());
        mCreateTime.setText(product.getCreateTime());
        mLastModifyTime.setText(product.getLastModifyTime());
        showTitles();
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mReviewsRecyclerView.setAdapter(new ReviewAdapter(product.getReviews()));
        mReviewsRecyclerView.setLayoutManager(mLayoutManager);
        mReviewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void showTitles() {
        mRatingTitle.setVisibility(View.VISIBLE);
        mAveragePriceTitle.setVisibility(View.VISIBLE);
        mCreateByTitle.setVisibility(View.VISIBLE);
        mLastModifiedByTitle.setVisibility(View.VISIBLE);
        mCreateTimeTitle.setVisibility(View.VISIBLE);
        mLastModifyTimeTitle.setVisibility(View.VISIBLE);
        mReviewsTitle.setVisibility(View.VISIBLE);
    }
}
