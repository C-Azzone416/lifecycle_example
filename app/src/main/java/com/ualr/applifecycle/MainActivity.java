package com.ualr.applifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.Executors;

// TODO 03. In your device/emulator access Settings > Developer options
// TODO 04. Under Apps, turn ON "Don't keep activities"
// TODO 05. Run the app and after 2-3 seconds press the home button to destroy activity

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logMessage(R.string.on_create);
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage(R.string.on_pause);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMessage(R.string.on_resume);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logMessage(R.string.on_restart);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMessage(R.string.on_start);

        DatabaseRepository database = new DatabaseRepository(Executors.newSingleThreadExecutor());
        database.getUser(new OnGetUserCallback() {
            @Override
            public void onGetUser(final String user) {
                Log.d(TAG, String.format("Callback called. We retrive user: %s", user));

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        TextView userLabel = findViewById(R.id.user_label);
                        userLabel.setText(user);
                    }
                });

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMessage(R.string.on_stop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logMessage(R.string.on_destroy);
    }

    public void destroyActivity(View view) {
        this.finishAndRemoveTask();
    }

    private void logMessage(int stringResId) {
        Log.d(TAG, getString(stringResId));
    }

}
