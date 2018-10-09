package org.developerjs.refreshapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.developerjs.refreshapp.ui.adapter.AdapterFragments
import org.developerjs.refreshapp.ui.fragment.GenericFargment


class MainActivity : AppCompatActivity() {


    lateinit var pagerAdapter: AdapterFragments

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                viewpager_main.currentItem=0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                viewpager_main.currentItem=1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //message.setText(R.string.title_notifications)
                //viewpager_main.currentItem=2
                //val detail=Detail()
                //detail.titulo="titulos"
                //detail.contenido="contenido"
                //detail.fecha="fecha hoy"
                //detail.fecha_actividad="fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad fecha actividad "
                //detail.video="video"
                //detail.foto="https://estaticos.elperiodico.com/resources/jpg/6/8/ciencia-del-universo-1530540262286.jpg"

                //val intent = DetailActivity.newIntent(this, detail)
                //startActivity(intent)

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
        pagerAdapter.addFragment(GenericFargment.newInstance(GenericFargment.FRAGMENT_CIRCULAR))
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
    }

    public fun navigationItemSelected(n:Int){
        when(n){
            0-> navigation_main.selectedItemId=R.id.navigation_home
            1-> navigation_main.selectedItemId=R.id.navigation_dashboard
            2-> navigation_main.selectedItemId=R.id.navigation_notifications

        }
    }
}
