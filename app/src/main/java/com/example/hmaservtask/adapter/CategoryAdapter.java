package com.example.hmaservtask.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hmaservtask.databinding.CategoryItemBinding;
import com.example.hmaservtask.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private CategoryItemBinding binding;
    private List<Category> categoryList = new ArrayList<>();
    private Category currentCategory;
    private OnCategoryClickListener onCategoryClickListener;
    public CategoryAdapter(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CategoryItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding, onCategoryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        currentCategory = categoryList.get(position);
        holder.bind(currentCategory);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setNewList(List<Category> newList) {
        this.categoryList = newList;
        notifyDataSetChanged();
    }

    public interface OnCategoryClickListener{
        void onCategoryClick(int categoryId);
    }
}


