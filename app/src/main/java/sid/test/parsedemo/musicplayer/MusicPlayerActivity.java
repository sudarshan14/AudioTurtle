package sid.test.parsedemo.musicplayer;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import sid.test.parsedemo.R;


public class MusicPlayerActivity extends AppCompatActivity {

    public TextView songName, startTimeField, endTimeField;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private ImageButton playButton, pauseButton;
    public static int oneTimeOnly = 0;
    static MediaPlayer mPlayer;
    String possibleEmail = "emails \n";
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // ParseUser currentUser = ParseUser.getCurrentUser();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // NULL POINTER EXCEPTION here
        getSupportActionBar().setHomeButtonEnabled(true);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail += "\n"+account.name;

            }
        }
        TextView email = (TextView)findViewById(R.id.textView);
        email.setText(possibleEmail);
    }
    public void playonline(View v) {

        // String url =
        // "http://android.programmerguru.com/wp-content/uploads/2013/04/hosannatelugu.mp3";
        boolean on = ((ToggleButton) v).isChecked();

        if (on) {
            // Disable vibrate

            mPlayer = new MediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource("http://sidangel.comxa.com/audiofiles/music/1942%20A%20Love%20Story%20-%20Rooth%20na%20jana.mp3");
            } catch (IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(),
                        "You might not set the URI correctly!",
                        Toast.LENGTH_LONG).show();
            } catch (SecurityException e) {
                Toast.makeText(getApplicationContext(),
                        "You might not set the URI correctly!",
                        Toast.LENGTH_LONG).show();
            } catch (IllegalStateException e) {
                Toast.makeText(getApplicationContext(),
                        "You might not set the URI correctly!",
                        Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mPlayer.prepare();
            } catch (IllegalStateException e) {
                Toast.makeText(getApplicationContext(),
                        "You might not set the URI correctly!",
                        Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),
                        "You might not set the URI correctly!",
                        Toast.LENGTH_LONG).show();
            }
            mPlayer.start();
        } else {
            if (mPlayer != null && mPlayer.isPlaying()) {
                mPlayer.stop();
            }
        }
    }

}