package com.example.productreview.network;

import com.example.productreview.models.Product;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface GateApiService {

    String SERVICE_ENDPOINT = "http://192.168.31.142:8082";

    @Multipart
    @POST("/api/v1/gate/product-determination/predict/collection/{modelName}")
    Call<List<Product>> determinateProducts(@Part MultipartBody.Part file, @Path("modelName") String modelName);

    @Multipart
    @POST("/api/v1/gate/product-determination/predict/single/{modelName}")
    Call<Product> determinateProduct(@Part MultipartBody.Part file, @Path("modelName") String modelName);

    @GET("/api/v1/gate/product-info/products")
    Call<List<Product>> fetchAllProducts();

    @GET("/api/v1/gate/product-info/products/{id}")
    Call<Product> fetchProductById(@Path("id") Long id);

    @GET("/api/v1/gate/product-info/products/category/{categoryName}")
    Call<List<Product>> fetchProductByCategoryName(@Path("categoryName") String categoryName);

    @GET("/api/v1/gate/product-info/products/category/alias/{categoryAlias}")
    Call<List<Product>> fetchProductByCategoryAlias(@Path("categoryAlias") String categoryAlias);

}
