package org.developerjs.refreshapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.interfaces.ItemClickListener;
import org.developerjs.refreshapp.pojo.Grupo;
import org.developerjs.refreshapp.util.IntentUtiles;

import java.text.SimpleDateFormat;
import java.util.Map;

public class DetailsGrupoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = DetailsGrupoActivity.class.getSimpleName();

    public static final String ACTIVITY_GRUPO = "DetailsGrupoActivity.grupo";

    private TextView mNombre,mContenido;
    private ImageView mFoto,mCelular,mFacebook,mTwitter,mInstagram,mWhatsapp;

    private Grupo grupo;
    private Context context=this;
    private String linkFacebook,linkTwitter,linkInstagram,linkWhatsapp;

    public static void createInstance(Context context, Grupo grupo) {
        Intent intent = getLaunchIntent(context,grupo);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context,Grupo grupo) {
        Intent intent = new Intent(context, DetailsGrupoActivity.class);
        intent.putExtra(ACTIVITY_GRUPO,grupo);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        //NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        //notificationManagerCompat.cancelAll();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_grupo);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        grupo = (Grupo) getIntent().getSerializableExtra(ACTIVITY_GRUPO);

        mNombre=(TextView)findViewById(R.id.tvNombreGrupo);
        mContenido=(TextView)findViewById(R.id.tvContenidoGrupo);

        mFoto=(ImageView) findViewById(R.id.ivFotoGrupo);
        mCelular=(ImageView) findViewById(R.id.ivCelularGrupo);
        mFacebook=(ImageView) findViewById(R.id.ivFacebookGrupo);
        mTwitter=(ImageView) findViewById(R.id.ivTwitterGrupo);
        mInstagram=(ImageView) findViewById(R.id.ivInstagramGrupo);
        mWhatsapp=(ImageView) findViewById(R.id.ivWhatsappGrupo);

        setTitle("Detalle del grupo");

        if (grupo!=null)
            getInit();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivFacebookGrupo:
                IntentUtiles.intentWeb(context,linkFacebook);break;
            case R.id.ivTwitterGrupo:
                IntentUtiles.intentWeb(context,linkTwitter);break;
            case R.id.ivInstagramGrupo:
                IntentUtiles.intentWeb(context,linkInstagram);break;
            case R.id.ivWhatsappGrupo:
                IntentUtiles.intentWeb(context,linkWhatsapp);break;

        }
    }
    public void getInit(){
        mNombre.setText(grupo.getNombre());
        mContenido.setText(grupo.getDescripcion());

        Glide.with(this).load(grupo.getFoto()).into(mFoto);

        mFacebook.setOnClickListener(this);
        mTwitter.setOnClickListener(this);
        mInstagram.setOnClickListener(this);
        mWhatsapp.setOnClickListener(this);

        if (grupo.getCelular()!=null){
            mCelular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentTelefonos(context,grupo.getCelular());
                }
            });
        }

        if(grupo.getSocial()!=null){

            if(grupo.getSocial().getFacebook()!=null){
                linkFacebook = grupo.getSocial().getFacebook();
            }else
                mFacebook.setVisibility(View.GONE);

            if(grupo.getSocial().getTwitter()!=null){
                linkTwitter=grupo.getSocial().getTwitter();
            }else
                mTwitter.setVisibility(View.GONE);

            if(grupo.getSocial().getInstagram()!=null){
                linkInstagram=grupo.getSocial().getInstagram();
            }else
                mInstagram.setVisibility(View.GONE);

            if(grupo.getSocial().getWhatsapp()!=null){
                linkWhatsapp = grupo.getSocial().getWhatsapp();
            }else
                mWhatsapp.setVisibility(View.GONE);
        }else {
            mFacebook.setVisibility(View.GONE);
            mTwitter.setVisibility(View.GONE);
            mInstagram.setVisibility(View.GONE);
            mWhatsapp.setVisibility(View.GONE);
        }
    }

}
