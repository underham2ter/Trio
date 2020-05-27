package com.example.trio;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList player_id, player_name, player_score;

    CustomAdapter(Activity activity, Context context, ArrayList player_id, ArrayList player_name, ArrayList player_score){
        this.activity = activity;
        this.context = context;
        this.player_id = player_id;
        this.player_name = player_name;
        this.player_score = player_score;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(player_id.get(position)));
        holder.name_txt.setText(String.valueOf(player_name.get(position)));
        holder.score_txt.setText(String.valueOf(player_score.get(position)));
    }

    @Override
    public int getItemCount() {
        return player_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, name_txt, score_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            score_txt = itemView.findViewById(R.id.score_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

    }

}
