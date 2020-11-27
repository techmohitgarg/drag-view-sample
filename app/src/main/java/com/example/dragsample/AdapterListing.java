package com.example.dragsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterListing extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int[] listModals;

    AdapterListing(int[] imagesList) {
        this.listModals = imagesList;
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        private ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_test_layout, parent, false);
        final ImageViewHolder holder = new ImageViewHolder(view);
        final View shape = holder.imageView;
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final int item = listModals[holder.getAdapterPosition()];
                final DragData state = new DragData(item, shape.getWidth(), shape.getHeight());
                final View.DragShadowBuilder shadow = new View.DragShadowBuilder(shape);
                ViewCompat.startDragAndDrop(shape, null, shadow, state, 0);
                return true;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
        int list = listModals[position];
        imageViewHolder.imageView.setImageResource(list);

    }

    @Override
    public int getItemCount() {
        return listModals.length;
    }
}