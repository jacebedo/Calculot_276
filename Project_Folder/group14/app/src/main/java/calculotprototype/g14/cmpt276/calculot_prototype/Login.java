package calculotprototype.g14.cmpt276.calculot_prototype;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;

/**
 * Created by Ryan on 3/12/2017.
 */
public class Login extends MainActivity {
    static Login toFinish; //For closing Login Activity
    Button newUserButtonGlob; //So we can delete it
    Button userButtons[]; //So we can delete them
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        toFinish = this;
    }

    @Override
    protected void onResume(){
        super.onResume();
        LinearLayout ll = (LinearLayout) findViewById(R.id.login_linearlayout);
        int numUsers = numUsers();
        if (userButtons != null)
            for (int i = 0; i < userButtons.length; i++)
                ll.removeView(userButtons[i]); //Deletes all dynamically added buttons so we can repopulate
        ll.removeView(newUserButtonGlob); //Deletes the current global newUserButton
        populateUserButtons();
        makeNewUserButton();
    }

    public void populateUserButtons(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.login_linearlayout);

        final int numUsers = (new UserDatabaseHelper(this)).numUsers();
        String usernames[] = new String[numUsers]; //String array for usernames
        userButtons = new Button[numUsers];

        UserDatabaseHelper DB = new UserDatabaseHelper(this);
        Cursor cursor = DB.getAnyUserInfo();

        if (cursor.moveToFirst()){
            for (int i = 0; i < numUsers; i++, cursor.moveToNext()) { //i to keep track of arrays, moves cursor in process too
                Button userButton = new Button(this); //Creates a new button
                usernames[i] = cursor.getString(0); //Gets username at i
                userButton.setText(usernames[i]);   //Button displays username
                userButton.setAllCaps(false);
                userButtons[i] = userButton; //Adds new button to array
                ll.addView(userButton); //Adds new button to LinearLayout
                final String name = usernames[i]; //Must use final for setOnClickListener
                userButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToConfirmPassword = new Intent(Login.this, ConfirmPassword.class);
                        goToConfirmPassword.putExtra("name",name); //Sends username to ConfirmPassword
                        startActivity(goToConfirmPassword);
                    }
                });
            }
        }
    }

    public void makeNewUserButton(){
        //Creates button to add user, that redirects to AddUser class
        LinearLayout ll = (LinearLayout) findViewById(R.id.login_linearlayout);
        Button newUserButton = new Button(this);
        newUserButtonGlob = newUserButton;
        newUserButton.setText(R.string.add_user);
        newUserButton.setAllCaps(false);
        ll.addView(newUserButton);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(Login.this, AddUser.class);
                startActivity(signUp);
            }
        });
    }

    public int numUsers(){
        UserDatabaseHelper DB = new UserDatabaseHelper(this);
        return DB.numUsers();
    }

    //Finishes this activity when called
    public static Login getInstance(){
        return toFinish;
    }
}
