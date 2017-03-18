package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;
import calculotprototype.g14.cmpt276.calculot_prototype.R;
import calculotprototype.g14.cmpt276.calculot_prototype.WhatToDo;

public class GameWinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_win);

        int xpgained = getIntent().getIntExtra("xp",0);

        TextView xpfield = (TextView)findViewById(R.id.gamewin_xpfield);
        TextView tryAgain = (TextView)findViewById(R.id.gameWin_TryAgain);
        TextView goBack = (TextView)findViewById(R.id.gameWin_goToMainMenu);
        xpfield.setText("You have achieved: " + Integer.toString(xpgained) + " XP for your reward");


        SharedPreferences pref = getSharedPreferences("MyPref",MODE_PRIVATE);
        String myusername = pref.getString("username","notmyusername");

        // Open database and store the XP into the database where username = _username
        UserDatabaseHelper userdb = new UserDatabaseHelper(getApplicationContext());
        userdb.addPracticeXP(myusername,xpgained);


        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGameDiff();
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // goes to game menu
        Intent goToMainMenu = new Intent(GameWinActivity.this,GameMenu.class);
        startActivity(goToMainMenu);
    }

    private void goToMainMenu() {
        Intent initMainMenu = new Intent(GameWinActivity.this, WhatToDo.class);
        startActivity(initMainMenu);
    }

    private void goToGameDiff() {
        Intent initGameDiff = new Intent(GameWinActivity.this,GameDiff.class);
        startActivity(initGameDiff);
    }
}
