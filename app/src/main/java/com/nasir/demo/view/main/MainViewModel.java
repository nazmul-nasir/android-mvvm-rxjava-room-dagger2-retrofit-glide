package com.nasir.demo.view.main;

import com.nasir.demo.data.DataManager;
import com.nasir.demo.utils.rx.SchedulerProvider;
import com.nasir.demo.view.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {
    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
