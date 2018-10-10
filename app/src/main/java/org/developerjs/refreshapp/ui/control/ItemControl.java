package org.developerjs.refreshapp.ui.control;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.developerjs.refreshapp.R;
import org.developerjs.refreshapp.interfaces.OnLoadMoreListener;
import org.developerjs.refreshapp.pojo.Footer;
import org.developerjs.refreshapp.pojo.Item;
import org.developerjs.refreshapp.pojo.Noticia;
import org.developerjs.refreshapp.ui.adapter.AdapterItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hp on 15/8/2017.
 */

public class ItemControl implements SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private RecyclerView recycler;
    private FragmentManager fragmentManager;
    private SwipeRefreshLayout refreshLayout;
    private String ruta;

    public static String TAG = ItemControl.class.getSimpleName();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Query next;

    private AdapterItem adapter;
    private List<Item> items = new ArrayList<Item>();

    public ItemControl() { }

    public void setupFragment(Context c, View v, FragmentManager f, String ruta){

        this.context=c;
        this.fragmentManager=f;

        recycler=(RecyclerView)v.findViewById(R.id.reciclador);
        refreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.sr_cargando);

        this.ruta=ruta;
        inicializar();
    }


    @Override
    public void onRefresh() {
        dismissCargando();
        clear();
    }

    public void inicializar(){

        adapter = new AdapterItem(items,context);

        adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                recycler.post(new Runnable() {
                    @Override
                    public void run() {
                        cargar();
                    }
                });
            }
        });

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(adapter);

        // Obtener el refreshLayout
        // Seteamos los colores que se usarán a lo largo de la animación
        refreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
        refreshLayout.setOnRefreshListener(this);

        if(items.isEmpty()){
            next = db.collection(ruta)
                    .orderBy("fecha",Query.Direction.DESCENDING)
                    .limit(2);
            cargar();
        }
    }

    public void cargar(){
        if(items.size()==0)
            showCargando();
        else{
            items.add(new Footer());
            adapter.notifyItemInserted(items.size()-1);
        }

        next.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                dismissCargando();

                cancelar();
                // Get the last visible document

                if(documentSnapshots.size()>1){
                    DocumentSnapshot lastVisible = documentSnapshots.getDocuments()
                            .get(documentSnapshots.size() -1);

                    // Construct a new query starting at this document,
                    // get the next 25 cities.
                    next = db.collection(ruta)
                            .orderBy("fecha",Query.Direction.DESCENDING)
                            .startAfter(lastVisible)
                            .limit(2);

                }else
                {
                    adapter.setMoreDataAvailable(false);
                }

                for(DocumentSnapshot document : documentSnapshots.getDocuments()){
                    Log.d(TAG, document.getId()+" => " + document.getData().toString());

                    if (document.exists()) {
                        // convert document to POJO
                        Noticia pojo = document.toObject(Noticia.class);
                        items.add(pojo);
                    }
                }
                adapter.notifyDataChanged();
                //queryDocumentSnapshots.toObjects(Noticia.class);

            }
        });



        /*
        for (int i = 0 ; i<10; i++){
            Noticia noticia = new Noticia();
            noticia.setTitulo("noticias real "+i);
            noticia.setContenido("contenido fake");
            noticia.setFecha("hoy");
            noticia.setFoto("foto");
            noticia.setVideo("video");
            items.add(noticia);
        }*/

    }

    public void clear(){
        items.clear();
        inicializar();
    }


    private void cancelar(){
        if (items.size()==0){
            dismissCargando();
        }else {
            //remove loading view
            items.remove(items.size()-1);
        }
    }

    public void showCargando(){
        refreshLayout.setRefreshing(true);
    }

    public void dismissCargando(){
        refreshLayout.setRefreshing(false);
    }


    public void getDialogo(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle("Alerta");

        //alertDialogBuilder.setIcon(R.drawable.ic_error_outline_24dp);

        // set dialog message
        alertDialogBuilder
                .setMessage("Oh no. Error de conexion intentar de nuevo ?")
                .setCancelable(false)
                .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        showCargando();
                        cargar();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
