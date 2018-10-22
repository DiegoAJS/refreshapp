package org.developerjs.refreshapp.ui.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.interfaces.ItemClickListener;
import org.developerjs.refreshapp.interfaces.OnLoadMoreListener;
import org.developerjs.refreshapp.pojo.Actividad;
import org.developerjs.refreshapp.pojo.Grupo;
import org.developerjs.refreshapp.pojo.Footer;
import org.developerjs.refreshapp.pojo.Item;
import org.developerjs.refreshapp.pojo.ItemFirst;
import org.developerjs.refreshapp.pojo.Noticia;
import org.developerjs.refreshapp.ui.activity.DetailsActividadActivity;
import org.developerjs.refreshapp.ui.activity.DetailsGrupoActivity;
import org.developerjs.refreshapp.ui.activity.DetailsNoticiaActivity;
import org.developerjs.refreshapp.ui.holder.ActividadHolder;
import org.developerjs.refreshapp.ui.holder.GrupoHolder;
import org.developerjs.refreshapp.ui.holder.ItemFirstHolder;
import org.developerjs.refreshapp.ui.holder.NoticiaHolder;

import java.util.List;

public class AdapterItem  extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemClickListener {

    private Context context;
    private List<Item> items;

    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    private static final int TYPE_FOOTER        = 0;
    private static final int TYPE_NOTICIA       = 1;
    private static final int TYPE_ACTIVIDAD     = 2;
    private static final int TYPE_GRUPO         = 3;
    private static final int TYPE_ITEM_FIRST    = 4;

    public AdapterItem(@NonNull List<Item> items, Context context) {
        this.items = items;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Noticia) {
            return TYPE_NOTICIA;
        } else if (items.get(position) instanceof Actividad) {
            return TYPE_ACTIVIDAD;
        } else if (items.get(position) instanceof Grupo) {
            return TYPE_GRUPO;
        } else if(items.get(position) instanceof ItemFirst){
            return TYPE_ITEM_FIRST;
        } else if(items.get(position) instanceof Footer){
            return TYPE_FOOTER;
        }else
            throw new RuntimeException("ItemViewType unknown");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case TYPE_ACTIVIDAD:
                viewHolder = new ActividadHolder(inflater.inflate(R.layout.item_actividad,parent,false),this);break;
            case TYPE_GRUPO:
                viewHolder = new GrupoHolder(inflater.inflate(R.layout.item_grupo,parent,false),this);break;
            case TYPE_NOTICIA:
                viewHolder = new NoticiaHolder(inflater.inflate(R.layout.item_noticia,parent,false),this);break;
            case TYPE_ITEM_FIRST:
                viewHolder = new ItemFirstHolder(inflater.inflate(R.layout.item_first,parent,false),this);break;
            case TYPE_FOOTER:
                viewHolder =  new LoadHolder(inflater.inflate(R.layout.item_load,parent,false));break;

        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(position>=getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        switch (getItemViewType(position)){
            case TYPE_ACTIVIDAD:((ActividadHolder)holder).bindActividad((Actividad) items.get(position));break;
            case TYPE_GRUPO:((GrupoHolder)holder).bindCircular(context,(Grupo) items.get(position));break;
            case TYPE_NOTICIA: ((NoticiaHolder)holder).bindNoticia((Noticia) items.get(position));break;
            case TYPE_ITEM_FIRST: ((ItemFirstHolder)holder).bindNoticia((ItemFirst) items.get(position));break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(View view, int position) {

        //Detail detail=null;

        switch (getItemViewType(position))
        {
            case TYPE_ACTIVIDAD:
               DetailsActividadActivity.createInstance(context,(Actividad)items.get(position));break;

            case TYPE_GRUPO:
                DetailsGrupoActivity.createInstance(context,(Grupo) items.get(position));break;

            case TYPE_NOTICIA:
               DetailsNoticiaActivity.createInstance(context,(Noticia) items.get(position));break;


        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it call adapter.notifyDataChanged(); after update the list */
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    //viewHolder Load
    static class LoadHolder extends RecyclerView.ViewHolder{
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }
}
