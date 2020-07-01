package com.nasir.demo.di.component;

import android.app.Application;

import com.nasir.demo.App;
import com.nasir.demo.data.remote.api.RetrofitApiClient;
import com.nasir.demo.di.builder.ActivityBuilder;
import com.nasir.demo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class, RetrofitApiClient.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
