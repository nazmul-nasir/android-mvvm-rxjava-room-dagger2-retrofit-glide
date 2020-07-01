package com.nasir.demo.data.remote;

import com.nasir.demo.data.model.PicsumResponse;
import com.nasir.demo.data.remote.api.APIService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {
    private final APIService apiService;

    @Inject
    public AppApiHelper(APIService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<PicsumResponse>> getServerImageApiCall(int page, int limit) {
        return apiService.getImagesFromServer(page, limit);
    }
}
