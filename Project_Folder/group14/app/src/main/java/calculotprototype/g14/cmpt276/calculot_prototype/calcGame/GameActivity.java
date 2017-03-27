package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.CalcQuestion;
import calculotprototype.g14.cmpt276.calculot_prototype.Classes.Monster;
import calculotprototype.g14.cmpt276.calculot_prototype.Classes.calcGameGraphics;
import calculotprototype.g14.cmpt276.calculot_prototype.R;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Health, XPGained, Level, Completed
        final int[] info = {7, 0, 1, 0};
        final int topic = getIntent().getIntExtra("game_topic", 0);

        // Set Initial TextViews
        final TextView healthfield = (TextView) findViewById(R.id.game_health);
        final TextView xpfield = (TextView) findViewById(R.id.game_xpgained);

        healthfield.setText("Health: " + Integer.toString(info[0]));
        xpfield.setText("XP Gained: " + Integer.toString(info[1]));

        // Wire up all of the TextViews
        final TextView question = (TextView) findViewById(R.id.game_question);
        final TextView answer1 = (TextView) findViewById(R.id.game_answer1);
        final TextView answer2 = (TextView) findViewById(R.id.game_answer2);
        final TextView answer3 = (TextView) findViewById(R.id.game_answer3);
        final TextView answer4 = (TextView) findViewById(R.id.game_answer4);
        final Intent gameOver = new Intent(GameActivity.this, GameOverActivity.class);


        Monster monster = new Monster(topic,info[2]);

        // Set up screen test;
        RelativeLayout gameScreen = (RelativeLayout) findViewById(R.id.game_screen);
        calcGameGraphics calcHelper = new calcGameGraphics(this,monster);
        calcHelper.setBackgroundColor(Color.WHITE);
        gameScreen.addView(calcHelper);

    }

    // Checks if the user can advance into the next level
    private void checkLevel(int[] info) {
        // End the game ( Level 3 and the user has answered 10 questions at level 3)
        if (info[2] == 3 && info[3] % 10 == 0) {
            info[1] += 200;
            Intent gameWin = new Intent(GameActivity.this,GameWinActivity.class);
            gameWin.putExtra("xp",info[1]);
            startActivity(gameWin);
        }

        // If the user has answered 10 questions at the current level, give the user XP and advance into the next level
        if (info[3] % 10 == 0) {
            info[1]+= 100;
            info[2]++;
            if (info[2] == 2) {
                info[1] += 50;
                info[0]++;
                Toast.makeText(getApplicationContext(),"LEVEL: 2",Toast.LENGTH_SHORT).show();
            }
            if (info[2] == 3) {
                info[1] += 100;
                info[0]++;
                Toast.makeText(getApplicationContext(),"LEVEL: 3",Toast.LENGTH_SHORT).show();

            }
        }
    }


    // Sets up the randomly generated question and start the timer up;
    public void setQuestion(TextView question, TextView answer1, TextView answer2,
                            TextView answer3, TextView answer4, CalcQuestion c, CountDownTimer timer) {

        timer.cancel();
        question.setText(c.getQuestion());
        answer1.setText(c.getAnswer_1());
        answer2.setText(c.getAnswer_2());
        answer3.setText(c.getAnswer_3());
        answer4.setText(c.getAnswer_4());

        timer.start();

    }


    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }
}