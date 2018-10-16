package org.developerjs.refreshapp.ui.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_grupo.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.Grupo


class GrupoHolder(itemView: View, var listener: ItemClickListener):RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }
    fun bindCircular(context:Context,grupo: Grupo?) {
        with(grupo!!) {

            Glide.with(context).load(grupo.foto).into(itemView.ivFotoItemGrupo)
            itemView.tvNombreItemGrupo.text = grupo.nombre
            itemView.tvDescripcionItemGrupo.text = grupo.descripcion

        }
    }
}