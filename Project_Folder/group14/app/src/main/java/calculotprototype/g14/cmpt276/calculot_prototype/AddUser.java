package calculotprototype.g14.cmpt276.calculot_prototype;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ryan on 3/10/2017.
 */

public class AddUser extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
    }

    public void constRun(){
        EditText firstNameET = (EditText) findViewById(R.id.addUser_firstName);
        EditText usernameET = (EditText) findViewById(R.id.addUser_username);
        EditText passwordET = (EditText) findViewById(R.id.addUser_password);
        EditText password2ET = (EditText) findViewById(R.id.addUser_retypepassword);

        String passwordSt = passwordET.getText().toString();
        String password2St = password2ET.getText().toString();

        if (passwordSt.equals(password2St)){
            Toast.makeText(this, "They're the same!", Toast.LENGTH_SHORT).show();
        }
        //3/10/17 - Not working, maybe put it after button press?

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do stuff
            }
        });*/
    }

    public void add_user_onClick_signUp(View view){
        Toast.makeText(this, "Clicked Sign Up... woo!", Toast.LENGTH_SHORT).show();
    }
}
