package com.ualr.applifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.Executors;

// TODO 02. Create the LifecycleObserver class

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DatabaseRepository database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logMessage(R.string.on_create);
        // TODO 04. We have to create a new instance of the Observer class and bind it to the lifecycle that we want to monitors
        database = new DatabaseRepository(Executors.newSingleThreadExecutor());
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
