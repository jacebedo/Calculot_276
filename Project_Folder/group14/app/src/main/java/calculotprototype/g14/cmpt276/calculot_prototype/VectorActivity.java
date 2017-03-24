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

public class VectorActivity extends YouTubeBaseActivity {
    private YouTubePlayerView yView;
    private YouTubePlayer.OnInitializedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        yView = (YouTubePlayerView) findViewById(R.id.vectorView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("odhAVmAahb4");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        yView.initialize("AIzaSyDCRngE35azPRQyUeK5GLH9jSUFnhpPn7g", listener);
        Button btn = (Button) findViewById(R.id.vectorbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(R.id.vectorbtn);
                EditText q1 = (EditText) findViewById(R.id.inputV1);
                EditText q2 = (EditText) findViewById(R.id.inputV2);
                EditText q3 = (EditText) findViewById(R.id.inputV3);
                EditText q4 = (EditText) findViewById(R.id.inputV4);
                EditText q5 = (EditText) findViewById(R.id.inputV5);
                EditText q6 = (EditText) findViewById(R.id.inputV6);
                TextView dif = (TextView) findViewById(R.id.vectorText);
                if(dif.getCurrentTextColor() != Color.GREEN) {
                    if (q1.getText().toString().equals("direction"))
                        if (q2.getText().toString().equals("velocity"))
                            if (q3.getText().toString().equals("(x2-x1,y2-y1)"))
                                if (q4.getText().toString().equals("(x1+x2,y1+y2)"))
                                    if (q5.getText().toString().equals("(10*a,10*b)"))
                                        if(q6.getText().toString().equals("a*c+b*d"))
                                       {
                                            dif.setTextColor(Color.GREEN);
                                            btn.setText("Back to Main Menu");
                                        }
                }
                else{
                    Intent profile = new Intent(VectorActivity.this, WhatToDo.class);
                    startActivity(profile);
                }
            }
        });

    }
}
