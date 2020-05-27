package com.example.trio;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button buttonLogIn;
    private  PreferenceLogger preferenceLogger;
    private PreferenceScore preferenceScore;
    private EditText userNameText;
    LeaderBoardSQL leaderBoardSQL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        leaderBoardSQL = new LeaderBoardSQL(getApplicationContext());
        preferenceLogger = new PreferenceLogger(getApplicationContext());
        preferenceScore = new PreferenceScore(getApplicationContext());
        userNameText = findViewById(R.id.inputNameText);
        buttonLogIn = findViewById(R.id.finishLoginButton);
        buttonLogIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Log.e("SET115",leaderBoardSQL.getAllData()+"");

                String name = userNameText.getText().toString();
                Log.e("SET116",leaderBoardSQL.checkName(name)+"");
                //Log.e("SET116",leaderBoardSQL.getScore(name)+"");
                if(name.isEmpty()){
                    userNameText.setHint("No empty here!");
                }else{
                    if(leaderBoardSQL.checkName(name)){
                        leaderBoardSQL.insertData(name,0);
                        preferenceLogger.setName(name);

                    }else{
                        preferenceLogger.setName(name);
                    }
                    preferenceScore.setValue(leaderBoardSQL.getScore(name));
                    Log.e("SET116",leaderBoardSQL.getScore(name)+"");
                    preferenceLogger.setValue(1);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
