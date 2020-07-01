package com.nasir.demo;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nasir.demo.data.DataManager;
import com.nasir.demo.utils.rx.SchedulerProvider;
import com.nasir.demo.view.main.input.InputViewModel;
import com.nasir.demo.view.main.MainViewModel;
import com.nasir.demo.view.main.output.OutputViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(InputViewModel.class)) {
            //noinspection unchecked
            return (T) new InputViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OutputViewModel.class)) {
            //noinspection unchecked
            return (T) new OutputViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}