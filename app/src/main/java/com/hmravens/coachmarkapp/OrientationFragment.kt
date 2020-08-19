package com.hmravens.coachmarkapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmravens.coachmark.Coachmark
import com.hmravens.coachmark.CoachmarkFactory
import com.hmravens.coachmark.EnumOrientation
import com.hmravens.common.needs.sample.R
import kotlinx.android.synthetic.main.fragment_orientation.*


/**
 * Table of system font types for typeface
 * Font                     | android:fontFamily          | android:textStyle
-------------------------|-----------------------------|-------------------
Roboto Thin              | sans-serif-thin             |
Roboto Light             | sans-serif-light            |
Roboto Regular           | sans-serif                  |
Roboto Bold              | sans-serif                  | bold
Roboto Medium            | sans-serif-medium           |
Roboto Black             | sans-serif-black            |
Roboto Condensed Light   | sans-serif-condensed-light  |
Roboto Condensed Regular | sans-serif-condensed        |
Roboto Condensed Medium  | sans-serif-condensed-medium |
Roboto Condensed Bold    | sans-serif-condensed        | bold
Noto Serif               | serif                       |
Noto Serif Bold          | serif                       | bold
Droid Sans Mono          | monospace                   |
Cutive Mono              | serif-monospace             |
Coming Soon              | casual                      |
Dancing Script           | cursive                     |
Dancing Script Bold      | cursive                     | bold
Carrois Gothic SC        | sans-serif-smallcaps        |
 */

class OrientationFragment: Fragment() {

    var coachmarks: MutableList<Coachmark> =  mutableListOf<Coachmark>()
    val handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.fragment_orientation, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reset_coachmarks.setOnClickListener( View.OnClickListener { resetCoachmarks() })
        handler.postDelayed( Runnable { buildCoachmarks() }, 300 )

    }







   fun buildCoachmarks() {



        val factory: CoachmarkFactory = CoachmarkFactory(this.context as Context)

        factory.orientation = EnumOrientation.NORTH
        factory.textColor = Color.WHITE
        factory.backgroundColor = Color.DKGRAY
        factory.borderColor = Color.LTGRAY
        factory.textSize = 30F
        factory.radiusSize = 15F
        factory.borderSize = 4F
        factory.typeFace =  Typeface.create("sans-serif-black", Typeface.NORMAL)
        factory.dismissOnTouch = true

        val northMark: Coachmark = factory.create("North", R.string.north )

        northMark.associate( reset_coachmarks )
        northMark.display()
        coachmarks.add( northMark )


       factory.orientation = EnumOrientation.NORTHEAST
       factory.textSize = 25F
       factory.borderColor = Color.BLACK
       factory.backgroundColor = Color.BLACK
       factory.typeFace =  Typeface.create("sans-serif-light", Typeface.NORMAL)
       val northEast: Coachmark = factory.create("NorthEast", R.string.northeast )
       northEast.associate( reset_coachmarks )

       northEast.display()
       coachmarks.add( northEast )

       factory.orientation = EnumOrientation.EAST
       factory.textSize = 20F
       factory.radiusSize = 10F
       factory.borderColor = Color.LTGRAY
       factory.backgroundColor = Color.parseColor("#BB5555")
       factory.typeFace =  Typeface.create("sans-serif-light", Typeface.BOLD)
       val east: Coachmark = factory.create("East", R.string.east )
       east.associate( reset_coachmarks )
       east.display()
       coachmarks.add( east )

       factory.orientation = EnumOrientation.SOUTHEAST
       factory.textSize = 18F
       factory.radiusSize = 4F
       factory.backgroundColor = Color.DKGRAY
       factory.borderColor = Color.BLACK
       factory.typeFace =  Typeface.create("serif", Typeface.ITALIC)
       val southeast: Coachmark = factory.create("SouthEast", R.string.southeast )
       southeast.associate( reset_coachmarks )
       southeast.display()
       coachmarks.add( southeast )

       factory.orientation = EnumOrientation.SOUTH
       factory.textSize = 35F
       factory.backgroundColor = Color.WHITE
       factory.borderColor = Color.GRAY
       factory.textColor = Color.BLACK
       factory.typeFace =  Typeface.create("cursive", Typeface.BOLD)
       val south: Coachmark = factory.create("South", R.string.south )
       south.associate( reset_coachmarks )
       south.display()
       coachmarks.add( south )

       factory.orientation = EnumOrientation.SOUTHWEST
       factory.textSize = 22F
       factory.textColor = Color.parseColor("#228822")

       val southwest: Coachmark = factory.create("SouthWest", R.string.southwest )
       southwest.associate( reset_coachmarks )
       southwest.display()
       coachmarks.add( southwest )

       factory.orientation = EnumOrientation.WEST
       factory.radiusSize = 20F
       factory.textColor = Color.parseColor("#222222")
       factory.typeFace = Typeface.create("serif-monospace",Typeface.NORMAL)

       val west: Coachmark = factory.create("West", R.string.west )
       west.associate( reset_coachmarks )
       west.display()
       coachmarks.add( west )


       factory.orientation = EnumOrientation.NORTHWEST
       factory.textColor = Color.parseColor("#111111")
       factory.borderColor = Color.parseColor( "#8888aa")
       factory.typeFace = Typeface.create("casual",Typeface.NORMAL)
       factory.textSize = 20F

       val northwest: Coachmark = factory.create("West", R.string.northwest )
       northwest.associate( reset_coachmarks )
       northwest.display()
       coachmarks.add( northwest )



       factory.orientation = EnumOrientation.SOUTHEAST
       factory.textColor = Color.WHITE
       factory.backgroundColor =  Color.parseColor("#111111")
       factory.borderColor =  Color.parseColor("#222255")
       factory.textSize = 20F
       factory.radiusSize = 15F
       factory.borderSize = 4F
       factory.typeFace =  Typeface.create("cursive",Typeface.NORMAL)
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