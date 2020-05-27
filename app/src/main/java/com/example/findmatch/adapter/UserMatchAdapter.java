package com.example.findmatch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmatch.R;
import com.example.findmatch.model.MatchItemModel;

import java.util.List;

public class MatchItemAdapter extends RecyclerView.Adapter<MatchItemAdapter.SportItemViewHolder> {

    public List<MatchItemModel> mMatchItemModel;

    public void setmMatchItemModel(List<MatchItemModel> mMatchItemModel) {
        this.mMatchItemModel = mMatchItemModel;
    }

    @NonNull
    @Override
    public SportItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.match_item_card_view, parent, false);
        SportItemViewHolder matchViewHolder = new SportItemViewHolder(view);

        return matchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SportItemViewHolder holder, int position) {
        holder.textView_sportTag.setText(mMatchItemModel.get(position).getSportTag());
        holder.textView_sportTitle.setText(mMatchItemModel.get(position).getSportTitle());
        holder.textView_sportAddressLocation.setText(mMatchItemModel.get(position).getSportAddressLocation());
        holder.textView_statusPlay.setText(mMatchItemModel.get(position).getStatusPlay());
        holder.textView_currentPlayer.setText(mMatchItemModel.get(position).getCurrentPlayer());
        holder.textView_maxPlayer.setText(mMatchItemModel.get(position).getMaxPlayer());
    }

    @Override
    public int getItemCount() {
        if (mMatchItemModel == null) {
            return 0;
        } else {
            return mMatchItemModel.size();
        }
    }

    public class SportItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView_sportTag;
        TextView textView_sportTitle;
        TextView textView_sportAddressLocation;
        TextView textView_statusPlay;
        TextView textView_currentPlayer;
        TextView textView_maxPlayer;

        public SportItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_sportTag = itemView.findViewById(R.id.textView_sportTagOther);
            textView_sportTitle = itemView.findViewById(R.id.textView_sportTitleOther);
            textView_sportAddressLocation = itemView.findViewById(R.id.textView_sportAddressLocationOther);
            textView_statusPlay = itemView.findViewById(R.id.textView_statusPlayOther);
            textView_currentPlayer = itemView.findViewById(R.id.textView_currentPlayerOther);
            textView_maxPlayer = itemView.findViewById(R.id.textView_maxPlayerOther);
        }
    }
}
