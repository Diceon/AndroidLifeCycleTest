package lt.vtvpmc.ems.zp182.ap.androidlifecycle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView timerText;
    private int seconds = 0;
    private boolean isRunning = false;
    private boolean started = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = (TextView) findViewById(R.id.timerText);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            started = savedInstanceState.getBoolean("started");
        }
        runTimer();

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
        started = isRunning;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
        outState.putBoolean("started", started);
    }

    private void runTimer() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = seconds % 3600 / 60;
                int sec = seconds % 60;

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, sec);
                timerText.setText(time);

                if (isRunning) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

    }

    public void timerStart(View view) {
        isRunning = true;
    }

    public void timerPause(View view) {
        isRunning = false;
    }

    public void timerReset(View view) {
        isRunning = false;
        seconds = 0;
    }
}
