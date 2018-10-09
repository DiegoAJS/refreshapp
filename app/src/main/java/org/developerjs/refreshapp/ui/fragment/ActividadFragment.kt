package org.developerjs.refreshapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import org.developerjs.refreshapp.R
import org.developerjs.refreshapp.ui.adapter.ActividadAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ActividadFragment: Fragment() {

    public var TAG : String = "ActividadFragment"
    var mFirestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var mQuery: Query
    lateinit var mActividadRef : DocumentReference
    lateinit var mRegistration: ListenerRegistration
    lateinit var mAdapter : ActividadAdapter
    lateinit var mActiviadaRecycler:RecyclerView

    companion object {

        val TYPE = "fragment.type"

        fun newInstance( type : String): ActividadFragment {
                val fragment = ActividadFragment()
                val args = Bundle()
                args.putString(TYPE, type)
                fragment.setArguments(args)
                return fragment
            }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView:View = inflater?.inflate(R.layout.fragment_list, container, false)
        if (arguments != null) {
            initFirestore(arguments!!.getString(TYPE))
        }

        mActiviadaRecycler=rootView.reciclador

        return rootView


    }

    private fun initFirestore(type:String) {
        mFirestore = FirebaseFirestore.getInstance()
        mQuery = mFirestore.collection(type).orderBy("fecha", Query.Direction.DESCENDING)
    }

    private fun initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView")
        }

        mAdapter  = object : ActividadAdapter(mQuery) {

            override fun onDataChanged() {
                // Called each time there is a new data snapshot. You may want to use this method
                // to hide a loading spinner or check for the "no documents" state and update your UI.
                // ...
                if (itemCount == 0) {
                    mActiviadaRecycler.setVisibility(View.GONE)
                    mEmptyView.setVisibility(View.VISIBLE)
                } else {
                    mActiviadaRecycler.setVisibility(View.VISIBLE)
                    mEmptyView.setVisibility(View.GONE)
                }
            }
            override fun onError(e:FirebaseFirestoreException){
                //
                Log.w(TAG, "Error: check logs for info.")
            }
        }

        mActiviadaRecycler.setLayoutManager(LinearLayoutManager(activity))
        mActiviadaRecycler.adapter=mAdapter


    }

}