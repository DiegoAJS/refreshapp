package org.developerjs.refreshapp.util.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class AdapterFragments (fragmentManager:FragmentManager) : FragmentPagerAdapter(fragmentManager){

    var items :ArrayList<Fragment> = ArrayList<Fragment>()

    override fun getItem(p0: Int): Fragment {
        return items.get(p0)
    }

    override fun getCount(): Int {
        return items.size
    }

    fun  addFragment(fragment:Fragment) {
        items.add(fragment)
    }


}