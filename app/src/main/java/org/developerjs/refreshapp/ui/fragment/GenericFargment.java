package org.developerjs.refreshapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.developerjs.refreshapp.R;

public class GenericFargment extends Fragment {

    public static final String TAG = "GenericFargment ";

    public static String FRAGMENT_MENU              = "fragment.menu";
    public static final int FRAGMENT_ACTIVIDAD      = 1;
    public static final int FRAGMENT_CIRCULAR       = 2;
    public static final int FRAGMENT_NOTICIA        = 3;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static GenericFargment newInstance(int fragmentMenu) {
        GenericFargment fragment = new GenericFargment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_MENU, fragmentMenu);
        fragment.setArguments(args);
        return fragment;
    }

    public GenericFargment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        // Ubicar argumento en el text view de section_fragment.xml
        switch (getArguments().getInt(FRAGMENT_MENU)){
            case FRAGMENT_NOTICIA: initNoticia();break;
            case FRAGMENT_CIRCULAR : initCircular(); break;
            case FRAGMENT_ACTIVIDAD: initActividad(); break;
        }
        return view;
    }


    public void initNoticia(){

        db.collection("noticias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }

    public void initCircular(){

    }

    public void initActividad(){

    }

}
