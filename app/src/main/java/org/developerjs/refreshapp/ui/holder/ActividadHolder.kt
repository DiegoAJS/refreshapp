package org.developerjs.refreshapp.ui.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_actividad.view.*
import kotlinx.android.synthetic.main.item_circular.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Actividad
import org.developerjs.refreshapp.pojo.Circular
import java.text.SimpleDateFormat

class ActividadHolder (itemView: View, var listener: ItemClickListener):RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }
    fun bindActividad( actividad: Actividad?) {
        with(actividad!!) {
            val format = SimpleDateFormat("EEE, d MMM yyyy")

            itemView.tvTitleItemActividad.text = actividad.titulo
            itemView.tvContentItemActividad.text = format.format(actividad.fecha_actividad)
            itemView.tvDateItemActividad.text = format.format(actividad.fecha)
        }
    }
}