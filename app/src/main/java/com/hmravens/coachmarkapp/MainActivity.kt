package com.hmravens.coachmarkapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.hmravens.coachmark.Coachmark
import com.hmravens.coachmark.CoachmarkFactory
import com.hmravens.coachmark.EnumOrientation
import com.hmravens.common.needs.sample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.tab_control


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var oritentationFrag: OrientationFragment = OrientationFragment()
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
            if ( tab_control.selectedTabPosition == 0 ) {
                var oritentationFrag: OrientationFragment = OrientationFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_holder, oritentationFrag )
                transaction.commit()
            }
            else
                if ( tab_control.selectedTabPosition == 1 )

            {
                var multiFrag: MultilineFragment = MultilineFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_holder, multiFrag )
                transaction.commit()
            }
            else
                {
                    var displayFrag: DisplayFunctionsFragment = DisplayFunctionsFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_holder, displayFrag )
                    transaction.commit()
                }
        }

    }


}
