package com.nasir.demo.data;

import android.content.Context;

import com.google.gson.Gson;
import com.nasir.demo.data.local.db.DbHelper;
import com.nasir.demo.data.local.pref.PreferencesHelper;
import com.nasir.demo.data.model.PicsumResponse;
import com.nasir.demo.data.model.Url;
import com.nasir.demo.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";
    private final Context mContext;
    private final DbHelper mDbHelper;
    private final Gson mGson;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(Context context, DbHelper mDbHelper, PreferencesHelper preferencesHelper, Gson gson, ApiHelper mApiHelper) {
        mContext = context;
        this.mDbHelper = mDbHelper;
        mPreferencesHelper = preferencesHelper;
        mGson = gson;
        this.mApiHelper = mApiHelper;
    }

    @Override
    public Observable<List<Url>> getAllUrls() {
        return mDbHelper.getAllUrls();
    }

    @Override
    public Observable<Boolean> saveUrl(Url url) {
        return mDbHelper.saveUrl(url);
    }

    @Override
    public Observable<Boolean> deleteUrl(Url url) {
        return mDbHelper.deleteUrl(url);
    }

    @Override
    public Observable<Boolean> saveUrlList(List<Url> urls) {
        return mDbHelper.saveUrlList(urls);
    }

    @Override
    public Single<List<PicsumResponse>> getServerImageApiCall(int page, int limit) {
        return mApiHelper.getServerImageApiCall(page, limit);
    }
}
