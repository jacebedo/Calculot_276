package cmpt276.g14.mathappg14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button Main_LoginButton = (Button) findViewById(R.id.Main_LoginButton);
		Button Main_SignUpButton = (Button)findViewById(R.id.Main_SignUpButton);
		
		Main_LoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_Login(v);
				
			}
		}); // Main_LoginButton.setOnClickListener() End;
		
		Main_SignUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_SignUp(v);
				
			}
		}); // Main_SignUpButton.setOnClickListener() End;
		
	} // onCreate() Function End

	
	
	
	
	
	// Starts login activity on click.
	public void onClick_Login(View view){
		Intent goToLogin = new Intent(MainActivity.this,LoginActivity.class);
		startActivity(goToLogin);
	}
	
	// Starts sign up activity on click.
	public void onClick_SignUp(View view){
		Intent goToSignUp= new Intent(MainActivity.this,SignUpActivity.class);
		startActivity(goToSignUp);
	}
	
	
}
