package org.developerjs.refreshapp.ServiceFCM;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.developerjs.refreshapp.MainActivity;
import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.pojo.Actividad;
import org.developerjs.refreshapp.pojo.Grupo;
import org.developerjs.refreshapp.pojo.Noticia;
import org.developerjs.refreshapp.ui.activity.DetailsActividadActivity;
import org.developerjs.refreshapp.ui.activity.DetailsGrupoActivity;
import org.developerjs.refreshapp.ui.activity.DetailsNoticiaActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.TaskStackBuilder;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;


public class MiFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "NOTICIAS_Servicio";

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    private final static int NOTIFICACION_NOTICIA=1;
    private final static int NOTIFICACION_ACTIVIDAD=2;
    private final static int NOTIFICACION_GRUPO=3;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();
        Log.d(TAG, "Mensaje recibido de: " + from);

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notificación: " + remoteMessage.getNotification().getBody());

            mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data: " + remoteMessage.getData());
        }

    }

    private void mostrarNotificacion(String title, String body) {

        setPendingIntent(NOTIFICACION_NOTICIA);
        createNotificationChannel();
        createNotification(title,body);

    }

    private void setPendingIntent(int type){

        Intent intent;
        TaskStackBuilder stackBuilder;
        switch (type){
            case NOTIFICACION_NOTICIA:
                intent = DetailsNoticiaActivity.getLaunchIntent(this,new Noticia());
                stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(DetailsNoticiaActivity.class);break;
            case NOTIFICACION_ACTIVIDAD:
                intent = DetailsActividadActivity.getLaunchIntent(this,new Actividad());
                stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(DetailsActividadActivity.class);break;
            default:
                intent = DetailsGrupoActivity.getLaunchIntent(this,new Grupo());
                stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(DetailsGrupoActivity.class);break;

        }
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(String titulo,String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_noticias_white);
        builder.setContentTitle(titulo);
        builder.setContentText(text);
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }


}