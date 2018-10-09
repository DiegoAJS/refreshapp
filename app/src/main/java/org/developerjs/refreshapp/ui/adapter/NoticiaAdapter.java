package org.developerjs.refreshapp.ui.adapter;

import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.firestore.Query;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.interfaces.OnClickItem;
import org.developerjs.refreshapp.pojo.Noticia;
import org.jetbrains.annotations.NotNull;

public class NoticiaAdapter extends FirestoreAdapter<NoticiaAdapter.ViewHolder> implements OnClickItem {

    public NoticiaAdapter (Query query) {
        super(query);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticia, parent, false),this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position).toObject(Noticia.class));
    }


    @Override
    public void onClickItemThis(@NotNull View view, int position) {
        Log.d("TAG",String.valueOf(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTitulo;
        TextView mFecha;
        TextView mContenido;
        Button mButton;

        OnClickItem onClickItem;

        public ViewHolder(View itemView, OnClickItem onClickItem) {
            super(itemView);
            mTitulo=(TextView) itemView.findViewById(R.id.tvTitleItemNoticia);
            mFecha=(TextView) itemView.findViewById(R.id.tvDateItemNoticia);
            mContenido=(TextView) itemView.findViewById(R.id.tvContentItemNoticia);
            mButton=(Button)itemView.findViewById(R.id.material_buttonNoticia);

            this.onClickItem= onClickItem;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickItem.onClickItemThis(v, getAdapterPosition());
        }

        public void bind(Noticia noticia) {
            mTitulo.setText(noticia.getTitulo());
            mFecha.setText(noticia.getFecha());
            mContenido.setText(noticia.getContenido());

        }
    }

}
