package calculotprototype.g14.cmpt276.calculot_prototype;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ComplexActivity extends YouTubeBaseActivity {
    private YouTubePlayerView yView;
    private YouTubePlayer.OnInitializedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        yView = (YouTubePlayerView) findViewById(R.id.complexView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("SP-YJe7Vldo");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        yView.initialize("AIzaSyDCRngE35azPRQyUeK5GLH9jSUFnhpPn7g", listener);
        Button btn = (Button) findViewById(R.id.complexbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(R.id.complexbtn);
                EditText q1 = (EditText) findViewById(R.id.inputC1);
                EditText q2 = (EditText) findViewById(R.id.inputC2);
                EditText q3 = (EditText) findViewById(R.id.inputC3);
                EditText q4 = (EditText) findViewById(R.id.inputC4);
                EditText q5 = (EditText) findViewById(R.id.inputC5);
                EditText q6 = (EditText) findViewById(R.id.inputC6);
                TextView complex = (TextView) findViewById(R.id.complexText);
                if(complex.getCurrentTextColor() != Color.GREEN) {
                    if (q1.getText().toString().equals("i"))
                        if (q2.getText().toString().equals("complex"))
                            if (q3.getText().toString().equals("imaginary part"))
                                if (q4.getText().toString().equals("(a+c)+(b+d)*i"))
                                    if (q5.getText().toString().equals("a^2+b^2"))
                                        if(q6.getText().toString().equals("cosx+i*sinx"))
                                        {
                                            complex.setTextColor(Color.GREEN);
                                            btn.setText("Back to Main Menu");
                                        }
                }
                else{
                    Intent profile = new Intent(ComplexActivity.this, WhatToDo.class);
                    startActivity(profile);
                }
            }
        });
    }
}
