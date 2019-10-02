package com.ualr.applifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.concurrent.Executor;

/**
 * Created by irconde on 2019-10-01.
 */

// TODO 02. Create the LifecycleObserver

public class DatabaseRepository implements LifecycleObserver {

    private static final String TAG = DatabaseRepository.class.getSimpleName();
    private Executor mExecutor;
    private Lifecycle mLifecycle;

    public DatabaseRepository(Lifecycle lifecycle, Executor executor) {
        this.mExecutor = executor;
        mLifecycle = lifecycle;
    }

    // TODO 03. We use the OnLifecycleEvent annotation to keep track of the desired events. The events
    //  will come from the lifecycle class.
    //  The method will be automatically triggered after MainActivity's onCreate method

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateEvent() {
        Log.i(TAG, "Observer: ON_CREATE");
    }

    // TODO 05. We can add a function for each of the existing lifecycle events

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStartEvent() {
        Log.i(TAG, "Observer: ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeEvent() {
        Log.i(TAG, "Observer: ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.i(TAG, "Observer: ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.i(TAG, "Observer: ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.i(TAG, "Observer: ON_DESTROY");
    }

    public void getUser(final OnGetUserCallback callback) {
        this.mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    // TODO 06: We can get the current state of the lifecycle and use that value to make a decision
                    //  about whether some code should be executed or not.
                    if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
                        callback.onGetUser("IVAN RODRIGUEZ-CONDE");
                    } else {
                        Log.d(TAG, "Lifecycle is not at least started");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
