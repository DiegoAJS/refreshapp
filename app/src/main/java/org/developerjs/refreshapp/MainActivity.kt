package org.developerjs.refreshapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.developerjs.refreshapp.interfaces.OnSelectPage
import org.developerjs.refreshapp.ui.adapter.AdapterFragments
import org.developerjs.refreshapp.ui.fragment.ActividadFragment
import org.developerjs.refreshapp.ui.fragment.CircularFragment
import org.developerjs.refreshapp.ui.fragment.NoticiaFragment

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
                viewpager_main.currentItem=2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = AdapterFragments(supportFragmentManager)
        pagerAdapter.addFragment(NoticiaFragment())
        pagerAdapter.addFragment(ActividadFragment())
        pagerAdapter.addFragment(CircularFragment())
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
