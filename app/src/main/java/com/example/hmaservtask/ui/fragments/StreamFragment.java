package com.example.hmaservtask.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hmaservtask.R;
import com.example.hmaservtask.adapter.StreamAdapter;
import com.example.hmaservtask.databinding.FragmentCategoryBinding;
import com.example.hmaservtask.databinding.FragmentStreamBinding;
import com.example.hmaservtask.model.CategoryState;
import com.example.hmaservtask.model.StreamState;
import com.example.hmaservtask.utils.Utils;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class StreamFragment extends Fragment {
    private StreamViewModel streamViewModel;
    private FragmentStreamBinding binding;
    private StreamAdapter adapter;
    private int categoryId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStreamBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        streamViewModel = new ViewModelProvider(requireActivity())
                .get(StreamViewModel.class);
        categoryId = StreamFragmentArgs.fromBundle(getArguments()).getCategoryId();
        initStreamRecyclerView();
        loadingStreamState();
        streamViewModel.getLiveStream(
                Utils.USER_NAME,
                Utils.PASSWORD,
                Utils.ACTION_STREAMS,
                categoryId
        ).observe(getViewLifecycleOwner(), new Observer<StreamState>() {
            @Override
            public void onChanged(StreamState streamState) {
                if (streamState.getStatus() == StreamState.Status.SUCCESS) {
                    binding.streamsRv.setVisibility(View.VISIBLE);
                    binding.streamErrorText.setVisibility(View.GONE);
                    binding.streamLoadingBar.setVisibility(View.GONE);
                    adapter.setNewList(streamState.getStreamList());
                } else if (streamState.getStatus() == StreamState.Status.ERROR) {
                    binding.streamsRv.setVisibility(View.GONE);
                    binding.streamErrorText.setVisibility(View.VISIBLE);
                    binding.streamLoadingBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadingStreamState() {
        binding.streamsRv.setVisibility(View.GONE);
        binding.streamErrorText.setVisibility(View.GONE);
        binding.streamLoadingBar.setVisibility(View.VISIBLE);
    }

    private void initStreamRecyclerView() {
        adapter = new StreamAdapter();
        binding.streamsRv.setHasFixedSize(true);
        binding.streamsRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.streamsRv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter.clearList();
    }
}

