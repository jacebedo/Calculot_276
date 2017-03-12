package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Created by Ryan on 3/8/2017.
 */

public class MainMenu extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sign_up_button = (Button) findViewById(R.id.sign_up_button); //Get reference to sign up button
        Button login_button = (Button) findViewById(R.id.login_button); //Get reference to login button
        Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast); //Get reference to fade_in animation
        sign_up_button.startAnimation(fadein);
        login_button.startAnimation(fadein);
    }

    public void main_onClick_signUp(View view){
        Intent goToSignUpForm = new Intent(MainMenu.this, AddUser.class);
        startActivity(goToSignUpForm);
    }
}
