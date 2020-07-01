package com.nasir.demo.data.local.db;

import com.nasir.demo.data.model.Url;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }
    @Override
    public Observable<List<Url>> getAllUrls() {
        return mAppDatabase.urlDao().loadAll().toObservable();
    }

    @Override
    public Observable<Boolean> saveUrl(Url url) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.urlDao().insert(url);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteUrl(Url url) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.urlDao().delete(url);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveUrlList(List<Url> urls) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.urlDao().insertAll(urls);
                return true;
            }
        });
    }
}
