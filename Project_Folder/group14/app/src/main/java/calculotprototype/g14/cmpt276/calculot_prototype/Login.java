package calculotprototype.g14.cmpt276.calculot_prototype;

import android.app.ActionBar;
import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        LinearLayout ll = (LinearLayout) findViewById(R.id.linearlay);

        final int numUsers = numUsers();
        String usernames[] = new String[numUsers]; //String array for usernames
        String firstNames[] = new String[numUsers]; //String array for first names

        UserDatabaseHelper DB = new UserDatabaseHelper(this);
        Cursor cursor = DB.getAnyUserInfo();

        if (cursor.moveToFirst()){
            for (int i = 0; i < numUsers; i++, cursor.moveToNext()) { //i to keep track of arrays, moves cursor in process too
                Button userButton = new Button(this); //Creates a new button
                usernames[i] = cursor.getString(0); //Gets username at i
                firstNames[i] = cursor.getString(1); //Gets first name at i
                userButton.setText(usernames[i]);   //Button displays username
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

        //Creates button to add user, that redirects to AddUser class
        Button newUserButton = new Button(this);
        newUserButton.setText("Add User");
        ll.addView(newUserButton);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(Login.this, AddUser.class);
                startActivity(signUp);
            }
        });


        //buttonsFunc();

    }

    public int numUsers(){
        UserDatabaseHelper DB = new UserDatabaseHelper(this);
        return DB.numUsers();
    }

}
