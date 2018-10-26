package org.developerjs.refreshapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import org.developerjs.refreshapp.ui.adapter.AdapterFragments
import org.developerjs.refreshapp.ui.fragment.GenericFargment
import org.developerjs.refreshapp.util.IntentUtiles


class MainActivity : AppCompatActivity() {


    lateinit var pagerAdapter: AdapterFragments
    val web:String="https://refreshapp.me/"

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

        setSupportActionBar(toolbar_main)

        val notificationManagerCompat = NotificationManagerCompat.from(Aplicacion.getInstance())
        notificationManagerCompat.cancelAll()

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_chat -> {
                IntentUtiles.intentWeb(this,web+"chat")
                //Toast.makeText(this, “Menu 1 is selected”, Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_info -> {
                IntentUtiles.intentWeb(this,web+"info")
                //Toast.makeText(this, “Menu 2 is selected”, Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_auxiliares-> {
                IntentUtiles.intentWeb(this,web+"aux")
                //Toast.makeText(this, “Menu 3 is selected”, Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
