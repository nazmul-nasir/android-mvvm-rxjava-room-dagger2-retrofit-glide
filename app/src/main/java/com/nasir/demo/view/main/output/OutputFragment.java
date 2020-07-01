package com.nasir.demo.view.main.output;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.nasir.demo.BR;
import com.nasir.demo.R;
import com.nasir.demo.ViewModelProviderFactory;
import com.nasir.demo.databinding.FragmentOutputBinding;
import com.nasir.demo.view.base.BaseFragment;

import javax.inject.Inject;

public class OutputFragment extends BaseFragment<FragmentOutputBinding, OutputViewModel> implements OutputNavigator {
    public static final String TAG = OutputFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private OutputViewModel outputViewModel;

    public static OutputFragment newInstance() {
        Bundle args = new Bundle();
        OutputFragment fragment = new OutputFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_output;
    }

    @Override
    public OutputViewModel getViewModel() {
        outputViewModel = ViewModelProviders.of(this, factory).get(OutputViewModel.class);
        return outputViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        outputViewModel.setNavigator(this);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        outputViewModel.init();
    }
    @Override
    public void showMessage(String message) {
        hideKeyboard();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
