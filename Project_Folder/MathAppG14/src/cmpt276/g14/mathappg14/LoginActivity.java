package cmpt276.g14.mathappg14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	
		// For release version, generate ALL buttons in runtime.
		Button goToPasswordActivity_user1 = (Button)findViewById(R.id.button_login_user_1);
		Button goToPasswordActivity_user2 = (Button)findViewById(R.id.button_login_user_2);
		Button goToPasswordActivity_user3 = (Button)findViewById(R.id.button_login_user_3);
		Button goToPasswordActivity_user4 = (Button)findViewById(R.id.button_login_user_4);
		
		goToPasswordActivity_user1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_UserButton(v);
			}
		});

		goToPasswordActivity_user2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_UserButton(v);
			}
		});
		
		goToPasswordActivity_user3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_UserButton(v);
			}
		});
		
		goToPasswordActivity_user4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_UserButton(v);
			}
		});
		
	
	
	}
	
	// There will be a different password prompt for every user registered.
	public void onClick_UserButton(View v) {
		Intent goToPasswordActivity = new Intent(LoginActivity.this,PasswordActivity.class);
		startActivity(goToPasswordActivity);
		
	}
	
	
	
}
