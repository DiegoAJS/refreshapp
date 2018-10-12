package org.developerjs.refreshapp.ui.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_comunidad.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Comunidad
import org.developerjs.refreshapp.util.IntentUtiles
import java.text.SimpleDateFormat

class ComunidadHolder (itemView: View, var listener: ItemClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }
    fun bindActividad( context: Context, comunidad: Comunidad?) {
        with(comunidad!!) {
            val format = SimpleDateFormat("EEE, d MMM yyyy")

            Glide.with(context).load(comunidad.logo).into(itemView.ivFotoItemComunidad)
            itemView.tvNombreItemComunidad.text = comunidad.nombre
            itemView.tvDescripcionItemComunidad.text = comunidad.descripcion
            itemView.ivlinkItemComunidad.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    IntentUtiles.intentWeb(context,comunidad.link)
                }
            })
        }
    }
}