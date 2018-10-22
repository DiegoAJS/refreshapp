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

import java.util.Date;
import java.util.Map;


public class MiFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "NOTICIAS_Servicio";

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

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

            /*Actividad actividad = new Actividad();
            actividad.setContenido("contenido x");
            actividad.setTitulo("titulo x");
            actividad.setCreate(new Date());
            actividad.setUpdate(new Date());
            actividad.setOrganizador("organiza x");
            actividad.setFecha_actividad(new Date());
            actividad.setLugar("en algun lugar");
            actividad.setLink("www.face.com");*/

            //setPendingIntentActividad(actividad);
            //createNotificationChannel();
            //createNotification();
            getObject();
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

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

    private void getObject(){

        DocumentReference docRef = db.collection(type).document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(type.equals("actividades")){
                    Actividad actividad= documentSnapshot.toObject(Actividad.class);
                    setPendingIntentActividad(actividad);
                    createNotificationChannel();
                    createNotification();
                }else if(type.equals("grupos")){
                    Grupo grupo= documentSnapshot.toObject(Grupo.class);
                }else if(type.equals("noticias")){
                    Noticia noticia= documentSnapshot.toObject(Noticia.class);
                }

            }
        });

    }
}