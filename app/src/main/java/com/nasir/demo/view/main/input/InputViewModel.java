package com.nasir.demo.view.main.input;

import android.os.Build;
import android.util.Patterns;
import android.webkit.URLUtil;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.nasir.demo.R;
import com.nasir.demo.data.DataManager;
import com.nasir.demo.data.model.PicsumResponse;
import com.nasir.demo.data.model.Url;
import com.nasir.demo.utils.AppConstants;
import com.nasir.demo.utils.CommonUtils;
import com.nasir.demo.utils.rx.SchedulerProvider;
import com.nasir.demo.view.base.BaseViewModel;
import com.nasir.demo.view.main.input.adapter.InputUrlAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class InputViewModel extends BaseViewModel<InputNavigator> {
    public MutableLiveData<String> url = new MutableLiveData<>();
    public MutableLiveData<String> selected;
    List<String> urlList = new ArrayList<>();
    private InputUrlAdapter inputUrlAdapter;

    public InputViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void init() {
        inputUrlAdapter = new InputUrlAdapter(R.layout.view_input_list, this);
        selected = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getUrls() {
        return (MutableLiveData<List<String>>) urlList;
    }

    public InputUrlAdapter getAdapter() {
        return inputUrlAdapter;
    }

    public void updateUrlInAdapter() {
        this.inputUrlAdapter.setUrls(urlList);
        this.inputUrlAdapter.notifyDataSetChanged();
    }

    public void fetchUrlAt(Integer position) {

    }

    public MutableLiveData<String> getSelected() {
        return selected;
    }

    public void onItemClick(Integer index) {
        String db = getUrlAt(index);
        selected.setValue(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void urlAddClicked() {
        String toAddUrl = url.getValue();
        assert toAddUrl != null;
        if (URLUtil.isValidUrl(toAddUrl) && Patterns.WEB_URL.matcher(toAddUrl).matches()) {
            if (CommonUtils.isYoutubeUrl(toAddUrl)) {
                String youtubeVideoId = CommonUtils.getYoutubeIdFromUrl(toAddUrl);
                urlAddToList(CommonUtils.getYoutubeVideoThumbnailFromId(youtubeVideoId));
            } else {
                urlAddToList(toAddUrl);
            }
        } else {
            getNavigator().showMessage("Please Enter Valid URL");
        }
    }
    public void urlAddToList(String urlToAdd) {
        urlList.add(urlToAdd);
        url.setValue("");
        updateUrlInAdapter();
        getNavigator().showMessage("Url has been added to the List");
    }
    public void saveClicked() {
        if (urlList.isEmpty()) {
            getNavigator().showMessage("Please Add Url First");
            return;
        }
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .saveUrlList(getUrlList())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        getNavigator().showMessage("Successfully Saved");
                        urlList.clear();
                        setIsLoading(false);
                        updateUrlInAdapter();
                    } else {
                        setIsLoading(true);
                        getNavigator().showMessage("Failed to Save");
                    }
                }, throwable -> {
                    getNavigator().showMessage(throwable.getMessage());
                }));
    }

    private List<Url> getUrlList() {
        List<Url> urlObjectList = new ArrayList<>();

        for (String url : urlList) {
            urlObjectList.add(new Url(url));
        }
        return urlObjectList;
    }

    public String getUrlAt(Integer index) {
        if (urlList != null &&
                index != null &&
                urlList.size() > index) {
            return urlList.get(index);
        }
        return null;
    }

    public void deleteClicked(Integer index) {
        if (urlList != null &&
                index != null &&
                urlList.size() > index) {
            urlList.remove((int) index);
            updateUrlInAdapter();
        }
    }

    public void fetchFromServerClicked() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getServerImageApiCall(CommonUtils.getRandomPageNumber(), AppConstants.PHOTO_LIMIT_FROM_SERVER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                        subscribeWith(new DisposableSingleObserver<List<PicsumResponse>>() {

                            @Override
                            public void onSuccess(List<PicsumResponse> responseList) {
                                updateUrlListWithServerData(responseList);
                                setIsLoading(false);
                                updateUrlInAdapter();
                            }
                            @Override
                            public void onError(Throwable throwable) {
                                setIsLoading(false);
                                getNavigator().showMessage(throwable.getMessage());
                            }
                        }));
    }

    private void updateUrlListWithServerData(List<PicsumResponse> urls) {
        for (PicsumResponse url : urls
        ) {
            urlList.add(url.getDownloadUrl());
        }
    }
}
