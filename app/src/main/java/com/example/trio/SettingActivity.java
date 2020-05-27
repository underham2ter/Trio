package com.example.trio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    private PreferenceLogger preferenceLogger;
    SharedPreferences sharedPreferences;
    private Button buttonLogOut, buttonGoBack, buttonDelete;
    private LeaderBoardSQL leaderBoardSQL;
    SeekBar seekBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        leaderBoardSQL = new LeaderBoardSQL(getApplicationContext());
        preferenceLogger = new PreferenceLogger(getApplicationContext());
        buttonDelete = findViewById(R.id.deleteButton);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaderBoardSQL.deleteData(preferenceLogger.getName());
                preferenceLogger.setValue(0);
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonGoBack = findViewById(R.id.backButton2);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonLogOut = findViewById(R.id.logOutButton);
        buttonLogOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                preferenceLogger.setValue(0);
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                sharedPreferences = getSharedPreferences("BASE", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("value",seekBar.getProgress());
                editor.apply();
            }
            public void  loadSettings(){
                sharedPreferences = getSharedPreferences("BASE",getApplicationContext().MODE_PRIVATE);
                if(sharedPreferences != null){
                    int val = sharedPreferences.getInt("value",2);
                    seekBar.setProgress(val);
                }

            }
        });
    }
}
