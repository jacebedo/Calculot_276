package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by peter on 3/13/2017.
 */

public class Profile extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        String username = pref.getString("username",null); //Gets current logged in username from SharedPreferences
        TextView usernameTV = (TextView) findViewById(R.id.profile_username);
        usernameTV.setText(username);

    }

}
