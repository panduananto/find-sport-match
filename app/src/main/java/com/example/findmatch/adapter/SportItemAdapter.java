package com.example.findmatch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findmatch.R;
import com.example.findmatch.model.SportItemModel;

import java.util.List;

public class SportItemAdapter extends RecyclerView.Adapter<SportItemAdapter.SportItemViewHolder> {

    private List<SportItemModel> mSportItemModel;

    public void setmSportItemModel(List<SportItemModel> mSportItemModel) {
        this.mSportItemModel = mSportItemModel;
    }

    @NonNull
    @Override
    public SportItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_view, parent, false);
        SportItemViewHolder sportViewHolder = new SportItemViewHolder(view);

        return sportViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SportItemViewHolder holder, int position) {
        holder.textView_sportName.setText(mSportItemModel.get(position).getSportName());

        String imageUri = mSportItemModel.get(position).getSportImage();
        Glide.with(holder.itemView.getContext())
                .load(imageUri)
                .centerCrop()
                .placeholder(R.drawable.card_shape)
                .into(holder.imageView_sportImage);
    }

    @Override
    public int getItemCount() {
        if (mSportItemModel == null) {
            return 0;
        } else {
            return mSportItemModel.size();
        }
    }

    public class SportItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView_sportName;
        ImageView imageView_sportImage;

        public SportItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_sportName = itemView.findViewById(R.id.textView_sportName);
            imageView_sportImage = itemView.findViewById(R.id.imageView_sportImage);
        }
    }
}
