package org.developerjs.refreshapp.util.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.developerjs.refreshapp.R

class NoticiaFragment : Fragment() {

    companion object {

        fun newInstance(): NoticiaFragment {
            return NoticiaFragment()
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_list, container, false)
    }
}