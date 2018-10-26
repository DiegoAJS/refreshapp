package org.developerjs.refreshapp.ServiceFCM;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.developerjs.refreshapp.Aplicacion;
import org.developerjs.refreshapp.MainActivity;
import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.pojo.Actividad;
import org.developerjs.refreshapp.pojo.Grupo;
import org.developerjs.refreshapp.pojo.Item;
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

import java.util.Date;
import java.util.Map;


public class MiFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "NOTICIAS_Servicio";

    private PendingIntent pendingIntent;
    public final static String CHANNEL_ID = "NOTIFICACION_REFRESHAPP";
    public final static int NOTIFICACION_ID = 1001;

    private String type;
    private String id;
    private String title;
    private String body;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();
        Log.d(TAG, "Mensaje recibido de: " + from);

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notificación: " + remoteMessage.getNotification().getTitle()+", "+remoteMessage.getNotification().getBody());
            title   =remoteMessage.getNotification().getTitle();
            body    =remoteMessage.getNotification().getBody();
        }

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data: " + remoteMessage.getData());

            type=remoteMessage.getData().get("type");
            id=remoteMessage.getData().get("id");

            getObject();
            /*if(type.equals("actividades")){
                setPendingIntentActividad();
                createNotificationChannel();
                createNotification();
            }else if(type.equals("grupos")){

                setPendingIntentGrupo();
                createNotificationChannel();
                createNotification();
            }else if(type.equals("noticias")){
                setPendingIntentNoticia();
                createNotificationChannel();
                createNotification();
            }*/
        }
    }

    private void setPendingIntentActividad(Actividad actividad){
        Intent intent = new Intent(this, DetailsActividadActivity.class);
        intent.putExtra(DetailsActividadActivity.ACTIVITY_ACTIVIDAD,actividad);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailsActividadActivity.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void setPendingIntentGrupo(Grupo grupo){
        Intent intent = new Intent(this, DetailsGrupoActivity.class);
        intent.putExtra(DetailsGrupoActivity.ACTIVITY_GRUPO,grupo);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailsGrupoActivity.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void setPendingIntentNoticia(Noticia noticia){
        Intent intent = new Intent(this, DetailsNoticiaActivity.class);
        intent.putExtra(DetailsNoticiaActivity.ACTIVITY_NOTICIA,noticia);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailsNoticiaActivity.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion_App";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Aplicacion.getInstance());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

    private void getObject(){

        DocumentReference docRef = db.collection(type).document(id);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(type.equals("actividades")){
                    Actividad actividad = documentSnapshot.toObject(Actividad.class);
                    setPendingIntentActividad(actividad);
                    createNotificationChannel();
                    createNotification();
                }else if(type.equals("grupos")){
                    Item item = documentSnapshot.toObject(Grupo.class);
                    setPendingIntentGrupo((Grupo) item);
                    createNotificationChannel();
                    createNotification();
                }else if(type.equals("noticias")){
                    Noticia noticia = documentSnapshot.toObject(Noticia.class);
                    setPendingIntentNoticia(noticia);
                    createNotificationChannel();
                    createNotification();
                }
            }
        });

    }
}