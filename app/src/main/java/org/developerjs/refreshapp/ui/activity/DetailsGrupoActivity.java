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
import org.developerjs.refreshapp.interfaces.ItemClickListener;
import org.developerjs.refreshapp.pojo.Grupo;
import org.developerjs.refreshapp.util.IntentUtiles;

import java.text.SimpleDateFormat;

public class DetailsGrupoActivity extends AppCompatActivity {

    public static final String TAG = DetailsGrupoActivity.class.getSimpleName().toLowerCase();

    public static final String ACTIVITY_GRUPO = "DetailsGrupoActivity.grupo";

    private TextView mNombre,mContenido;
    private ImageView mFoto,mCelular,mFacebook,mTwitter,mInstagram,mWhatsapp;

    private Grupo grupo;
    private Context context=this;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_grupo);
        setSupportActionBar(toolbar);

        grupo = (Grupo) getIntent().getSerializableExtra(ACTIVITY_GRUPO);

        mNombre=(TextView)findViewById(R.id.tvNombreGrupo);
        mContenido=(TextView)findViewById(R.id.tvContenidoGrupo);

        mFoto=(ImageView) findViewById(R.id.ivFotoGrupo);
        mCelular=(ImageView) findViewById(R.id.ivCelularGrupo);
        mFacebook=(ImageView) findViewById(R.id.ivFacebookGrupo);
        mTwitter=(ImageView) findViewById(R.id.ivTwitterGrupo);
        mInstagram=(ImageView) findViewById(R.id.ivInstagramGrupo);
        mWhatsapp=(ImageView) findViewById(R.id.ivWhatsappGrupo);

        setTitle("Detalle");

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");

        mNombre.setText(grupo.getNombre());
        mContenido.setText(grupo.getDescripcion());

        Glide.with(this).load(grupo.getFoto()).into(mFoto);

        if (grupo.getCelular()!=null){
            mCelular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentTelefonos(context,grupo.getCelular());
                }
            });
        }

        if(grupo.getSocial().getFacebook()!=null){
            mFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,grupo.getSocial().getFacebook());
                }
            });
        }

        if(grupo.getSocial().getTwitter()!=null){
            mTwitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,grupo.getSocial().getTwitter());
                }
            });
        }

        if(grupo.getSocial().getInstagram()!=null){
            mInstagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,grupo.getSocial().getInstagram());
                }
            });
        }
        if(grupo.getSocial().getWhatsapp()!=null){
            mWhatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,grupo.getSocial().getWhatsapp());
                }
            });
        }




    }
}
