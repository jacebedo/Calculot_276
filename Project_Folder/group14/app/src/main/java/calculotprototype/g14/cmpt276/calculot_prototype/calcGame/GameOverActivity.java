package calculotprototype.g14.cmpt276.calculot_prototype.calcGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;
import calculotprototype.g14.cmpt276.calculot_prototype.MainMenu;
import calculotprototype.g14.cmpt276.calculot_prototype.R;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int xpgained = getIntent().getIntExtra("xp",0);
        xpgained = xpgained / 2;

        TextView XPField = (TextView)findViewById(R.id.gameOverXP);

        XPField.setText("You have gained: " + Integer.toString(xpgained) + "XP");

        SharedPreferences pref = getSharedPreferences("MyPref",MODE_PRIVATE);
        String myusername = pref.getString("username","notmyusername");

        // Open database and store the XP into the database where username = _username
        UserDatabaseHelper userdb = new UserDatabaseHelper(getApplicationContext());
        userdb.addPracticeXP(myusername,xpgained);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // temporarily goes into mainmenu. This will change.
        Intent goToMainMenu = new Intent(GameOverActivity.this,MainMenu.class);
        startActivity(goToMainMenu);
    }
}
