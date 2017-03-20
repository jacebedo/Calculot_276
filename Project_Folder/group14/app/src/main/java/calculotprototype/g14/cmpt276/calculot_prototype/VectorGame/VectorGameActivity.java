package calculotprototype.g14.cmpt276.calculot_prototype.VectorGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.CalcQuestion;
import calculotprototype.g14.cmpt276.calculot_prototype.Classes.VectorQuestionGenerator;
import calculotprototype.g14.cmpt276.calculot_prototype.R;
import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameOverActivity;
import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameWinActivity;

public class VectorGameActivity extends AppCompatActivity {

    Intent gameOver;

    //Game Activity Data: Difficulty, Level, Shells, Points in Shell, Potential gain/loss, change in XP, timer
    int Difficulty; //Choose Easy, Medium, or Hard
    int EasyLevel;
    int MediumLevel;
    int HardLevel;

    int GainedXP = 0;

    VectorQuestionGenerator TheGenerator;

    //Multiple Choice: Question, QuestionInfo, (2-5) Answer choices
    LinearLayout MultipleChoice;
    String[] AnswerArray;
    TextView[] ChoiceArray; //Index: 0 - Question, 1 - QuestionInfo, 2 and beyond correspond to the AnswerArray index i+2
    int AnswerArraySize;
    int AnswerArrayIndex;
    String Question;
    String QuestionInfo;

    TextView addQuestion;
    TextView addQuestionInfo;

    //Game Info: Timer
    LinearLayout GameInfo;
    CountDownTimer Timer;
    TextView TextTimer;
    int TextTime = 0;       //the time left for the current question vector
    int QuestionTime;   //the time for each question at that particular level

    // set toast for right/wrong answer
    Toast wrongAnswer;
    Toast rightAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vectorgame);

        gameOver = new Intent(VectorGameActivity.this, GameOverActivity.class);
        //retrieve Difficulty and Respective Levels
        Difficulty = getIntent().getIntExtra("Difficulty", 1);
        EasyLevel = getIntent().getIntExtra("EasyLevel", 1);
        MediumLevel = getIntent().getIntExtra("MediumLevel", 1);
        HardLevel = getIntent().getIntExtra("HardLevel", 1);

        MultipleChoice = (LinearLayout) findViewById(R.id.vectorMultipleChoiceLayout);
        GameInfo = (LinearLayout) findViewById(R.id.vectorGameInfoLayout);

        //Textview for timer
        TextTimer = new TextView(this);
        TextTimer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //TextTimer.setText("Time Left: "+String.valueOf(TextTime));

        GameInfo.addView(TextTimer);

        wrongAnswer = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
        rightAnswer = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);

        TheGenerator = new VectorQuestionGenerator(Difficulty, EasyLevel, MediumLevel, HardLevel);
        TheGenerator.generateQuestion();
        startQuestion();
    }

    private void startTimer() {
        // Set up countdown timer depending on difficulty
        TextTime = QuestionTime;
        TextTimer.setText("Time Left: "+String.valueOf(TextTime));
        Timer = new CountDownTimer(QuestionTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TextTime--;
                TextTimer.setText("Test Time Left: "+String.valueOf(TextTime));
            }

            @Override
            public void onFinish() {

                //game over scenario
                if (true)   //placeholder
                {
                    gameOver.putExtra("xp", GainedXP);
                    gameOver.putExtra("game", 1);
                    this.cancel();
                    startActivity(gameOver);
                }
            }
        };
        Timer.start();
    }

    private void startQuestion() {
        //Multiple Choice: Question, QuestionInfo, (2-7) Answer choices depending on difficulty
        AnswerArray = TheGenerator.getAnswerArray();
        AnswerArraySize = TheGenerator.getAnswerArraySize();
        AnswerArrayIndex = TheGenerator.getAnswerArrayIndex();
        Question = TheGenerator.getQuestion();
        QuestionInfo = TheGenerator.getQuestionInfo();

        QuestionTime = TheGenerator.getQuestionTime();

        TextView addQuestion = new TextView(this);
        TextView addQuestionInfo = new TextView(this);

        addQuestion.setText(Question);
        addQuestionInfo.setText(QuestionInfo);

        addQuestion.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        addQuestionInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        MultipleChoice.addView(addQuestion);
        MultipleChoice.addView(addQuestionInfo);

        for (int i = 0; i < AnswerArraySize; i++) {
            //need to set onclick listeners for correct/incorrect answers
            TextView addText = new TextView(this);  //scope of addText is within this for loop
            addText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            addText.setText(AnswerArray[i]);

            if (i == AnswerArrayIndex) {
                //right answer
                addText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //implement
                        rightAnswer.show();
                    }
                });
            }
            else {
                //wrong answer
                addText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //implement
                        wrongAnswer.show();
                    }
                });
            }

            MultipleChoice.addView(addText);
        }

        startTimer();
    }
}