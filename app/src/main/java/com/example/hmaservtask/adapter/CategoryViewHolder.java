package com.example.hmaservtask.adapter;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hmaservtask.databinding.CategoryItemBinding;
import com.example.hmaservtask.databinding.FragmentCategoryBinding;
import com.example.hmaservtask.model.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    CategoryItemBinding binding;
    CategoryAdapter.OnCategoryClickListener onCategoryClickListener;
    Category currentCategory;
    public CategoryViewHolder(CategoryItemBinding binding, CategoryAdapter.OnCategoryClickListener onCategoryClickListener) {
        super(binding.getRoot());
        binding.getRoot().setOnClickListener(this);
        this.binding = binding;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public void bind(Category category) {
        binding.categoryName.setText(category.getCategoryName());
        this.currentCategory = category;
    }

    @Override
    public void onClick(View view) {
        onCategoryClickListener.onCategoryClick(currentCategory.getCategoryId());
    }
}
