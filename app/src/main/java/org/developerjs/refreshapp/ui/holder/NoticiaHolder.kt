package org.developerjs.refreshapp.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_noticia.view.*
import org.developerjs.refreshapp.pojo.Noticia

class NoticiaHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    fun bindMessage(noticia: Noticia?) {
        with(noticia!!) {
            itemView.tvTitleItemNoticia.text = noticia.titulo
            itemView.tvContentItemNoticia.text = noticia.contenido
            itemView.tvDateItemNoticia.text = noticia.fecha
        }
    }
}