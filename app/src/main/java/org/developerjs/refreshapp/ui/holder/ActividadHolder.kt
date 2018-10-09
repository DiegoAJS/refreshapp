package org.developerjs.refreshapp.ui.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_actividad.view.*
import kotlinx.android.synthetic.main.item_circular.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Actividad
import org.developerjs.refreshapp.pojo.Circular

class ActividadHolder (itemView: View, var listener: ItemClickListener):RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }
    fun bindActividad( actividad: Actividad?) {
        with(actividad!!) {
            itemView.tvTitleItemActividad.text = actividad.titulo
            itemView.tvContentItemActividad.text = actividad.fecha_actividad
            itemView.tvDateItemActividad.text = actividad.fecha
        }
    }
}