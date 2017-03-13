package calculotprototype.g14.cmpt276.calculot_prototype;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import calculotprototype.g14.cmpt276.calculot_prototype.Classes.User;
import calculotprototype.g14.cmpt276.calculot_prototype.Databases.UserDatabaseHelper;

/**
 * Created by Ryan on 3/10/2017.
 */

public class AddUser extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
    }

    public void add_user_onClick_signUp(View view){ //When sign up button is pressed
        //TODO: on last field, have next button submit form
        EditText firstNameET = (EditText) findViewById(R.id.addUser_firstName);
        EditText usernameET = (EditText) findViewById(R.id.addUser_username);
        EditText passwordET = (EditText) findViewById(R.id.addUser_password);
        EditText password2ET = (EditText) findViewById(R.id.addUser_retypepassword);

        boolean wrong = false;
        if (firstNameET.getText().toString().trim().equals("")) { firstNameET.setError("This field cannot be left blank."); wrong = true; }
        if (usernameET.getText().toString().trim().equals("")) { usernameET.setError("This field cannot be left blank."); wrong = true; }
        if (passwordET.getText().toString().equals("")) { passwordET.setError("This field cannot be left blank."); wrong = true; }
        if (!password2ET.getText().toString().equals(passwordET.getText().toString())) { password2ET.setError("Passwords do not match."); wrong = true; }
        if (wrong) return; //Obviously don't continue if fields aren't correct

        String firstNameSt = firstNameET.getText().toString().trim(); //trim removes trailing spaces
        String usernameSt  = usernameET.getText().toString().trim();
        String passwordSt  = passwordET.getText().toString();
        String password2St = password2ET.getText().toString();

        UserDatabaseHelper DB = new UserDatabaseHelper(this);

        if (!(passwordSt.equals(password2St)))password2ET.setError("Passwords do not match!");
        else if (DB.userNotTaken(usernameSt)){ //Do if username is available
            User newUser = new User(usernameSt, firstNameSt, passwordSt);
            DB.insertUser(newUser);
            Log.i("InsertUser","User inserted!");
            Toast.makeText(this, "User created!", Toast.LENGTH_SHORT).show();
            finish();
            //TODO: Set shared preferences so user is logged in
        }
        else Toast.makeText(this, "Username already taken.", Toast.LENGTH_SHORT).show();
    }
}
