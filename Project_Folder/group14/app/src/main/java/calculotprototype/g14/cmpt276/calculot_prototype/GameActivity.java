package calculotprototype.g14.cmpt276.calculot_prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.CalcQuestion;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        CalcQuestion calc = new CalcQuestion(0,3);
        int health = 5;
        int xpgained = 0;

        TextView healthfield = (TextView)findViewById(R.id.game_health);
        healthfield.setText("Health: " + Integer.toString(health));

        TextView xpfield = (TextView)findViewById(R.id.game_xpgained);
        xpfield.setText("XP Gained: " + Integer.toString(xpgained));

        TextView question = (TextView)findViewById(R.id.game_question);
        question.setText(calc.getQuestion());

        TextView answer1 = (TextView)findViewById(R.id.game_answer1);
        TextView answer2 = (TextView)findViewById(R.id.game_answer2);
        TextView answer3 = (TextView)findViewById(R.id.game_answer3);
        TextView answer4 = (TextView)findViewById(R.id.game_answer4);

        answer1.setText(calc.getAnswer_1());
        answer2.setText(calc.getAnswer_2());
        answer3.setText(calc.getAnswer_3());
        answer4.setText(calc.getAnswer_4());

    }
}
