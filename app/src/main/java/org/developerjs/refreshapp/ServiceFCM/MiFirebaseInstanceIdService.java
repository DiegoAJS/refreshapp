package org.developerjs.refreshapp.ServiceFCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MiFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String TAG = "NOTICIAS_Servicio";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        Log.d(TAG, "Token: " + token);

        enviarTokenAlServidor(token);
    }

    private void enviarTokenAlServidor(String token) {
        // Enviar token al servidor
    }
}