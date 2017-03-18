package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.User;
import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;
import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameActivity;

/**
 * Created by Ryan on 3/13/2017.
 */

public class Profile extends MainActivity {
    User user; //Global User to be used by functions
    int MAX_PRACTICE_XP = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        String username = pref.getString("username",null); //Gets current logged in username from SharedPreferences

        UserDatabaseHelper DB = new UserDatabaseHelper(this);
        user = DB.getUser(username); //Gets User object from database (includes all user info)

        TextView usernameTV = (TextView) findViewById(R.id.profile_username);
        TextView firstNameTV = (TextView) findViewById(R.id.profile_firstName);
        usernameTV.setText(user.getUsername());
        firstNameTV.setText(user.getFirstname());
    }

    @Override
    protected void onResume(){
        super.onResume(); //Keep this here!
        updateUI();
    }

    public void updateUI(){
        //Get reference to textviews
        TextView learningXPTV = (TextView) findViewById(R.id.profile_learningXP_text);
        TextView practiceXPTV = (TextView) findViewById(R.id.profile_practiceXP_text);
        TextView levelTV = (TextView) findViewById(R.id.profile_level);
        //Get strings for textview and cast as string (because it's messy)
        String learningXPSt = getApplicationContext().getString(R.string.learning_xp);
        String practiceXPSt = getApplicationContext().getString(R.string.practice_xp);
        String levelSt = getApplicationContext().getString(R.string.level);

        learningXPTV.setText(learningXPSt); //TODO: make learningXP track number of modules finished instead. Each module adds y XP to total

        //This is messy too
        String practiceXPoutOfSt = Integer.toString(user.getPracticeXP()) + "/" + Integer.toString(MAX_PRACTICE_XP);
        //Sets TextView so user can see int of how much XP they have
        practiceXPTV.setText(practiceXPSt + practiceXPoutOfSt);
        //Sets level TextView to the players level
        levelTV.setText(levelSt + ": " + Integer.toString(user.getlevel()));

        //Update PracticeXP Progress bar
        ProgressBar practiceXPPB = (ProgressBar) findViewById(R.id.profile_practiceXP_progress);
        practiceXPPB.setMax(MAX_PRACTICE_XP);
        practiceXPPB.setProgress(user.getPracticeXP());
    }

    public void profile_onClick_addXP(View view){
        user.setPracticeXP(user.getPracticeXP()+5);
        Log.i("Profile","Button pressed");
        updateUI();
    }
}
