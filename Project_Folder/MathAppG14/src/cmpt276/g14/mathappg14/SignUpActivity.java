package cmpt276.g14.mathappg14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		Button SignUp_SignUpButton = (Button)findViewById(R.id.button_SignUp_SignUp);
		SignUp_SignUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_SignUp(v);
			}
		});
	}
	
	
	// For UI Mock-up purposes only
	public void onClick_SignUp(View view) {
		Intent goToMainMenu = new Intent(SignUpActivity.this, MainActivity.class);
		Toast.makeText(getApplicationContext(), "Clicking this button will register the valid user and go back to the main menu", Toast.LENGTH_LONG).show();
		startActivity(goToMainMenu);
	}
}
