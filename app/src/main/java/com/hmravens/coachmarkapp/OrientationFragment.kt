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
import com.hmravens.coachmark.EnumOrientation
import com.hmravens.common.needs.sample.R
import kotlinx.android.synthetic.main.fragment_orientation.*


class OrientationFragment: Fragment() {

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
    }





   fun buildCoachmarks() {
        super.onStart()

        val factory: CoachmarkFactory = CoachmarkFactory(this.context as Context)

        factory.orientation = EnumOrientation.NORTH
        factory.textColor = Color.WHITE
        factory.backgroundColor = Color.DKGRAY
        factory.borderColor = Color.LTGRAY
        factory.textSize = 20F
        factory.radiusSize = 8F
        factory.borderSize = 4F
        factory.typeFace =  Typeface.create("sans-serif", Typeface.NORMAL )
        factory.dismissOnTouch = true

        val northMark: Coachmark = factory.create("North", R.string.north )
        northMark.associate( reset_coachmarks )
        northMark.display()


       factory.orientation = EnumOrientation.NORTHEAST

       val northEast: Coachmark = factory.create("NorthEast", R.string.northeast )
       northEast.associate( reset_coachmarks )
       northEast.display()

       factory.orientation = EnumOrientation.EAST

       val east: Coachmark = factory.create("East", R.string.east )
       east.associate( reset_coachmarks )
       east.display()

       factory.orientation = EnumOrientation.SOUTHEAST

       val southeast: Coachmark = factory.create("SouthEast", R.string.southeast )
       southeast.associate( reset_coachmarks )
       southeast.display()

       factory.orientation = EnumOrientation.SOUTH

       val south: Coachmark = factory.create("South", R.string.south )
       south.associate( reset_coachmarks )
       south.display()

       factory.orientation = EnumOrientation.SOUTHWEST

       val southwest: Coachmark = factory.create("SouthWest", R.string.southwest )
       southwest.associate( reset_coachmarks )
       southwest.display()

       factory.orientation = EnumOrientation.WEST

       val west: Coachmark = factory.create("West", R.string.west )
       west.associate( reset_coachmarks )
       west.display()


       factory.orientation = EnumOrientation.NORTHWEST

       val northwest: Coachmark = factory.create("West", R.string.northwest )
       northwest.associate( reset_coachmarks )
       northwest.display()

   }



}