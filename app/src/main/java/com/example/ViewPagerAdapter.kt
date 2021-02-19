package com.example

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return  2

    }

    override fun getItem(position: Int): Fragment {
    if (position==0)
    {
        return Frag_Two()
    }
        return Frag_One()





    }
    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null


              if (position == 1) title = "جهت ارسال"
              else   if (position == 0) title = "عدم حضور مشتری"

        return title
    }
}