package com.nasir.demo.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nasir.demo.data.AppDataManager;
import com.nasir.demo.data.DataManager;
import com.nasir.demo.data.local.db.AppDatabase;
import com.nasir.demo.data.local.db.AppDbHelper;
import com.nasir.demo.data.local.db.DbHelper;
import com.nasir.demo.data.local.pref.AppPreferencesHelper;
import com.nasir.demo.data.local.pref.PreferencesHelper;
import com.nasir.demo.data.remote.ApiHelper;
import com.nasir.demo.data.remote.AppApiHelper;
import com.nasir.demo.di.DatabaseInfo;
import com.nasir.demo.di.PreferenceInfo;
import com.nasir.demo.utils.AppConstants;
import com.nasir.demo.utils.rx.AppSchedulerProvider;
import com.nasir.demo.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return "Demo-App";
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
