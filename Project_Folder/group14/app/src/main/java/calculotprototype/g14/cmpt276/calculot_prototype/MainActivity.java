package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;


public class MainActivity extends AppCompatActivity {
    //MainActivity will act as the splashscreen, as well as to load anything you need to do before main menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        splashy();
        //Code here will run during splashscreen
        // Master commit attempt - jacebedo
    }

    public void splashy(){
        Log.i("splashy","Splashy started");
        TextView title = (TextView) findViewById(R.id.splashscreen_title); //Get reference to Splashscreen Title
        Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in); //Get reference to fade_in animation
        title.startAnimation(fadein); //Fade title in

        fadein.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {} //Don't delete, auto-generated
            public void onAnimationEnd(Animation animation) {
                Intent goToMainMenu = new Intent(MainActivity.this, MainMenu.class);
                startActivity(goToMainMenu);
                finish(); //Ends splash screen, so you can't go back to it
                Log.i("splashy","Fade in finished");
            }
            public void onAnimationRepeat(Animation animation) {} //Don't delete, auto-generated
        });
    }
}