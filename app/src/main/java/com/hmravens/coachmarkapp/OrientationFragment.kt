package com.hmravens.coachmarkapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmravens.coachmark.Coachmark
import com.hmravens.coachmark.CoachmarkFactory
import com.hmravens.coachmark.CoachmarkOnClickCallback
import com.hmravens.coachmark.EnumOrientation
import com.hmravens.common.needs.sample.R
import kotlinx.android.synthetic.main.fragment_orientation.*


class OrientationFragment: Fragment() {

    var coachmarks: MutableList<Coachmark> =  mutableListOf<Coachmark>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.fragment_orientation, container, false)
    }

    override fun onStart() {
        super.onStart()
        view!!.viewTreeObserver
            .addOnWindowFocusChangeListener {
               buildCoachmarks()
            }

         reset_coachmarks.setOnClickListener( View.OnClickListener { resetCoachmarks() })
    }





   fun buildCoachmarks() {
        super.onStart()

        val factory: CoachmarkFactory = CoachmarkFactory(this.context as Context)

        factory.orientation = EnumOrientation.NORTH
        factory.textColor = Color.WHITE
        factory.backgroundColor = Color.DKGRAY
        factory.borderColor = Color.LTGRAY
        factory.textSize = 30F
        factory.radiusSize = 15F
        factory.borderSize = 4F
        factory.typeFace =  Typeface.create("sans-serif", Typeface.NORMAL )
        factory.dismissOnTouch = true

        val northMark: Coachmark = factory.create("North", R.string.north )

        northMark.associate( reset_coachmarks )
        northMark.display()
        coachmarks.add( northMark )


       factory.orientation = EnumOrientation.NORTHEAST
       factory.textSize = 25F
       factory.borderColor = Color.BLACK
       factory.backgroundColor = Color.BLACK
       val northEast: Coachmark = factory.create("NorthEast", R.string.northeast )
       northEast.associate( reset_coachmarks )

       northEast.display()
       coachmarks.add( northEast )

       factory.orientation = EnumOrientation.EAST
       factory.textSize = 20F
       factory.radiusSize = 10F
       factory.borderColor = Color.LTGRAY
       factory.backgroundColor = Color.RED
       val east: Coachmark = factory.create("East", R.string.east )
       east.associate( reset_coachmarks )
       east.display()
       coachmarks.add( east )

       factory.orientation = EnumOrientation.SOUTHEAST
       factory.textSize = 18F
       factory.radiusSize = 4F
       factory.backgroundColor = Color.DKGRAY
       factory.borderColor = Color.BLACK

       val southeast: Coachmark = factory.create("SouthEast", R.string.southeast )
       southeast.associate( reset_coachmarks )
       southeast.display()
       coachmarks.add( southeast )

       factory.orientation = EnumOrientation.SOUTH
       factory.textSize = 35F
       factory.backgroundColor = Color.WHITE
       factory.borderColor = Color.GRAY
       factory.textColor = Color.BLACK
       val south: Coachmark = factory.create("South", R.string.south )
       south.associate( reset_coachmarks )
       south.display()
       coachmarks.add( south )

       factory.orientation = EnumOrientation.SOUTHWEST
       factory.textSize = 22F

       val southwest: Coachmark = factory.create("SouthWest", R.string.southwest )
       southwest.associate( reset_coachmarks )
       southwest.display()
       coachmarks.add( southwest )

       factory.orientation = EnumOrientation.WEST
       factory.radiusSize = 20F

       val west: Coachmark = factory.create("West", R.string.west )
       west.associate( reset_coachmarks )
       west.display()
       coachmarks.add( west )


       factory.orientation = EnumOrientation.NORTHWEST

       val northwest: Coachmark = factory.create("West", R.string.northwest )
       northwest.associate( reset_coachmarks )
       northwest.display()
       coachmarks.add( northwest )



       factory.orientation = EnumOrientation.SOUTHEAST
       factory.textColor = Color.WHITE
       factory.backgroundColor = Color.DKGRAY
       factory.borderColor = Color.LTGRAY
       factory.textSize = 20F
       factory.radiusSize = 15F
       factory.borderSize = 4F
       factory.typeFace =  Typeface.create("sans-serif", Typeface.NORMAL )
       factory.dismissOnTouch = false
       factory.microAdjustX = -100
       factory.microAdjustY = -20


       val microadjust: Coachmark = factory.create("MicroAdjustExample", R.string.microadjust_coachmark )
       microadjust.associate( microadjust_button)
       microadjust.display()

   }

   fun resetCoachmarks() {

       for( coachmark:Coachmark in coachmarks ) {
           coachmark.display()
       }

   }

}