package com.hmravens.common.needs.sample

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hmravens.coachmark.CoachMark
import com.hmravens.coachmark.CoachMarkFactory
import com.hmravens.coachmark.EnumOrientation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        var factory: CoachMarkFactory =
            CoachMarkFactory(this)

        factory.orientation = EnumOrientation.NORTH
        factory.textColor = Color.DKGRAY
        factory.backgroundColor = Color.YELLOW
        factory.borderColor = Color.LTGRAY
        factory.textSize = 30F
        factory.radiusSize = 15F
        factory.borderSize = 4F
        factory.typeFace =  Typeface.create("Arial", Typeface.NORMAL )
        factory.dismissOnTouch = true
        factory.microAdjustX = 100

        var coachMark: CoachMark = factory.create( "one","Coachmark Baby!!!")

        coachMark.associate( hello_world )

        coachMark.display()

        factory.orientation = EnumOrientation.SOUTHEAST
        factory.textSize = 44F
        factory.textColor = Color.WHITE
        factory.backgroundColor = Color.BLACK
        factory.borderColor = Color.BLACK

        var coachMark2: CoachMark = factory.create( "two","In the beginning\nThere was Nothing\nLife was without\nform and void.")

        coachMark2.associate( hello_world )

        coachMark2.display()



        factory.orientation = EnumOrientation.SOUTHWEST
        factory.textSize = 55F
        factory.textColor = Color.BLACK
        factory.backgroundColor = Color.LTGRAY
        factory.borderColor = Color.GRAY
        factory.dismissOnTouch = false;

        var coachMark3: CoachMark = factory.create( "three","SOUTHWEST!!!")

        coachMark3.associate( hello_world )

        coachMark3.display()


    }


}
