package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;
import calculotprototype.g14.cmpt276.calculot_prototype.MainMenu;
import calculotprototype.g14.cmpt276.calculot_prototype.R;
import calculotprototype.g14.cmpt276.calculot_prototype.VectorGame.VectorGameActivity;
import calculotprototype.g14.cmpt276.calculot_prototype.WhatToDo;

public class GameOverActivity extends AppCompatActivity {

    int GameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int xpgained = getIntent().getIntExtra("xp",0);
        GameType = getIntent().getIntExtra("game",0);   //default is 0 -> gamediff, 1 -> vectorgameactivity
        xpgained = xpgained / 2;

        TextView XPField = (TextView)findViewById(R.id.gameOverXP);
        TextView tryAgain = (TextView)findViewById(R.id.gameOver_TryAgain);
        TextView goBack = (TextView)findViewById(R.id.gameOver_goToMainMenu);

        XPField.setText("You have gained: " + Integer.toString(xpgained) + "XP");

        SharedPreferences pref = getSharedPreferences("MyPref",MODE_PRIVATE);
        String myusername = pref.getString("username","notmyusername");

        // Open database and store the XP into the database where username = _username
        UserDatabaseHelper userdb = new UserDatabaseHelper(getApplicationContext());
        userdb.addPracticeXP(myusername,xpgained);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGame();
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
        Intent goToMainMenu = new Intent(GameOverActivity.this,GameMenu.class);
        startActivity(goToMainMenu);
    }

    private void goToMainMenu() {
        Intent initMainMenu = new Intent(GameOverActivity.this, WhatToDo.class);
        startActivity(initMainMenu);
    }

    private void goToGame() {
        Intent initGame;
        if (GameType==0)
            initGame = new Intent(GameOverActivity.this,GameDiff.class);
        else initGame = new Intent(GameOverActivity.this,VectorGameActivity.class);
        startActivity(initGame);
    }
}
