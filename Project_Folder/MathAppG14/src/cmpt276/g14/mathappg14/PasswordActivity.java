package cmpt276.g14.mathappg14;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cmpt276.g14.mathappg14.Topics.TopicsActivity;

public class PasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		
		Button goToTopicsActivity = (Button)findViewById(R.id.button_login_enter_password_login);
		goToTopicsActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_login_enter_password_login(v);
			}
		});
		
		
		
	}

	public void onClick_login_enter_password_login(View view){
		//get password from editText
		//check password
		//set user as "Signed in"
		Intent goToTopics = new Intent(PasswordActivity.this,TopicsActivity.class);
		startActivity(goToTopics);
	}
	
	
}
