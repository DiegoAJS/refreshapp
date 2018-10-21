package org.developerjs.refreshapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.developerjs.refreshapp.ui.adapter.AdapterFragments
import org.developerjs.refreshapp.ui.fragment.GenericFargment
import com.google.firebase.iid.FirebaseInstanceId




class MainActivity : AppCompatActivity() {


    lateinit var pagerAdapter: AdapterFragments

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_noticias-> {
                //message.setText(R.string.title_home)
                viewpager_main.currentItem=0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_actividades-> {
                //message.setText(R.string.title_dashboard)
                viewpager_main.currentItem=1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_grupos-> {
                viewpager_main.currentItem=3
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = AdapterFragments(supportFragmentManager)
        pagerAdapter.addFragment(GenericFargment.newInstance(GenericFargment.FRAGMENT_NOTICIA))
        pagerAdapter.addFragment(GenericFargment.newInstance(GenericFargment.FRAGMENT_ACTIVIDAD))
        pagerAdapter.addFragment(GenericFargment.newInstance(GenericFargment.FRAGMENT_GRUPO))
        viewpager_main.adapter = pagerAdapter

        navigation_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        viewpager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                //throw UnsupportedOperationException()
                // your code
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //throw UnsupportedOperationException()
                // your code
            }

            override fun onPageSelected(position: Int) {
                navigationItemSelected(position)
            }
        })

        //val token = FirebaseInstanceId.getInstance().token

        //Log.d("MainActivity_Token", "Token: " + token!!)


    }

    public fun navigationItemSelected(n:Int){
        when(n){
            0-> navigation_main.selectedItemId=R.id.navigation_noticias
            1-> navigation_main.selectedItemId=R.id.navigation_actividades
            2-> navigation_main.selectedItemId=R.id.navigation_grupos

        }
    }
}
