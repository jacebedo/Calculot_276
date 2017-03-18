package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.User;
import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;
import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameActivity;

/**
 * Created by Ryan on 3/17/2017.
 */


public class WhatToDo extends MainActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_to_do);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        String username = pref.getString("username",null); //Gets current logged in username from SharedPreferences

        user = (new UserDatabaseHelper(this)).getUser(username); //Gets User object from database (includes all user info)

        TextView welcomeMessageNameTV = (TextView)  findViewById(R.id.what_to_do_welcome_message_name);
        welcomeMessageNameTV.setText(user.getFirstname() + "!");

    }

    public void what_to_do_onClick_profile(View view){
        Intent goToProfile = new Intent(WhatToDo.this, Profile.class);
        startActivity(goToProfile);
    }

    public void what_to_do_onClick_learn(View view){
        Intent goToLearn = new Intent(WhatToDo.this, LearnActivity.class);
        startActivity(goToLearn);
    }

    public void what_to_do_onClick_game(View view){
        Intent goToGameActivity = new Intent(WhatToDo.this, GameActivity.class);
        startActivity(goToGameActivity);
    }

    public void what_to_do_onClick_logout(View view){
        //Logs user out by setting current logged in user to null
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username",null);
        editor.commit();
        Log.i("WhatToDo","User logged out succesfully");
        //Go back to beginning of app
        Intent goToMainMenu = new Intent(WhatToDo.this, MainMenu.class);
        startActivity(goToMainMenu);

        finish();
    }
}
