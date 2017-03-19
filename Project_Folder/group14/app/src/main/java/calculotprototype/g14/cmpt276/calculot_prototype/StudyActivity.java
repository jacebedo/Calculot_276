package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        TextView btn = (TextView) findViewById(R.id.toDiff);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diff = new Intent(StudyActivity.this, LearnActivity.class);
                startActivity(diff);
            }
        });
    }
}
