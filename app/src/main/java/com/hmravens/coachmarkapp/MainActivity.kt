package com.hmravens.coachmarkapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.hmravens.common.needs.sample.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val oritentationFrag = OrientationFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_holder, oritentationFrag )
        transaction.commit()

        tab_control.getTabAt(0)!!.select()

        tab_control.addOnTabSelectedListener( TabController() )


    }


    inner class TabController : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
            // no action
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            // no action
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab_control.selectedTabPosition) {
                0 -> {
                    val oritentationFrag = OrientationFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_holder, oritentationFrag )
                    transaction.commit()
                }
                1 -> {
                    val multiFrag = MultilineFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_holder, multiFrag )
                    transaction.commit()
                }
                else -> {
                    val displayFrag = DisplayFunctionsFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_holder, displayFrag )
                    transaction.commit()
                }
            }
        }

    }


}
