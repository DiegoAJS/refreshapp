package org.developerjs.refreshapp;

import android.content.Context;

public class Aplicacion extends  android.app.Application {

    private static Aplicacion mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized Aplicacion getInstance() {
        return mInstance;
    }
}
