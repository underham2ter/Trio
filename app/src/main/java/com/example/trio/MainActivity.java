package com.example.trio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private PreferenceLogger preferenceLogger;
    private PreferenceScore preferenceScore;
    private Fragment fragment,fragment2, fragment3;
    private FragmentManager fragmentManager, fragmentManager2, fragmentManager3;
    private FragmentTransaction fragmentTransaction, fragmentTransaction2, fragmentTransaction3;
    private FloatingActionButton buttonMenuStart;
    private Button buttonSettings;
    private Button buttonLeaderBoard;
    private TextView scoreView, nameView;
    private RegisterFragment registerFragment;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu);
        preferenceScore = new PreferenceScore(getApplicationContext());
        preferenceLogger = new PreferenceLogger(getApplicationContext());
        buttonLeaderBoard = findViewById(R.id.leaderBoard);
        scoreView = findViewById(R.id.scoreView);
        buttonSettings = findViewById(R.id.settings);
        buttonMenuStart = findViewById(R.id.startButton);
        scoreView.setText("Best score: " + preferenceScore.getValue());

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        buttonLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
                startActivity(intent);
            }
        });
        buttonMenuStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameProcess.class);
                startActivity(intent);
            }
        });

        if(preferenceLogger.getValue()==1) {

        }
        if(preferenceLogger.getValue()==0){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }



    }
//    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Do you want to exist?")
//                .setCancelable(false)
//                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent startMain = new Intent(Intent.ACTION_MAIN);
//                        startMain.addCategory(Intent.CATEGORY_HOME);
//                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(startMain);
//                        finish();
//                    }
//                })
//                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
    public void addFragmentRegister(){
        fragment = new RegisterFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.startMenu,fragment);
        fragmentTransaction.commit();
    }

    public void addFragmentLogOut(){
        fragment3 = new LogOutFragment();
        fragmentManager3 = getSupportFragmentManager();
        fragmentTransaction3 = fragmentManager3.beginTransaction();
        fragmentTransaction3.add(R.id.startMenu,fragment3);
        fragmentTransaction3.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
