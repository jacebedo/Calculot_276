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
        EditText firstNameET = (EditText) findViewById(R.id.addUser_firstName);
        EditText usernameET = (EditText) findViewById(R.id.addUser_username);
        EditText passwordET = (EditText) findViewById(R.id.addUser_password);
        EditText password2ET = (EditText) findViewById(R.id.addUser_retypepassword);

        String firstNameSt = firstNameET.getText().toString();
        String usernameSt  = usernameET.getText().toString();
        String passwordSt  = passwordET.getText().toString();
        String password2St = password2ET.getText().toString();

        UserDatabaseHelper DB = new UserDatabaseHelper(this);

        if (!(passwordSt.equals(password2St))){
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }
        else if (DB.userNotTaken(usernameSt)){
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
