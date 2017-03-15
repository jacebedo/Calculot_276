package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.CalcQuestion;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Health, XPGained, Level, Completed
        final int[] info = {5,0,1,0};
        final CalcQuestion calc = new CalcQuestion(0,1);


        final TextView healthfield = (TextView) findViewById(R.id.game_health);
        healthfield.setText("Health: " + Integer.toString(info[0]));

        final TextView xpfield = (TextView) findViewById(R.id.game_xpgained);
        xpfield.setText("XP Gained: " + Integer.toString(info[1]));

        // Wire up all of the TextViews
        final TextView question = (TextView) findViewById(R.id.game_question);
        final TextView answer1 = (TextView) findViewById(R.id.game_answer1);
        final TextView answer2 = (TextView) findViewById(R.id.game_answer2);
        final TextView answer3 = (TextView) findViewById(R.id.game_answer3);
        final TextView answer4 = (TextView) findViewById(R.id.game_answer4);
        final Intent gameOver = new Intent(GameActivity.this, GameOverActivity.class);
        setQuestion(question, answer1, answer2, answer3, answer4, calc);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer1) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(0, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc);
                } else {
                    // Do something else if incorrect
                    info[0]--;
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]==0){
                    startActivity(gameOver);
                }
            }
        });


        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer2) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(0, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc);
                } else {
                    // Do something else if wrong
                    info[0]--;
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]==0){
                    startActivity(gameOver);
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer3) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(0, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc);
                } else {
                    // Do something else if wrong
                    info[0]--;
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]==0){
                    startActivity(gameOver);
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calc.isCorrect(answer4) == true) {
                    // Do something if correct
                    info[3]++;
                    checkLevel(info);

                    calc.getNewQuestion(0, info[2]);
                    setQuestion(question, answer1, answer2, answer3, answer4, calc);
                } else {
                    // Do something else if wrong
                    info[0]--;
                }
                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                healthfield.setText("Health: " + Integer.toString(info[0]));
                if(info[0]==0){
                    startActivity(gameOver);
                }
            }
        });


    }

    private boolean checkLevel(int[] info) {

        if (info[2] == 3 && info[3] % 10 == 0) {
            info[1] += 1000;
            return true;
        }

        if (info[3] % 10 == 0) {
            info[1]+= 100;
            info[2]++;
            if (info[2] == 2) { info[1] += 50; }
            if (info[2] == 3) { info[1] += 100;}

            return false;
        }

        return false;
    }


    public void setQuestion(TextView question, TextView answer1, TextView answer2,
                            TextView answer3, TextView answer4, CalcQuestion c) {

        question.setText(c.getQuestion());
        answer1.setText(c.getAnswer_1());
        answer2.setText(c.getAnswer_2());
        answer3.setText(c.getAnswer_3());
        answer4.setText(c.getAnswer_4());

    }
}