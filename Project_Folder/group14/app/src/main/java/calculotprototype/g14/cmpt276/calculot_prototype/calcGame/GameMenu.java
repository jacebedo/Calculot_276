package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.R;

public class GameMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        TextView goToCalcGame = (TextView)findViewById(R.id.gameMenu_Defense);
        TextView goToTrigGame = (TextView)findViewById(R.id.gameMenu_CrystalBall);

        goToCalcGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent initCalcGame = new Intent(GameMenu.this,GameDiff.class);
                startActivity(initCalcGame);
            }
        });

        // Do the same for goToTrigGame
        goToTrigGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent initCalcGame = new Intent(GameMenu.this,GameDiff.class);
                startActivity(initCalcGame);
            }
        });
    }
}
