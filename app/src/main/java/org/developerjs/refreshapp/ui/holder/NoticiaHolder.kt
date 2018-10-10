package org.developerjs.refreshapp.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_noticia.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Noticia
import java.text.SimpleDateFormat

class NoticiaHolder(itemView: View, var listener: ItemClickListener):RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }

    fun bindNoticia(noticia: Noticia?) {
        with(noticia!!) {
            itemView.tvTitleItemNoticia.text = noticia.titulo
            itemView.tvContentItemNoticia.text = noticia.contenido
            val format = SimpleDateFormat("EEE, d MMM yyyy")

            itemView.tvDateItemNoticia.text = format.format(noticia.fecha)
        }
    }
}