package org.developerjs.refreshapp.ui.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_circular.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Circular
import java.text.SimpleDateFormat


class CircularHolder(itemView: View, var listener: ItemClickListener):RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }
    fun bindCircular(context:Context,circular: Circular?) {
        with(circular!!) {
            val format = SimpleDateFormat("EEE, d MMM yyyy")
            itemView.tvTituloItemCircular.text = circular.titulo
            itemView.tvFechaItemCicular.text = format.format(circular.update)
            Glide.with(context).load(circular.foto).into(itemView.fotoItemCircular)
            itemView.tvContenidoItemCircular.text = circular.contenido
            itemView.tvDateItemCircular.text = format.format(circular.fecha_circular)

        }
    }
}