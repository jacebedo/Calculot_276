package calculotprototype.g14.cmpt276.calculot_prototype.VectorGame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import calculotprototype.g14.cmpt276.calculot_prototype.Classes.VectorQuestionGenerator;
import calculotprototype.g14.cmpt276.calculot_prototype.R;
import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameOverActivity;

//kza21
public class VectorGameActivity extends AppCompatActivity {

    Intent gameOver;

    //Game Activity Data: Difficulty, Level, Shells, Points in Shell, Potential gain/loss, change in XP, timer
    int Difficulty; //Choose Easy, Medium, or Hard
    int EasyLevel;
    int MediumLevel;
    int HardLevel;

    VectorQuestionGenerator TheGenerator;

    //Game View
    RelativeLayout GameView;
    Bitmap BMGrid;
    Bitmap BMQuestionVector;
    Bitmap BMClockVector; //includes potential gain
    Bitmap BMCrystalBall;
    int GameHeight;
    int GameWidth;
    Paint BlackPaint;
    Canvas GameCanvas;

    //Multiple Choice: Question, QuestionInfo, (2-5) Answer choices
    LinearLayout MultipleChoice;
    String[] AnswerArray;
    TextView[] ChoiceArray; //Index: 0 - Question, 1 - QuestionInfo, 2 and beyond correspond to the AnswerArray index i+2; unneeded?
    int AnswerArraySize;
    int AnswerArrayIndex;
    int AnswerChoices;      //number of answer choices left -> decrement by 1 if incorrect answer chosen -> if 1 then fail current question
    String Question;
    String QuestionInfo;

    TextView addQuestion;
    TextView addQuestionInfo;

    //Game Info: Timer
    LinearLayout GameInfo;
    CountDownTimer Timer;
    TextView TextTimer;
    int TextTime = 0;       //the time left for the current question vector
    int QuestionTime;       //the time for each question at that particular level
    TextView TextLevel;     //the level textview
    int Level;
    int DecreaseAmount;  //+180 when a user picks the wrong option -> if >0, the user input is frozen and points/time decrease rapidly

    // set toast for right/wrong answer
    Toast wrongAnswer;
    Toast rightAnswer;

    //Question points
    int Shell;              //base shell level 1
    int MaxShell;           //the shell level required to progress to the next level
    int ShellPoints;        //Points in the current shell: 1-360 -> upon reaching 0, decrement shell value
    int TotalGain = 0;      //XP to be gained
    TextView TextTotalGain; //display total XP to be gained
    int PotentialGain;      //current question potential change in points: -360 to 360 -> upon reaching less than -360, fail current question

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


        wrongAnswer = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
        rightAnswer = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);

        TheGenerator = new VectorQuestionGenerator(Difficulty, EasyLevel, MediumLevel, HardLevel);

        //TextView for Level
        TextLevel = new TextView(this);
        TextLevel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Level = getLevel();
        TextLevel.setText("Level: "+String.valueOf(Level));
        TextLevel.setTextColor(getResources().getColor(R.color.colorPrimary));

        GameInfo.addView(TextLevel);

        //TextView for total XP gain
        TextTotalGain = new TextView(this);
        TextTotalGain.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextTotalGain.setText("Total XP gained: "+String.valueOf(TotalGain));
        TextTotalGain.setTextColor(getResources().getColor(R.color.colorPrimary));

        GameInfo.addView(TextTotalGain);

        //Textview for timer
        TextTimer = new TextView(this);
        TextTimer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //TextTimer.setText("Time Left: "+String.valueOf(TextTime));
        TextTimer.setTextColor(getResources().getColor(R.color.colorAccent));

        GameInfo.addView(TextTimer);

        //Initialize Game View
        GameWidth = TheGenerator.getHalfWidth() * 2;
        GameHeight = TheGenerator.getHalfHeight() * 2;

        GameView = (RelativeLayout) findViewById(R.id.crystalBallLayout);
        BMGrid = Bitmap.createBitmap(GameWidth, GameHeight, Bitmap.Config.ARGB_8888);
        GameCanvas = new Canvas(BMGrid);

        BlackPaint = new Paint();
        BlackPaint.setColor(Color.BLACK);
        BlackPaint.setStrokeWidth(3);

        GameCanvas.drawLine(0, GameHeight/2, GameWidth, GameHeight/2, BlackPaint);  //x axis
        GameCanvas.drawLine(GameWidth/2, 0, GameWidth/2, GameHeight, BlackPaint);  //y axis

        ImageView Grid = new ImageView(this);
        Grid.setImageBitmap(BMGrid);
        GameView.addView(Grid);
        //------

        startQuestion();
    }

    private int getLevel() {
        if (Difficulty == 0)
            return EasyLevel;
        else if (Difficulty == 1)
            return MediumLevel;
        else return HardLevel;
    }

    private void changeLevel(int _changelevel) {
        if (Difficulty == 0) {
            EasyLevel = _changelevel;
            TheGenerator.setEasyLevel(EasyLevel);
        }
        else if (Difficulty == 1) {
            MediumLevel = _changelevel;
            TheGenerator.setMediumLevel(MediumLevel);
        }
        else {
            HardLevel = _changelevel;
            TheGenerator.setHardLevel(HardLevel);
        }

        Level = getLevel();
        TextLevel.setText("Level: "+String.valueOf(Level));
    }

    private void startTimer() {
        // Set up countdown timer depending on difficulty
        TextTime = QuestionTime;
        TextTimer.setText("Time Left: "+String.valueOf(TextTime));

        //temporary
        PotentialGain = TextTime * 10;
        Timer = new CountDownTimer(QuestionTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TextTime--;
                TextTimer.setText("Time Left: "+String.valueOf(TextTime));

                //temporary;
                PotentialGain = TextTime * 10;
            }

            @Override
            public void onFinish() {
                //game over scenario
                goToGameOver();
            }
        };
        Timer.start();
    }

    private void goToGameOver() {
        gameOver.putExtra("xp", TotalGain*2);   //since gameoveractivity divides XP by 2
        gameOver.putExtra("game", 1);
        Timer.cancel();
        finish();   //pop this activity off the stack
        startActivity(gameOver);
    }

    private void startQuestion() {
        TheGenerator.generateQuestion();
        //
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

        addQuestion.setTextColor(getResources().getColor(R.color.colorGreen));
        addQuestionInfo.setTextColor(getResources().getColor(R.color.colorGreen));

        MultipleChoice.addView(addQuestion);
        MultipleChoice.addView(addQuestionInfo);

        for (int i = 0; i < AnswerArraySize; i++) {
            //need to set onclick listeners for correct/incorrect answers
            TextView addText = new TextView(this);  //scope of addText is within this for loop
            addText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            addText.setTextSize(17);
            addText.setText(AnswerArray[i]);

            if (i == AnswerArrayIndex) {
                //right answer
                addText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //implement
                        rightAnswer.show();

                        Timer.cancel();
                        if((MultipleChoice).getChildCount() > 0)
                            (MultipleChoice).removeAllViews();

                        TotalGain += PotentialGain;

                        TextTotalGain.setText("Total XP gained: "+String.valueOf(TotalGain));
                        changeLevel( getLevel() + 1 );  //increment level by 1
                        startQuestion();
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

                        Timer.cancel();
                        if((MultipleChoice).getChildCount() > 0)
                            (MultipleChoice).removeAllViews();

                        goToGameOver();
                    }
                });
            }

            MultipleChoice.addView(addText);
        }

        startTimer();
    }

    @Override
    public void onBackPressed() {
        //finish activities to avoid large stack of activities?
        goToGameOver(); //call goToGameOver() which cancels the timer
    }
}