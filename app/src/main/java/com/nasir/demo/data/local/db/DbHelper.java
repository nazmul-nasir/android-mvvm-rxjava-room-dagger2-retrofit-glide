package com.nasir.demo.data.local.db;

import com.nasir.demo.data.model.Url;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {
    Observable<List<Url>> getAllUrls();

    Observable<Boolean> saveUrl(final Url url);

    Observable<Boolean> deleteUrl(final Url url);

    Observable<Boolean> saveUrlList(List<Url> urls);
}
