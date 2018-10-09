package org.developerjs.refreshapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.developerjs.refreshapp.R
import org.developerjs.refreshapp.pojo.Detail
class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        setSupportActionBar(toolbar_detail)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        collapser.title="titulos"

        val mTitulo = intent.getStringExtra(titulo)?: throw IllegalStateException("field $titulo missing in Intent")
        val mFecha = intent.getStringExtra(fecha)?: throw IllegalStateException("field $fecha missing in Intent")
        val mFechaActividad = intent.getStringExtra(fecha_actividad)?: throw IllegalStateException("field $fecha_actividad missing in Intent")
        val mContenido = intent.getStringExtra(contenido)?: throw IllegalStateException("field $contenido missing in Intent")
        val mVideo = intent.getStringExtra(video)?: throw IllegalStateException("field $video missing in Intent")
        val mFoto = intent.getStringExtra(foto)?: throw IllegalStateException("field $foto missing in Intent")

        collapser.title=mTitulo
        tvDateDetail.text=mFecha
        if (mFechaActividad.length>0)
            tvTextDetail.text=mFechaActividad
        else
            tvTextDetail.text=mContenido

        if (mVideo.length>0)
            floatingActionButton.isEnabled=true
        else
            floatingActionButton.isEnabled=false

        if (mFoto.length>0)
            Glide.with(this)
                    .load(mFoto)
                    .into(image_paralax)

    }

    companion object {

        private val titulo          = "detail.title"
        private val fecha           = "detail.fecha"
        private val fecha_actividad = "detail.fecha_actividad"
        private val contenido       = "detail.contenido"
        private val video           = "detail.video"
        private val foto            = "detail.foto"

        fun newIntent(context: Context, detail: Detail): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(titulo, detail.titulo)
            intent.putExtra(fecha, detail.fecha)
            intent.putExtra(fecha_actividad, detail.fecha_actividad)
            intent.putExtra(contenido, detail.contenido)
            intent.putExtra(video, detail.video)
            intent.putExtra(foto, detail.foto)
            return intent
        }
    }
}

