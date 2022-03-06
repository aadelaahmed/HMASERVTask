package com.example.hmaservtask.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hmaservtask.databinding.StreamItemBinding;
import com.example.hmaservtask.model.Stream;

import java.util.ArrayList;
import java.util.List;

public class StreamAdapter extends RecyclerView.Adapter<StreamViewHolder> {
    StreamItemBinding binding;
    List<Stream> streamList = new ArrayList<>();
    Stream currentStream;
    LayoutInflater inflater;

    @NonNull
    @Override
    public StreamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        binding = StreamItemBinding.inflate(inflater, parent, false);
        return new StreamViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamViewHolder holder, int position) {
        currentStream = streamList.get(position);
        holder.bind(currentStream);
    }

    public void setNewList(List<Stream> newList) {
        this.streamList = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return streamList.size();
    }

    public void clearList() {
        streamList.clear();
        notifyDataSetChanged();
    }
}
