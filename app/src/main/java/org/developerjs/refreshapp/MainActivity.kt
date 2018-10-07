package org.developerjs.refreshapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.developerjs.refreshapp.util.adapter.AdapterFragments
import org.developerjs.refreshapp.util.fragment.ActividadFragment
import org.developerjs.refreshapp.util.fragment.CircularFragment
import org.developerjs.refreshapp.util.fragment.NoticiaFragment

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: AdapterFragments



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = AdapterFragments(supportFragmentManager)
        pagerAdapter.addFragment(NoticiaFragment())
        pagerAdapter.addFragment(ActividadFragment())
        pagerAdapter.addFragment(CircularFragment())
        viewpager_main.adapter = pagerAdapter
    }


}
