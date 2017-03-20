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

public class IntegrationActivity extends YouTubeBaseActivity {
    private YouTubePlayerView yView;
    private YouTubePlayer.OnInitializedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integration);
        yView = (YouTubePlayerView) findViewById(R.id.intView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("dh__n9FVKA0&list=PLVwvyzz17cDI9d-P8FN69xr2zDz4bSuHF");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        yView.initialize("AIzaSyDCRngE35azPRQyUeK5GLH9jSUFnhpPn7g", listener);
        Button btn = (Button) findViewById(R.id.intBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(R.id.intBtn);
                EditText q1 = (EditText) findViewById(R.id.intq1);
                EditText q2 = (EditText) findViewById(R.id.intq2);
                EditText q3 = (EditText) findViewById(R.id.intq3);

                TextView integral = (TextView) findViewById(R.id.intR);
                if(integral.getCurrentTextColor() != Color.GREEN) {
                    if (q1.getText().toString().equals("f(x)g(x)"))
                        if (q2.getText().toString().equals("g(f(x))"))
                            if (q3.getText().toString().equals("-ln|cosx|"))
                                {
                                            integral.setTextColor(Color.GREEN);
                                            btn.setText("Back to Main Menu");
                                        }
                }
                else{
                    Intent profile = new Intent(IntegrationActivity.this, WhatToDo.class);
                    startActivity(profile);
                }
            }
        });
    }
}
