package org.developerjs.refreshapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.pojo.Actividad;
import org.developerjs.refreshapp.util.IntentUtiles;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsActividadActivity extends AppCompatActivity {

    public static final String TAG = DetailsActividadActivity.class.getSimpleName().toLowerCase();

    public static final String ACTIVITY_ACTIVIDAD          = "DetailsActividadActivity.actividad";

    private TextView mTitulo,mOrganizador,mContenido,mLugar,mFechaPublicacion,mFechaActividad;
    private ImageView mFoto,mVideo,mLocalizacion,mWeb;

    private Actividad actividad;
    private Context context=this;

    public static void createInstance(Context context, Actividad actividad) {
        Intent intent = getLaunchIntent(context,actividad);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context,Actividad actividad) {
        Intent intent = new Intent(context, DetailsActividadActivity.class);
        intent.putExtra(ACTIVITY_ACTIVIDAD,actividad);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actividad);
        setSupportActionBar(toolbar);

        actividad=(Actividad) getIntent().getSerializableExtra(ACTIVITY_ACTIVIDAD);

        mTitulo             =(TextView)findViewById(R.id.tvTituloActividad);
        mOrganizador        =(TextView)findViewById(R.id.tvOrganizadorActividad);
        mContenido          =(TextView) findViewById(R.id.tvContenidoActividad);
        mFechaPublicacion   =(TextView)findViewById(R.id.tvFechaPublicacionActividad);
        mFechaActividad     =(TextView)findViewById(R.id.tvFechaActividad);
        mLugar              =(TextView)findViewById(R.id.tvLugarActividad);

        mFoto               =(ImageView) findViewById(R.id.ivFotoActividad);
        mVideo              =(ImageView) findViewById(R.id.ivVideoActividad);
        mLocalizacion       =(ImageView) findViewById(R.id.ivMapActividad);
        mWeb                =(ImageView) findViewById(R.id.ivWebActividad);

        setTitle("Detalle");

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");

        mTitulo.setText(actividad.getTitulo());
        mOrganizador.setText(actividad.getOrganizador());
        mContenido.setText(actividad.getContenido());
        mFechaPublicacion.setText(format.format(actividad.getUpdate()));
        mFechaActividad.setText(format.format(actividad.getUpdate()));
        mLugar.setText(actividad.getLugar());

        if (actividad.getFoto()!=null){
            Glide.with(this).load(actividad.getFoto()).into(mFoto);
        } else{
            mFoto.setVisibility(View.GONE);
        }

        if(actividad.getVideo()!=null){
            mVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,actividad.getVideo());
                }
            });
        }else {
            mVideo.setVisibility(View.GONE);
        }

        if(actividad.getLocalizacion()!=null){
            mLugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentMap(context,actividad.getLocalizacion());
                }
            });
        }

    }
}
