package calculotprototype.g14.cmpt276.calculot_prototype;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;

/**
 * Created by Ryan on 3/12/2017.
 */

public class ConfirmPassword extends MainActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ConfirmPassword","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_password);

        Bundle bundle = getIntent().getExtras(); //Must be in onCreate
        username = bundle.getString("name"); //Get username trying to log in


        TextView welcomeMessage = (TextView) findViewById(R.id.ConfirmPassword_welcomeMessage);
        welcomeMessage.setText("Welcome " + username);
    }

    public void ConfirmPassword_onClick_sendPass(View view){
        EditText passwordET = (EditText) findViewById(R.id.ConfirmPassword_ET_password);
        String passwordSt = passwordET.getText().toString();
        UserDatabaseHelper DB = new UserDatabaseHelper(this);
        boolean loginStatus = DB.isValidUser(username, passwordSt);
        if (!loginStatus) {
            passwordET.setError("Incorrect password!");
        }
        else Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        //TODO: Figure out sharedpreferences, set user as logged in
    }

}
