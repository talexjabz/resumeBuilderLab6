package com.miu.mdp.resumebuildermary

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ResumeViewPagerAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFrag()
            1 -> EducationFrag()
            2 -> WorkExpFrag()
            3 -> ContactFrag()
            else -> HomeFrag()
        }
    }

}
