package com.cesaraguirre.movies.di;

import com.cesaraguirre.movies.presentation.application.MyApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ViewModelModule.class,
        AppModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        DataComponent.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
    }
}
