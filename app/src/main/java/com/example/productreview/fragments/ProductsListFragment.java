package com.example.productreview.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.productreview.R;
import com.example.productreview.adapters.ProductAdapter;
import com.example.productreview.constants.ProductListState;
import com.example.productreview.di.ContextModule;
import com.example.productreview.di.DaggerAppComponent;
import com.example.productreview.models.Product;
import com.example.productreview.network.GateApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecycleView;
    @BindView(R.id.searchProductView)
    SearchView mSearchView;
    @BindView(R.id.loadingProductsPanel)
    ProgressBar productsLoadingProgressBar;
    @Inject
    GateApiService gateApiService;
    @Inject
    ProductsListFragment productsListFragment;
    LinearLayoutManager mLayoutManager;

    private ProductListState state = ProductListState.ALL;

    @Override
    public void onAttach(Context context) {
        DaggerAppComponent.builder()
                .contextModule(new ContextModule(getContext()))
                .build()
                .inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.products_fragment, container, false);
        ButterKnife.bind(this, view);
        fetchAllProducts();
        return view;
    }

    public void fetchAllProducts() {
        state = ProductListState.ALL;
        gateApiService
                .fetchAllProducts()
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        setUp(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        setUp(new ArrayList<Product>());
                    }
                });
    }

    public void fetchProductsByCategoryAlias(String alias) {
        state = ProductListState.of(alias);
        gateApiService
                .fetchProductByCategoryAlias(alias)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        setUp(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        setUp(new ArrayList<Product>());
                    }
                });
    }


    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
    }

    public ProductListState getState() {
        return this.state;
    }

    private void setUp(List<Product> products) {
        productsLoadingProgressBar.setVisibility(View.GONE);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycleView.setAdapter(new ProductAdapter(products));
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
    }
}
