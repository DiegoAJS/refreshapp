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
import org.developerjs.refreshapp.pojo.Actividad;
import org.jetbrains.annotations.NotNull;

public class ActividadAdapter extends FirestoreAdapter<ActividadAdapter.ViewHolder> implements OnClickItem {

    public ActividadAdapter (Query query) {
        super(query);
    }

    @Override
    public ActividadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActividadAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actividad, parent, false),this);
    }

    @Override
    public void onBindViewHolder(ActividadAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position).toObject(Actividad.class));
    }

    @Override
    public void onClickItemThis(@NotNull View view, int position) {
        Log.d("TAG",String.valueOf(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTitulo;
        TextView mFecha;
        TextView mContenido;
        Button mButton;

        OnClickItem onClickItem;

        public ViewHolder(View itemView, OnClickItem onClickItem) {
            super(itemView);
            mTitulo=(TextView) itemView.findViewById(R.id.tvTitleItemActividad);
            mFecha=(TextView) itemView.findViewById(R.id.tvDateItemActividad);
            mContenido=(TextView) itemView.findViewById(R.id.tvContentItemActividad);
            mButton=(Button)itemView.findViewById(R.id.material_buttonActividad);

            this.onClickItem= onClickItem;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickItem.onClickItemThis(v, getAdapterPosition());
        }

        public void bind(Actividad actividad) {
            mTitulo.setText(actividad.getTitulo());
            mFecha.setText(actividad.getFecha());
            mContenido.setText(actividad.getFecha_actividad());

        }
    }
}
