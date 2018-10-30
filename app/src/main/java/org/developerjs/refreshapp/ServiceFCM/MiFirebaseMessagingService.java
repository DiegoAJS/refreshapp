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
    public final static String ID_CHANNEL           ="NOTIFICACION_APP";
    //public final static String ID_CHANNEL_GRUPO     = "NOTIFICACION_GRUPO";
    //public final static String ID_CHANNEL_NOTICIA   = "NOTIFICACION_NOTICIA";

    public static final String ACTIVITY_ACTIVIDAD           = "DetailsActividadActivity.actividad";
    public static final String ACTIVITY_GRUPO               = "DetailsGrupoActivity.grupo";
    public static final String ACTIVITY_NOTICIA             = "DetailsNoticiaActivity.noticia";

    public static final int ID_NOTIFICATION_ACTIVIDAD   = 10001;
    public static final int ID_NOTIFICATION_GRUPO       = 10002;
    public static final int ID_NOTIFICATION_NOTICIA     = 10003;

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

        /*Intent intent = new Intent(this, DetailsNoticiaActivity.class);
        intent.putExtra(ACTIVITY_NOTICIA,noticia);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailsNoticiaActivity.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);*/

        Intent intent = new Intent(this, DetailsNoticiaActivity.class);
        intent.putExtra(ACTIVITY_NOTICIA,noticia);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

    }

    private void createNotificationChannel(String channel){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "NoticacionApp";
            String descripcion = "Noticacion";

            /*NotificationChannel notificationChannel = new NotificationChannel(channel, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);*/

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel(channel, name, importance);
            mChannel.setDescription(descripcion);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(mChannel);
        }
    }

    private void createNotification(int id, String channel){

        /*NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(id, builder.build());*/

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(id, mBuilder.build());

    }

    private void getObject(){

        DocumentReference docRef = db.collection(type).document(id);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(type.equals("actividades")){
                    Actividad actividad = documentSnapshot.toObject(Actividad.class);
                    setPendingIntentActividad(actividad);
                    createNotificationChannel(ID_CHANNEL);
                    createNotification(ID_NOTIFICATION_ACTIVIDAD,ID_CHANNEL);
                }else if(type.equals("grupos")){
                    Item item = documentSnapshot.toObject(Grupo.class);
                    setPendingIntentGrupo((Grupo) item);
                    createNotificationChannel(ID_CHANNEL);
                    createNotification(ID_NOTIFICATION_GRUPO,ID_CHANNEL);
                }else if(type.equals("noticias")){
                    Noticia noticia = documentSnapshot.toObject(Noticia.class);
                    setPendingIntentNoticia(noticia);
                    createNotificationChannel(ID_CHANNEL);
                    createNotification(ID_NOTIFICATION_NOTICIA,ID_CHANNEL);
                    //notificacionNoticia(noticia);
                }
            }
        });

    }


    public void notificacionNoticia(Noticia noticia){

        Intent intent = new Intent(this, DetailsNoticiaActivity.class);
        intent.putExtra(ACTIVITY_NOTICIA,noticia);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}