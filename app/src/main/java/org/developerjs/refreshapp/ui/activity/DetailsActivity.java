package org.developerjs.refreshapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.pojo.Detail;

public class DetailsActivity  extends AppCompatActivity {

    public static final String TAG = DetailsActivity.class.getSimpleName().toLowerCase();

    public static final String TAG_titulo           = "DetalleActivity.itulo";
    public static final String TAG_fecha            = "DetalleActivity.fecha";
    public static final String TAG_fecha_actividad  = "DetalleActivity.fecha_actividad";
    public static final String TAG_contenido        = "DetalleActivity.contenido";
    public static final String TAG_video            = "DetalleActivity.video";
    public static final String TAG_foto             = "DetalleActivity.foto";

    private TextView mTitle,mFecha,mContenido;
    private ImageView mParallax;
    private FloatingActionButton mFloatingActionButton;

    public static void createInstance(Context context, Detail detail) {
        Intent intent = getLaunchIntent(context,detail);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context,Detail detail) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(TAG_titulo,detail.getTitulo());
        intent.putExtra(TAG_fecha,detail.getFecha());
        intent.putExtra(TAG_fecha_actividad,detail.getFecha_actividad());
        intent.putExtra(TAG_contenido,detail.getContenido());
        intent.putExtra(TAG_video,detail.getVideo());
        intent.putExtra(TAG_foto,detail.getFoto());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);

        setSupportActionBar(toolbar);

        mTitle=(TextView)findViewById(R.id.tvTitleDetail);
        mFecha=(TextView)findViewById(R.id.tvDateDetail);
        mContenido=(TextView) findViewById(R.id.tvTextDetail);
        mParallax=(ImageView)findViewById(R.id.image_paralax);

        mFloatingActionButton=(FloatingActionButton) findViewById(R.id.floatingActionButtonDetail);

        setTitle("Detalle");

        String titulo, fecha, fecha_actividad, contenido,video,foto;

        titulo=getIntent().getStringExtra(TAG_titulo);
        fecha=getIntent().getStringExtra(TAG_fecha);
        fecha_actividad=getIntent().getStringExtra(TAG_fecha_actividad);
        contenido = getIntent().getStringExtra(TAG_contenido);
        foto= getIntent().getStringExtra(TAG_foto);
        video = getIntent().getStringExtra(TAG_video);

        mTitle.setText(titulo);
        mFecha.setText(fecha);

        if (foto!=null)
            Glide.with(this).load(foto).into(this.mParallax);

        if (fecha_actividad!=null)
            mContenido.setText(fecha_actividad);
        else
            mContenido.setText(contenido);

        if(video==null){

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
            params.setBehavior(null);
            mFloatingActionButton.requestLayout();
            mFloatingActionButton.setVisibility(View.GONE);
        }


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
            }
        });
    }

}
