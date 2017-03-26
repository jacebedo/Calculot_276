package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.CalcQuestion;
import calculotprototype.g14.cmpt276.calculot_prototype.Classes.calcGameGraphics;
import calculotprototype.g14.cmpt276.calculot_prototype.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Health, XPGained, Level, Completed
        final int[] info = {7,0,1,0};
        final int topic = getIntent().getIntExtra("game_topic",0);
        final CalcQuestion calc = new CalcQuestion(topic,1);


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


        // set toast for taking damage
        final Toast damageTaken = Toast.makeText(getApplicationContext(),"You have lost a life!", Toast.LENGTH_SHORT);
        // Set up countdown timer ( 7 seconds currently - 1s = 1000ms)
        final CountDownTimer timer = new CountDownTimer(8000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                info[0]--;
                damageTaken.show();
                calc.getNewQuestion(topic, info[2]);
                setQuestion(question, answer1, answer2, answer3, answer4, calc, this);
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if (info[0] <= 0) {
                    this.cancel();
                    gameOver.putExtra("xp",info[1]);
                    startActivity(gameOver);
                }
            }
        };

       // Set the first answer field
        setQuestion(question, answer1, answer2, answer3, answer4, calc, timer);

        // Set up screen test;
        RelativeLayout gameScreen = (RelativeLayout)findViewById(R.id.game_screen);
        calcGameGraphics calcHelper = new calcGameGraphics(this);
        calcHelper.setBackgroundColor(Color.WHITE);

        gameScreen.addView(calcHelper);



        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer1) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                } else {
                    // Do something else if incorrect
                    info[0]--;
                    damageTaken.show();
                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]<=0){
                    timer.cancel();
                    gameOver.putExtra("xp",info[1]);
                    startActivity(gameOver);
                }
            }
        });

        // Set up the second answer field
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer2) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                } else {
                    // Do something else if wrong
                    info[0]--;
                    damageTaken.show();
                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]<=0){
                    timer.cancel();
                    gameOver.putExtra("xp",info[1]);
                    startActivity(gameOver);
                }
            }
        });

        // Set up the third answer field
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer3) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                } else {
                    // Do something else if wrong
                    info[0]--;
                    damageTaken.show();
                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]<=0){
                    timer.cancel();
                    gameOver.putExtra("xp",info[1]);
                    startActivity(gameOver);
                }
            }
        });

        // Set up the fourth answer field
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer4) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                } else {
                    // Do something else if wrong
                    info[0]--;
                    damageTaken.show();
                    calc.getNewQuestion(topic, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc,timer);
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]<=0){
                    timer.cancel();
                    gameOver.putExtra("xp",info[1]);
                    startActivity(gameOver);
                }
            }
        });


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
                ;}

            ;
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

    }
}