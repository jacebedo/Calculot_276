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

    //Game Activity Data: Difficulty, Level, Shells, Points in Shell, Potential gain/loss, change in XP, timer

    //Multiple Choice: Question, QuestionInfo, (2-5) Answer choices
    LinearLayout MultipleChoice = (LinearLayout) findViewById(R.id.vectorMultipleChoiceLayout);
    String[] AnswerArray;
    int AnswerArraySize;
    int AnswerArrayIndex;
    String Question;
    String QuestionInfo;

    TextView addQuestion;
    TextView addQuestionInfo;

    // set toast for right/wrong answer
    final Toast wrongAnswer = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
    final Toast rightAnswer = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vectorgame);

        gameOver = new Intent(VectorGameActivity.this, GameOverActivity.class);
        MediumLevel = getIntent().getIntExtra("MediumLevel", 1);

        TheGenerator = new VectorQuestionGenerator(1, 1, MediumLevel, 1);
        TheGenerator.generateQuestion();
    }

    private void startTimer() {
        // Set up countdown timer depending on difficulty
        final CountDownTimer timer = new CountDownTimer(TheGenerator.getQuestionTime() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {

                //game over scenario
                if (true)   //placeholder
                {
                    gameOver.putExtra("xp", GainedXP);
                    this.cancel();
                    startActivity(gameOver);
                }
            }
        };
    }

    private void generateQuestion() {
        //Multiple Choice: Question, QuestionInfo, (2-5) Answer choices
        //turn into method
        AnswerArray = TheGenerator.getAnswerArray();
        AnswerArraySize = TheGenerator.getAnswerArraySize();
        AnswerArrayIndex = TheGenerator.getAnswerArrayIndex();
        Question = TheGenerator.getQuestion();
        QuestionInfo = TheGenerator.getQuestionInfo();

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
    }
}