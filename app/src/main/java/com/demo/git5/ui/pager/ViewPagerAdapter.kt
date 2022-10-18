package com.demo.git5.ui.pager

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.demo.git5.R
import com.demo.git5.ui.repos.ReposFragment

/**
 * Create view paper
 */
class ViewPagerAdapter(
    private val mContext: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.repository_title)

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ReposFragment()
            else -> ReposFragment()
        }
    }

    override fun getCount(): Int = 1
}