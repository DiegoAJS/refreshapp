package org.developerjs.refreshapp.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import org.developerjs.refreshapp.R;

public class ZoomDialog extends DialogFragment {

    private static final String TAG = ZoomDialog.class.getSimpleName();
    private static final String LOAD = "ZoomDialog.load";
    private PhotoView photoView;

    public static ZoomDialog getInstanceDialog(String load){
        ZoomDialog zoomDialog=new ZoomDialog();
        Bundle bundle= new Bundle();
        bundle.putString(LOAD,load);
        zoomDialog.setArguments(bundle);
        return zoomDialog;
    }

    public ZoomDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createLoginDialogo();
    }

    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_zoom, null);

        String load=getArguments().getString(LOAD);

        photoView = (PhotoView) v.findViewById(R.id.ivZoom);
        Glide.with(getActivity()).load(load).into(photoView);

        builder.setView(v);

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}