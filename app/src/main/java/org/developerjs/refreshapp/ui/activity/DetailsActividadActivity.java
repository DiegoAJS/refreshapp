package org.developerjs.refreshapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.pojo.Actividad;
import org.developerjs.refreshapp.ui.dialog.ZoomDialog;
import org.developerjs.refreshapp.util.IntentUtiles;

import java.text.SimpleDateFormat;


public class DetailsActividadActivity extends AppCompatActivity {

    public static final String TAG = DetailsActividadActivity.class.getSimpleName();

    public static final String ACTIVITY_ACTIVIDAD           = "DetailsActividadActivity.actividad";
    public static final String ACTIVITY_ACTIVIDAD_ID        = "DetailsActividadActivity.actividad.id";

    private TextView mTitulo,mDia,mMes,mHora, mContenido,mDireccion,mLink,mFechaPublicacion,mOrganizador;
    private ImageView mFoto;
    private FloatingActionButton mFloatingActionButtonVideo;
    private CardView mCVDireccion,mCVLink;

    private Actividad actividad;
    private Context context=this;

    private ZoomDialog zoomDialog;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void createInstance(Context context, Actividad actividad) {
        Intent intent = getLaunchIntent(context,actividad);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context,Actividad actividad) {
        Intent intent = new Intent(context, DetailsActividadActivity.class);
        intent.putExtra(ACTIVITY_ACTIVIDAD,actividad);
        return intent;
    }

    public static Intent getLaunchIntent(Context context,String id) {
        Intent intent = new Intent(context, DetailsActividadActivity.class);
        intent.putExtra(ACTIVITY_ACTIVIDAD_ID,id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actividad);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getStringExtra(ACTIVITY_ACTIVIDAD_ID)!=null){
            //getActividad(getIntent().getStringExtra(ACTIVITY_ACTIVIDAD_ID));
            Log.d(TAG,getIntent().getStringExtra(ACTIVITY_ACTIVIDAD_ID));
        }else {
            actividad=(Actividad) getIntent().getSerializableExtra(ACTIVITY_ACTIVIDAD);
            update();
        }

        mTitulo=(TextView)findViewById(R.id.tvTituloActividad);
        mDia=(TextView)findViewById(R.id.tvDiaActividad);
        mMes=(TextView)findViewById(R.id.tvMesActividad);
        mHora=(TextView)findViewById(R.id.tvHoraActividad);

        mOrganizador        =(TextView) findViewById(R.id.tvOrganizadorActividad);
        mContenido          =(TextView) findViewById(R.id.tvContenidoActividad);
        mFechaPublicacion   =(TextView) findViewById(R.id.tvFechaPublicacionActividad);
        mDireccion          =(TextView) findViewById(R.id.tvDireccionActividad);
        mLink               =(TextView) findViewById(R.id.tvLinkActividad);

        mFoto               =(ImageView) findViewById(R.id.ivFotoActividad);

        mCVDireccion=(CardView) findViewById(R.id.cvDireccionActividad);
        mCVLink=(CardView) findViewById(R.id.cvLinkActividad);

        mFloatingActionButtonVideo=(FloatingActionButton) findViewById(R.id.floatingActionButtonVideoActividad);


        setTitle("Detalle actividad");


    }

    public void update(){

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
        SimpleDateFormat formatMes = new SimpleDateFormat("MMM");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");

        mTitulo.setText(actividad.getTitulo());
        mDia.setText(String.valueOf(actividad.getFecha_actividad().getDay()));
        mMes.setText(formatMes.format(actividad.getFecha_actividad()));
        mHora.setText(formatHora.format(actividad.getFecha_actividad()));
        mOrganizador.setText(actividad.getOrganizador());
        mContenido.setText(actividad.getContenido());
        mFechaPublicacion.setText(format.format(actividad.getUpdate()));
        mDireccion.setText(actividad.getLugar());
        mLink.setText(actividad.getLink());

        if (actividad.getFoto()!=null){
            Glide.with(this).load(actividad.getFoto()).into(mFoto);
            zoomDialog = ZoomDialog.getInstanceDialog(actividad.getFoto());
            mFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomDialog.show(getSupportFragmentManager(),TAG);
                }
            });
        } else{
            mFoto.setVisibility(View.GONE);
        }

        if(actividad.getVideo()!=null){
            mFloatingActionButtonVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,actividad.getVideo());
                }
            });
        }else {
            mFloatingActionButtonVideo.setVisibility(View.GONE);
        }

        if(actividad.getLocalizacion()!=null){
            mCVDireccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentMap(context,actividad.getLocalizacion());
                }
            });
        }
        if (actividad.getLink()!=null){
            mCVLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,actividad.getLink());
                }
            });
        }else {
            mCVLink.setVisibility(View.GONE);
        }
    }

    public void getActividad(String id){

    }
}
