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

public class DatabaseRepository {

    private static final String TAG = DatabaseRepository.class.getSimpleName();
    private Executor mExecutor;

    // TODO 07. We'll pass the Lifecycle object just another parameter of the constructor
    public DatabaseRepository(Executor executor) {
        this.mExecutor = executor;
    }

    // TODO 03. We use the OnLifecycleEvent annotation to keep track of the desired events. The events
    //  will come from the lifecycle class.
    //  The method will be automatically triggered after MainActivity's onCreate method

    // TODO 05. We can add a function for each of the existing lifecycle events
    // OnStart, OnResume, OnPause, OnStop, OnDestroy

    public void getUser(final OnGetUserCallback callback) {
        this.mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    // TODO 06: We can get the current state of the lifecycle and use that value to make a decision
                    //  about whether some code should be executed or not.
                    callback.onGetUser("IVAN RODRIGUEZ-CONDE");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
