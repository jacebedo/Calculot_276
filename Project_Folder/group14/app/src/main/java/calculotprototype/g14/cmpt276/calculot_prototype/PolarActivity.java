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

public class PolarActivity extends YouTubeBaseActivity {
    private YouTubePlayerView yView;
    private YouTubePlayer.OnInitializedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polar);
        yView = (YouTubePlayerView) findViewById(R.id.polarView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("tKi05dfUhAA&t=69s");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        yView.initialize("AIzaSyDCRngE35azPRQyUeK5GLH9jSUFnhpPn7g", listener);
        Button btn = (Button) findViewById(R.id.polarbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(R.id.polarbtn);
                EditText q1 = (EditText) findViewById(R.id.inputP1);
                EditText q2 = (EditText) findViewById(R.id.inputP2);
                EditText q3 = (EditText) findViewById(R.id.inputP3);
                EditText q4 = (EditText) findViewById(R.id.inputP4);
                EditText q5 = (EditText) findViewById(R.id.inputP5);
                TextView dif = (TextView) findViewById(R.id.polarText);
                if(dif.getCurrentTextColor() != Color.GREEN) {
                    if (q1.getText().toString().equals("r*cos(theta)"))
                        if (q2.getText().toString().equals("r*sin(theta)"))
                            if (q3.getText().toString().equals("(x^2+y^2)^(1/2)"))
                                if (q4.getText().toString().equals("y/x"))
                                    if (q5.getText().toString().equals("tan^(-1)(y/x)"))
                                        {
                                            dif.setTextColor(Color.GREEN);
                                            btn.setText("Back to Main Menu");
                                        }
                }
                else{
                    Intent profile = new Intent(PolarActivity.this, WhatToDo.class);
                    startActivity(profile);
                }
            }
        });
    }
}
