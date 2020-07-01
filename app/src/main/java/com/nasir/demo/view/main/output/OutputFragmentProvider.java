package com.nasir.demo.view.main.output;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class OutputFragmentProvider {
    @ContributesAndroidInjector
    abstract OutputFragment provideOutputFragmentFactory();
}
