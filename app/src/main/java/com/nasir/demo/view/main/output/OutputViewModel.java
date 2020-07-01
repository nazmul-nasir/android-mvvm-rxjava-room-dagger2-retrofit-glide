package com.nasir.demo.view.main.output;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nasir.demo.R;
import com.nasir.demo.data.DataManager;
import com.nasir.demo.data.model.Url;
import com.nasir.demo.utils.rx.SchedulerProvider;
import com.nasir.demo.view.base.BaseViewModel;
import com.nasir.demo.view.main.output.adapter.OutputAdapter;

import java.util.List;

import javax.inject.Inject;

public class OutputViewModel extends BaseViewModel<OutputNavigator> {

    public MutableLiveData<String> selected;
    private MutableLiveData<List<Url>> urlsLiveData;
    private OutputAdapter outputAdapter;

    public OutputViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        urlsLiveData = new MutableLiveData<>();
        fetchUrlFromDb();
    }
    @Inject
    public void init() {
        outputAdapter = new OutputAdapter(R.layout.view_output_list, this);
        selected = new MutableLiveData<>();
    }
    public OutputAdapter getAdapter() {
        return outputAdapter;
    }
    public void updateOutputAdapter() {
        this.outputAdapter.setUrls(urlsLiveData.getValue());
        this.outputAdapter.notifyDataSetChanged();
    }
    public Url getUrlAt(Integer index) {
        if (urlsLiveData != null &&
                index != null &&
                urlsLiveData.getValue().size() > index) {
            return urlsLiveData.getValue().get(index);
        }
        return null;
    }
    private void fetchUrlFromDb() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllUrls()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(urls -> {
                    if (urls != null && !urls.isEmpty()) {
                        urlsLiveData.setValue(urls);
                        updateOutputAdapter();
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().showMessage(throwable.getMessage());
                }));
    }

    public void deleteClicked(Integer index) {
        if (urlsLiveData != null &&
                index != null &&
                urlsLiveData.getValue().size() > index) {
            getCompositeDisposable().add(getDataManager()
                    .deleteUrl(getUrlAt(index))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(urls -> {
                        urlsLiveData.getValue().remove((int) index);
                        updateOutputAdapter();
                        setIsLoading(false);
                    }, throwable -> {
                        setIsLoading(false);
                        getNavigator().showMessage(throwable.getMessage());
                    }));
        }
    }

    public LiveData<List<Url>> getUrlListLiveData() {
        return urlsLiveData;
    }
}
