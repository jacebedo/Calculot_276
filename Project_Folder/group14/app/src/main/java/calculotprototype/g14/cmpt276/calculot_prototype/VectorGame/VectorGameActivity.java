package calculotprototype.g14.cmpt276.calculot_prototype.VectorGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.CalcQuestion;
import calculotprototype.g14.cmpt276.calculot_prototype.Classes.VectorQuestionGenerator;
import calculotprototype.g14.cmpt276.calculot_prototype.R;
import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameOverActivity;
import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameWinActivity;

public class VectorGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Intent gameOver = new Intent(VectorGameActivity.this, GameOverActivity.class);
        int MediumLevel = getIntent().getIntExtra("MediumLevel", 1);

        final int GainedXP = 0; //-> non final

        VectorQuestionGenerator TheGenerator = new VectorQuestionGenerator(1, 1, MediumLevel, 1);

        // set toast for taking damage
        final Toast wrongAnswer = Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT);
        // Set up countdown timer depending on difficulty
        final CountDownTimer timer = new CountDownTimer(15 * 1000, 1000) {
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
}