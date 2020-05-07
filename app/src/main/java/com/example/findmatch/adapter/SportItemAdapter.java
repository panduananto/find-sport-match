package com.example.findmatch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmatch.R;

public class SportItemAdapter extends RecyclerView.Adapter<SportItemAdapter.MyViewHolder> {

    String mSportName[];
    int mSportImage[];
    Context mContext;

    //constructor
    public SportItemAdapter(Context context, String sportName[], int sportImage[]) {
        mContext = context;
        mSportName = sportName;
        mSportImage = sportImage;
    }

    //inisiasi viewholder untuk menentukan view mana yang digunakan untuk menampilkan data
    //data yang akan ditampilkan adalah string-array sportName menuju ke textView_sportName
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_sportName;
        ImageView imageView_sportImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_sportName = itemView.findViewById(R.id.textView_sportName);
            imageView_sportImage = itemView.findViewById(R.id.imageView_sportImage);
        }
    }

    //panggil view yang akan digunakan sebagai penampung item list
    //view nya adalah berupa CardView yang terdapat pada file layout item_card_view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_card_view, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    //untuk mendapatkan informasi index dari data yang akan ditampilkan
    //karena data berbentuk string-array
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView_sportName.setText(mSportName[position]);
        holder.imageView_sportImage.setImageResource(mSportImage[position]);
    }

    //untuk mendapatkan informasi jumlah data yang akan ditampilkan
    @Override
    public int getItemCount() {
        return mSportName.length;
        /*return mSportImage.length;*/
    }
}
