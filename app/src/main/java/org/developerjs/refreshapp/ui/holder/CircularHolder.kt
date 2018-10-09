package org.developerjs.refreshapp.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_circular.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Circular


class CircularHolder(itemView: View, var listener: ItemClickListener):RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }
    fun bindCircular(circular: Circular?) {
        with(circular!!) {
            itemView.tvTitleItemCircular.text = circular.titulo
            itemView.tvContentItemCircular.text = circular.contenido
            itemView.tvDateItemCircular.text = circular.fecha
        }
    }
}