package com.example.findmatch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmatch.R;

public class MatchItemAdapter extends RecyclerView.Adapter<MatchItemAdapter.MyViewHolder> {

    String mSportTag[];
    String mSportLocation[];
    String mSportAddressLocation[];
    String mStatusPlay[];
    String mSpotAvailable[];
    Context mContext;

    public MatchItemAdapter(Context context, String sportTag[], String sportLocation[], String sportAddressLocation[], String statusPlay[], String spotAvailable[]) {
        mContext = context;
        mSportTag = sportTag;
        mSportLocation = sportLocation;
        mSportAddressLocation = sportAddressLocation;
        mStatusPlay = statusPlay;
        mSpotAvailable = spotAvailable;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.match_item_card_view, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView_sportTag.setText(mSportTag[position]);
        holder.textView_sportLocation.setText(mSportLocation[position]);
        holder.textView_sportAddressLocation.setText(mSportAddressLocation[position]);
        holder.textView_statusPlay.setText(mStatusPlay[position]);
        holder.textView_spotAvailable.setText(mSpotAvailable[position]);
    }

    @Override
    public int getItemCount() {
        return mSportTag.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_sportTag;
        TextView textView_sportLocation;
        TextView textView_sportAddressLocation;
        TextView textView_statusPlay;
        TextView textView_spotAvailable;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_sportTag = itemView.findViewById(R.id.textView_sportTag);
            textView_sportLocation = itemView.findViewById(R.id.textView_sportLocation);
            textView_sportAddressLocation = itemView.findViewById(R.id.textView_sportAddressLocation);
            textView_statusPlay = itemView.findViewById(R.id.textView_statusPlay);
            textView_spotAvailable = itemView.findViewById(R.id.textView_spotAvailable);
        }
    }
}
