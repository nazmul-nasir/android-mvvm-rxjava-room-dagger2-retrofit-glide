package com.nasir.demo.di.builder;

import com.nasir.demo.view.main.input.InputFragmentProvider;
import com.nasir.demo.view.main.MainActivity;
import com.nasir.demo.view.main.output.OutputFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {
            InputFragmentProvider.class,
            OutputFragmentProvider.class})
    abstract MainActivity bindMainActivity();
}
