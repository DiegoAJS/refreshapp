package org.developerjs.refreshapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.Query;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.interfaces.OnClickItem;
import org.developerjs.refreshapp.pojo.Circular;
import org.jetbrains.annotations.NotNull;

public class CircularAdapter extends FirestoreAdapter<CircularAdapter.ViewHolder> implements OnClickItem {

    public CircularAdapter (Query query) {
        super(query);
    }

    @Override
    public CircularAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CircularAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticia, parent, false),this);
    }

    @Override
    public void onBindViewHolder(CircularAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position).toObject(Circular.class));
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
            mTitulo=(TextView) itemView.findViewById(R.id.tvTitleItemCircular);
            mFecha=(TextView) itemView.findViewById(R.id.tvDateItemCircular);
            mContenido=(TextView) itemView.findViewById(R.id.tvContentItemCircular);
            mButton=(Button)itemView.findViewById(R.id.material_buttonCircular);

            this.onClickItem= onClickItem;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickItem.onClickItemThis(v, getAdapterPosition());
        }

        public void bind(Circular circular) {
            mTitulo.setText(circular.getTitulo());
            mFecha.setText(circular.getFecha());
            mContenido.setText(circular.getContenido());

        }
    }
}
