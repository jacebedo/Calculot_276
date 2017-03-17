package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.R;

public class GameWinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_win);

        int xpgained = getIntent().getIntExtra("xp",0);

        TextView xpfield = (TextView)findViewById(R.id.gamewin_xpfield);
        xpfield.setText("You have achieved: " + Integer.toString(xpgained) + " XP for your reward");
    }
}
