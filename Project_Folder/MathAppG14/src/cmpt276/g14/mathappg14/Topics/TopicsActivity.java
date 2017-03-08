package cmpt276.g14.mathappg14.Topics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cmpt276.g14.mathappg14.MenuActivity;
import cmpt276.g14.mathappg14.R;

public class TopicsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topics);
		
		TextView Topic1_1_Button = (TextView)findViewById(R.id.Topic1_1);
		Topic1_1_Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goToMenu = new Intent(TopicsActivity.this,MenuActivity.class);
				startActivity(goToMenu);
			}
		});
	}


}
