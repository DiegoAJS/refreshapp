package org.developerjs.refreshapp.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_actividad.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Actividad
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
            val formatDia = SimpleDateFormat("dd")
            val formatHora = SimpleDateFormat("HH:mm")
            val formatMes = SimpleDateFormat("MMM")

            itemView.tvByItemActividad.text = actividad.organizador

            itemView.tvDiaItemActividad.text = formatDia.format(actividad.fecha_actividad)
            itemView.tvMesItemActividad.text = formatMes.format(actividad.fecha_actividad)
            itemView.tvHoraItemActividad.text = formatHora.format(actividad.fecha_actividad)
            itemView.tvTitleItemActividad.text = actividad.titulo
            itemView.tvContenitdoItemActividad.text=actividad.contenido
            itemView.tvTipoItemActividad.text=actividad.tipo_actividad
        }
    }
}