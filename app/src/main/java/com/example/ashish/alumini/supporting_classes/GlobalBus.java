package com.example.ashish.alumini.supporting_classes;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by ashish on 19/8/16.
 */
public class GlobalBus extends Bus {
    private static final GlobalBus mGlobalBus = new GlobalBus();
    private final Handler handler = new Handler(Looper.getMainLooper());

    private GlobalBus() {
        super(ThreadEnforcer.ANY,"default_bus");
    }

    public static GlobalBus getInstance() {
        return mGlobalBus;
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    GlobalBus.super.post(event);
                }
            });
        }
    }

}

