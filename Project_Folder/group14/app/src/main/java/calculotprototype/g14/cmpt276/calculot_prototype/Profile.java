package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    int MAX_PRACTICE_XP = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        String username = pref.getString("username",null); //Gets current logged in username from SharedPreferences

        UserDatabaseHelper DB = new UserDatabaseHelper(this);
        user = DB.getUser(username); //Gets User object from database (includes all user info)
        DB.addPracticeXP(username, 120);

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
        TextView practiceXPTV = (TextView) findViewById(R.id.profile_practiceXP_text);
        TextView levelTV = (TextView) findViewById(R.id.profile_level);
        //Get strings for textview and cast as string (because it's messy)
        String practiceXPSt = getApplicationContext().getString(R.string.practice_xp);
        String levelSt = getApplicationContext().getString(R.string.level);

        int XPneededfornextlevel = MAX_PRACTICE_XP - user.getRemainderXP();
        //This is messy too
        String practiceXPoutOfSt = Integer.toString(user.getRemainderXP()) + "/" + Integer.toString(MAX_PRACTICE_XP) + "\n" + Integer.toString(XPneededfornextlevel) + " XP needed for next level.";
        //Sets TextView so user can see int of how much XP they have
        practiceXPTV.setText(practiceXPSt + " " + practiceXPoutOfSt);
        //Sets level TextView to the players level
        levelTV.setText(levelSt + ": " + Integer.toString(user.getlevel()));

        //Update PracticeXP Progress bar
        ProgressBar practiceXPPB = (ProgressBar) findViewById(R.id.profile_practiceXP_progress);
        practiceXPPB.setMax(MAX_PRACTICE_XP);
        practiceXPPB.setProgress(user.getPracticeXP());

        int avatarUse = user.getAvatar();
        ImageView avatarPicture = (ImageView) findViewById(R.id.profile_userImage);
        if (avatarUse == 1) avatarPicture.setImageResource(R.drawable.malewizard1);
        else if (avatarUse == 2) avatarPicture.setImageResource(R.drawable.malewizard2);
        else if (avatarUse == 3) avatarPicture.setImageResource(R.drawable.malewizard3);
        //Else leave at default

        //Get reference to achievement images and lock status texts
        ImageView achievement1img = (ImageView) findViewById(R.id.profile_achievement_image1);
        ImageView achievement2img = (ImageView) findViewById(R.id.profile_achievement_image2);
        ImageView achievement3img = (ImageView) findViewById(R.id.profile_achievement_image3);
        TextView achievement1text = (TextView) findViewById(R.id.profile_achievement1_lockstatus);
        TextView achievement2text = (TextView) findViewById(R.id.profile_achievement2_lockstatus);
        TextView achievement3text = (TextView) findViewById(R.id.profile_achievement3_lockstatus);

        //Update achievements if applicable
        if (user.getlevel() >= 5){
            achievement1img.setImageResource(R.drawable.bronze);
            achievement1text.setText("Unlocked"); }
        if (user.getlevel() >= 10){
            achievement2img.setImageResource(R.drawable.silver);
            achievement2text.setText("Unlocked"); }
        if (user.getlevel() >= 15){
            achievement3img.setImageResource(R.drawable.gold);
            achievement3text.setText("Unlocked"); }
    }
}
