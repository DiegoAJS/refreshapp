package org.developerjs.refreshapp;

import com.google.firebase.messaging.FirebaseMessaging;

public class Aplicacion extends  android.app.Application {

    private static Aplicacion mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FirebaseMessaging.getInstance();
    }

    public static synchronized Aplicacion getInstance() {
        return mInstance;
    }
}
