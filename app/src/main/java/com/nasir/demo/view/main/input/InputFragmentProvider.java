package com.nasir.demo.view.main.input;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InputFragmentProvider {
    @ContributesAndroidInjector
    abstract InputFragment provideInputFragmentFactory();
}
