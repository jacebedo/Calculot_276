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
        TextView btn1 = (TextView) findViewById(R.id.toInt);
        TextView btn2 = (TextView) findViewById(R.id.toVectors);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diff = new Intent(StudyActivity.this, LearnActivity.class);
                startActivity(diff);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integral = new Intent(StudyActivity.this, IntegrationActivity.class);
                startActivity(integral);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vector = new Intent(StudyActivity.this, VectorActivity.class);
                startActivity(vector);
            }
        });
    }
}
