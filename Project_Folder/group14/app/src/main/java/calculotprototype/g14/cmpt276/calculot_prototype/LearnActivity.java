package calculotprototype.g14.cmpt276.calculot_prototype;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class LearnActivity extends YouTubeBaseActivity {
    private YouTubePlayerView yView;
    private YouTubePlayer.OnInitializedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        yView = (YouTubePlayerView) findViewById(R.id.diffView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("rAof9Ld5sOg");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        yView.initialize("AIzaSyDCRngE35azPRQyUeK5GLH9jSUFnhpPn7g", listener);
        Button btn = (Button) findViewById(R.id.learnbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText q11 = (EditText) findViewById(R.id.inputq11);
                EditText q12 = (EditText) findViewById(R.id.inputq12);
                EditText q21 = (EditText) findViewById(R.id.inputq21);
                EditText q31 = (EditText) findViewById(R.id.inputq31);
                TextView dif = (TextView) findViewById(R.id.diffR);
                if(q11.getText().toString().equals("y") && q12.getText().toString().equals("x"))
                    if(q21.getText().toString().equals("limit"))
                        if(q31.getText().toString().equals("A"))
                            dif.setTextColor(Color.GREEN);
            }
        });

    }
}
