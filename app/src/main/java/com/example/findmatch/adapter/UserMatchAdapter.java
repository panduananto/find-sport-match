package com.example.findmatch.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmatch.R;
import com.example.findmatch.model.UserMatchModel;

import java.util.List;

public class UserMatchAdapter extends RecyclerView.Adapter<UserMatchAdapter.UserMatchViewHolder> {

    public List<UserMatchModel> mUserMatchModel;
    private String statusPlay;

    public void setmUserMatchModel(List<UserMatchModel> mUserMatchModel) {
        this.mUserMatchModel = mUserMatchModel;
    }

    @NonNull
    @Override
    public UserMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.match_item_card_view, parent, false);
        UserMatchViewHolder matchViewHolder = new UserMatchViewHolder(view);

        return matchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserMatchViewHolder holder, int position) {
        holder.textView_sportTag.setText(mUserMatchModel.get(position).getSportTag());
        holder.textView_sportTitle.setText(mUserMatchModel.get(position).getSportTitle());
        holder.textView_sportAddressLocation.setText(mUserMatchModel.get(position).getSportAddressLocation());
        holder.textView_statusPlay.setText(mUserMatchModel.get(position).getStatusPlay());
        statusPlay = holder.textView_statusPlay.getText().toString();
        if (statusPlay.equalsIgnoreCase("SEDANG MAIN")) {
            holder.textView_statusPlay.setTextColor(Color.parseColor("#48bb78"));
        } else {
            holder.textView_statusPlay.setTextColor(Color.parseColor("#4299E1"));
        }
        holder.textView_currentPlayer.setText(mUserMatchModel.get(position).getCurrentPlayer());
        holder.textView_maxPlayer.setText(mUserMatchModel.get(position).getMaxPlayer());
    }

    @Override
    public int getItemCount() {
        if (mUserMatchModel == null) {
            return 0;
        } else {
            return mUserMatchModel.size();
        }
    }

    public class UserMatchViewHolder extends RecyclerView.ViewHolder {

        TextView textView_sportTag;
        TextView textView_sportTitle;
        TextView textView_sportAddressLocation;
        TextView textView_statusPlay;
        TextView textView_currentPlayer;
        TextView textView_maxPlayer;

        public UserMatchViewHolder(@NonNull View itemView) {
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
