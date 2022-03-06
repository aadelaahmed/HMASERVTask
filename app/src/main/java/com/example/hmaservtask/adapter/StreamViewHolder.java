package com.example.hmaservtask.adapter;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.hmaservtask.databinding.StreamItemBinding;
import com.example.hmaservtask.model.Category;
import com.example.hmaservtask.model.Stream;

public class StreamViewHolder extends RecyclerView.ViewHolder {
    StreamItemBinding binding;

    public StreamViewHolder(StreamItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Stream stream) {
        binding.streamId.setText(String.valueOf(stream.getStreamId()));
        binding.streamName.setText(stream.getName());
        Glide.with(binding.getRoot())
                .load(stream.getStreamIcon())
                .into(binding.streamImage);
    }
}
