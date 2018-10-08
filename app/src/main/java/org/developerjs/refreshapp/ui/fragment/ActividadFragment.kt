package org.developerjs.refreshapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.developerjs.refreshapp.R

class ActividadFragment: Fragment() {

    companion object {

        fun newInstance(): ActividadFragment {
            return ActividadFragment()
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_list, container, false)
    }
}