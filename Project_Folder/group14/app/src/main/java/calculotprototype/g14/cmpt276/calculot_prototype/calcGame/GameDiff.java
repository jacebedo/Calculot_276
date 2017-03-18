package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import calculotprototype.g14.cmpt276.calculot_prototype.R;

public class GameDiff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_diff);

        int topic;

        Button gameDiff = (Button)findViewById(R.id.gameDiff_Diff);
        Button gameInt = (Button)findViewById(R.id.gameDiff_Int);
        Button gameComb = (Button)findViewById(R.id.gameDiff_Comb);

        gameDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeGame(0);
            }
        });

        gameInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeGame(1);
            }
        });

        gameComb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeGame(2);
            }
        });
    }

    private void initializeGame(int topic) {
        Intent initGame = new Intent(GameDiff.this,GameActivity.class);
        initGame.putExtra("game_topic",topic);
        startActivity(initGame);
    }
}
