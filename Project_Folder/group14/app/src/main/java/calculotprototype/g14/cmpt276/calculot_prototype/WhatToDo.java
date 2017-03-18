package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import calculotprototype.g14.cmpt276.calculot_prototype.calcGame.GameActivity;

/**
 * Created by Ryan on 3/17/2017.
 */


public class WhatToDo extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_to_do);
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
        finish(); //Goes back to login
    }
}
