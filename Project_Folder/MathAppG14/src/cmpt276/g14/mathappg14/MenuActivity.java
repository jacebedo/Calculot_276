package cmpt276.g14.mathappg14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cmpt276.g14.mathappg14.Topics.Topic11;
import cmpt276.g14.mathappg14.Topics.TopicsActivity;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		TextView MenuLearn = (TextView)findViewById(R.id.Menu_Learn);
		TextView MenuPractice = (TextView)findViewById(R.id.Menu_Practice);
		TextView MenuProfile = (TextView)findViewById(R.id.Menu_Profile);
		
		MenuLearn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goToTopic1_1 = new Intent(MenuActivity.this,Topic11.class);
				startActivity(goToTopic1_1);
			}
		}); // MenuLearn.setOnClickListener() End.
		
		
		MenuPractice.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goToPracticeActivity = new Intent(MenuActivity.this,PracticeActivity.class);
				startActivity(goToPracticeActivity);
			}
		}); // MenuPractice.setOnClickListener() End.
		
		MenuProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goToProfileActivity = new Intent(MenuActivity.this,ProfileActivity.class);
				startActivity(goToProfileActivity);
			}
		});
		
	}
}
