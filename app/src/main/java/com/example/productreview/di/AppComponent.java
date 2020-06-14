package com.example.productreview.di;

import android.app.Application;

import com.example.productreview.MainActivity;
import com.example.productreview.activity.ProductActivity;
import com.example.productreview.activity.TakePhotoActivity;
import com.example.productreview.fragments.ProductsListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(TakePhotoActivity activity);
    void inject(ProductsListFragment fragment);
    void inject(Application application);
    void inject(ProductActivity productActivity);
}
