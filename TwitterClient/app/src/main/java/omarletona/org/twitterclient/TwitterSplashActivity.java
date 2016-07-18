package omarletona.org.twitterclient;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.twitter.sdk.android.Twitter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import omarletona.org.twitterclient.main.ui.MainActivity;

public class TwitterSplashActivity extends AppCompatActivity {
    @Bind(R.id.image_view)
    ImageView imageView;

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 1110;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_twitter_splash);
        ButterKnife.bind(this);
        imageView.animate()
                .setStartDelay(500)
                .setDuration(1000)
                .scaleX(20)
                .scaleY(20)
                .alpha(0);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent(TwitterSplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

    }


}
