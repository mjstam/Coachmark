package com.hmravens.coachmarkapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {

        var factory: CoachmarkFactory =
            CoachmarkFactory(this)

        factory.orientation = EnumOrientation.NORTH
        factory.textColor = Color.DKGRAY
        factory.backgroundColor = Color.YELLOW
        factory.borderColor = Color.LTGRAY
        factory.textSize = 30F
        factory.radiusSize = 15F
        factory.borderSize = 4F
        factory.typeFace =  Typeface.create("Arial", Typeface.NORMAL )
        factory.dismissOnTouch = true


        var coachMark: Coachmark = factory.create( "one","Coachmark Baby!!!")

      //  coachMark.associate( hello_world )

   //     coachMark.display()

        factory.orientation = EnumOrientation.SOUTHEAST
        factory.textSize = 44F
        factory.textColor = Color.WHITE
        factory.backgroundColor = Color.BLACK
        factory.borderColor = Color.BLACK
        factory.microAdjustX = -100

        var coachMark2: Coachmark = factory.create( "two","In the beginning\nthere was nothing and\nlife was without\nform and void.")

    //    coachMark2.associate( hello_world )

    //    coachMark2.display()



        factory.orientation = EnumOrientation.SOUTHWEST
        factory.textSize = 55F
        factory.textColor = Color.BLACK
        factory.backgroundColor = Color.LTGRAY
        factory.borderColor = Color.GRAY
        factory.dismissOnTouch = false;
        factory.microAdjustX = 0

        var coachMark3: Coachmark = factory.create( "three","SOUTHWEST!!!")

      //  coachMark3.associate( hello_world )

     //   coachMark3.display()


    }


}
