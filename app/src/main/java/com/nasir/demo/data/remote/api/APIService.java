package com.nasir.demo.data.remote.api;

import com.nasir.demo.data.model.PicsumResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {
    @Headers({"Content-Type: application/json"})
    @GET("v2/list/")
    Single<List<PicsumResponse>> getImagesFromServer(@Query("page") int page, @Query("limit") int limit);
}
