package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // temporarily goes into mainmenu. This will change.
        Intent goToMainMenu = new Intent(GameOverActivity.this,MainMenu.class);
        startActivity(goToMainMenu);
    }
}
