package com.example.trio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> player_id, player_name;
    ArrayList<Integer> player_score;
    CustomAdapter customAdapter;
    LeaderBoardSQL leaderBoardSQL;
    private Button goBackButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_fragment);
        leaderBoardSQL = new LeaderBoardSQL(getApplicationContext());
        recyclerView = findViewById(R.id.recuclerView);
        player_id = new ArrayList<>();
        player_name = new ArrayList<>();
        player_score = new ArrayList<>();
        goBackButton = findViewById(R.id.backButton);
        storeDataInArrays();
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        customAdapter = new CustomAdapter(HighScoreActivity.this,this, player_id, player_name, player_score);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HighScoreActivity.this));
    }
    void storeDataInArrays(){
        Cursor cursor = leaderBoardSQL.getAllData();
        int n = 1;
            while (cursor.moveToNext()){
                //player_id.add(cursor.getString(0));
                player_id.add(n+"");
                n++;
                player_name.add(cursor.getString(1));
                player_score.add(cursor.getInt(2));
        }
    }
}
