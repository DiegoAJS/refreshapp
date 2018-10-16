package org.developerjs.refreshapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.pojo.Noticia;
import org.developerjs.refreshapp.util.IntentUtiles;

import java.text.SimpleDateFormat;

public class DetailsNoticiaActivity extends AppCompatActivity {

    public static final String TAG = DetailsNoticiaActivity.class.getSimpleName();

    public static final String ACTIVITY_NOTICIA = "DetailsNoticiaActivity.noticia";

    private TextView mTitulo,mFuente,mContenido,mFechaPublicacion,mVideo;
    private ImageView mFoto;

    private Noticia noticia;
    private Context context=this;

    public static void createInstance(Context context, Noticia noticia) {
        Intent intent = getLaunchIntent(context,noticia);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context,Noticia noticia) {
        Intent intent = new Intent(context, DetailsNoticiaActivity.class);
        intent.putExtra(ACTIVITY_NOTICIA,noticia);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_noticia);
        setSupportActionBar(toolbar);

        noticia = (Noticia) getIntent().getSerializableExtra(ACTIVITY_NOTICIA);

        mTitulo             =(TextView)findViewById(R.id.tvTituloNoticia);
        mFuente             =(TextView)findViewById(R.id.tvFuenteNoticia);
        mFechaPublicacion   =(TextView)findViewById(R.id.tvFechaPublicacionNoticia);
        mContenido          =(TextView) findViewById(R.id.tvContenidoNoticia);
        mFoto               =(ImageView) findViewById(R.id.ivFotoNoticia);
        mVideo              =(TextView)findViewById(R.id.tvVideoNoticia);

        setTitle("Detalle Noticia");

        getInit();
        //Log.d(TAG,noticia.toString());


    }

    void getInit(){
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");

        mTitulo.setText(noticia.getTitulo());
        mFuente.setText(noticia.getFuente());
        mContenido.setText(noticia.getContenido());
        mFechaPublicacion.setText(format.format(noticia.getUpdate()));

        if (noticia.getFoto()!=null)
            Glide.with(this).load(noticia.getFoto()).into(mFoto);
        else
            mFoto.setVisibility(View.GONE);

        if (noticia.getVideo()!=null){
            mVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtiles.intentWeb(context,noticia.getVideo());
                }
            });
        }else {
            mVideo.setVisibility(View.GONE);
        }
    }
}
