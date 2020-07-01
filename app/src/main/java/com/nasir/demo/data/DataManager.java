package com.nasir.demo.data;

import com.nasir.demo.data.local.db.DbHelper;
import com.nasir.demo.data.local.pref.PreferencesHelper;
import com.nasir.demo.data.model.PicsumResponse;
import com.nasir.demo.data.model.Url;
import com.nasir.demo.data.remote.ApiHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {
    Observable<List<Url>> getAllUrls();

    Observable<Boolean> saveUrl(Url url);

    Observable<Boolean> deleteUrl(Url url);

    Observable<Boolean> saveUrlList(List<Url> urls);

    Single<List<PicsumResponse>> getServerImageApiCall(int page, int limit);
}
