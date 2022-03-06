package com.example.hmaservtask.ui.fragments;

import static com.google.gson.reflect.TypeToken.get;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hmaservtask.R;
import com.example.hmaservtask.adapter.CategoryAdapter;
import com.example.hmaservtask.databinding.FragmentCategoryBinding;
import com.example.hmaservtask.model.Category;
import com.example.hmaservtask.model.CategoryState;
import com.example.hmaservtask.utils.Utils;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Delay;

@AndroidEntryPoint
public class CategoryFragment extends Fragment implements CategoryAdapter.OnCategoryClickListener {
    private FragmentCategoryBinding binding;
    private CategoryViewModel categoryViewModel;
    private CategoryAdapter adapter;
    private NavController navController;
    private CategoryFragmentDirections.ActionCategoryFragmentToStreamFragment action;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        navController = Navigation.findNavController(requireActivity(), R.id.main_nav_host);
        adapter = new CategoryAdapter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initCategoryList();
        loadingCategoryState();
        categoryViewModel.getLiveCategory(Utils.USER_NAME, Utils.PASSWORD, Utils.ACTION_CATEGORIES)
        .observe(getViewLifecycleOwner(), new Observer<CategoryState>() {
            @Override
            public void onChanged(CategoryState categoryState) {
                if (categoryState.getStatus() == CategoryState.Status.SUCCESS){
                    binding.categoriesRv.setVisibility(View.VISIBLE);
                    binding.categoryErrorText.setVisibility(View.GONE);
                    binding.categoryLoadingBar.setVisibility(View.GONE);
                    adapter.setNewList(categoryState.getCategoryList());
                }
                else if (categoryState.getStatus() == CategoryState.Status.ERROR){
                    binding.categoriesRv.setVisibility(View.GONE);
                    binding.categoryErrorText.setVisibility(View.VISIBLE);
                    binding.categoryLoadingBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadingCategoryState() {
        binding.categoriesRv.setVisibility(View.GONE);
        binding.categoryErrorText.setVisibility(View.GONE);
        binding.categoryLoadingBar.setVisibility(View.VISIBLE);
    }

    private void initCategoryList() {
        binding.categoriesRv.setAdapter(adapter);
        binding.categoriesRv.setHasFixedSize(true);
        binding.categoriesRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    @Override
    public void onCategoryClick(int categoryId) {
        action =
                CategoryFragmentDirections.actionCategoryFragmentToStreamFragment(categoryId);
        navController.navigate(action);
    }
}
