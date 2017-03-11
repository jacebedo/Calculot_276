package calculotprototype.g14.cmpt276.calculot_prototype;

import android.os.Bundle;
import android.view.View;
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

    public void add_user_onClick_signUp(View view){
        Toast.makeText(this, "Clicked Sign Up... woo!", Toast.LENGTH_SHORT).show();
    }
}
