package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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

        setEmptyQuestion(question,answer1,answer2,answer3,answer4);

        final Monster monster = new Monster(topic,info[2]);

        // Set up screen test;
        RelativeLayout gameScreen = (RelativeLayout) findViewById(R.id.game_screen);
        final calcGameGraphics calcHelper = new calcGameGraphics(this,monster,info,healthfield);
        calcHelper.setBackgroundColor(Color.WHITE);
        gameScreen.addView(calcHelper);

        calcHelper.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (checkBounds(event,monster) == true) {
                        monster.selectMonster();
                        setMonsterQuestion(question,answer1,answer2,answer3,answer4,monster);

                        answer1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ( monster.isCorrect(answer1)) {
                                    info[3]++;
                                    checkLevel(info);
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                } else {
                                    info[0]--;
                                    checkGameOver(info, gameOver);
                                    healthfield.setText("Health: " + Integer.toString(info[0]));
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                }
                                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                            }
                        });
                        answer2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ( monster.isCorrect(answer2)) {
                                    info[3]++;
                                    checkLevel(info);
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                } else {
                                    info[0]--;
                                    checkGameOver(info, gameOver);
                                    healthfield.setText("Health: " + Integer.toString(info[0]));
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                }
                                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                            }
                        });
                        answer3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ( monster.isCorrect(answer3)) {
                                    info[3]++;
                                    checkLevel(info);
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                } else {
                                    info[0]--;
                                    checkGameOver(info, gameOver);
                                    healthfield.setText("Health: " + Integer.toString(info[0]));
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                }
                                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                            }
                        });
                        answer4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ( monster.isCorrect(answer4)) {
                                    info[3]++;
                                    checkLevel(info);
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                } else {
                                    info[0]--;
                                    checkGameOver(info, gameOver);
                                    healthfield.setText("Health: " + Integer.toString(info[0]));
                                    monster.respawnMonster(info[2]);
                                    setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                                }
                                xpfield.setText("XP Gained: " + Integer.toString(info[1]));
                            }
                        });

                    }
                    else {
                        monster.deselectMonster();
                        setEmptyQuestion(question,answer1,answer2,answer3,answer4);
                    }
                }

                return true;
            }

        });

    }

    private void checkGameOver(int[] info, Intent gameOver) {
        if (info[0] <= 0) {
            gameOver.putExtra("xp",info[1]);
            startActivity(gameOver);
        }
    }

    private boolean checkBounds(MotionEvent event, Monster monster){
        int x = (int) Math.round(event.getX());
        int y = (int) Math.round(event.getY());

        if (x >= monster.getXCoord() && x <= monster.getXCoord() + monster.getMonster_width() &&
                y >= monster.getYCoord() && y <= monster.getYCoord() + monster.getMonster_height() ) {
            return true;
        }
            return false;
    }




    private void setEmptyQuestion(TextView _question, TextView _answer1, TextView _answer2, TextView _answer3, TextView _answer4)  {
        _question.setText(getResources().getString(R.string.emptyQuestion));
        _answer1.setText(getResources().getString(R.string.ans1));
        _answer2.setText(getResources().getString(R.string.ans2));
        _answer3.setText(getResources().getString(R.string.ans3));
        _answer4.setText(getResources().getString(R.string.ans4));
    }

    private void setMonsterQuestion(TextView _question, TextView _answer1, TextView _answer2, TextView _answer3, TextView _answer4,Monster monster)  {
        _question.setText(monster.getQuestion());
        _answer1.setText(monster.getAnswer_1());
        _answer2.setText(monster.getAnswer_2());
        _answer3.setText(monster.getAnswer_3());
        _answer4.setText(monster.getAnswer_4());
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