package com.example.productreview.di;

import com.example.productreview.fragments.ProductsListFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class AppModule {

    @Provides
    @Singleton
    public ProductsListFragment provideProductsListFragment() {
        return new ProductsListFragment();
    }
}
