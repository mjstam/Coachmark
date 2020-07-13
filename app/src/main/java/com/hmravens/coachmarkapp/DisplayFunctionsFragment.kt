package com.hmravens.coachmarkapp

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.hmravens.coachmark.*
import com.hmravens.common.needs.sample.R
import kotlinx.android.synthetic.main.fragment_display_functions.*
import kotlinx.android.synthetic.main.fragment_multiline.*
import kotlinx.android.synthetic.main.fragment_orientation.*


class DisplayFunctionsFragment: Fragment() {

    var handler: Handler =  Handler()
    lateinit var shortTimeDisplay: Coachmark
    lateinit var onceDisplay: Coachmark


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.fragment_display_functions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed( Runnable { buildCoachmarks() }, 300 )

        five_seconds.setOnClickListener( { if (shortTimeDisplay.visibility != View.VISIBLE )  {displayOnTimer()} })

        display_once.setOnClickListener( { onceDisplay.reset() })

    }

   fun displayOnTimer() {
       val coachmarkDisplayHandler = CoachmarkDisplayHandler()
       coachmarkDisplayHandler.displayForLimitedTime(context!!, shortTimeDisplay, 5 )
   }

    fun displayOnce() {
        val coachmarkDisplayHandler = CoachmarkDisplayHandler()
        coachmarkDisplayHandler.displayOnce(context!!, onceDisplay, 5 )
    }

   fun buildCoachmarks() {

       val factory: CoachmarkFactory = CoachmarkFactory(this.context as Context)

       factory.orientation = EnumOrientation.SOUTHEAST
       factory.textColor = Color.WHITE
       factory.backgroundColor = Color.DKGRAY
       factory.borderColor = Color.parseColor("#888888")
       factory.textSize = 20F
       factory.radiusSize = 15F
       factory.borderSize = 3F
       factory.typeFace = Typeface.create("sans-serif-thin", Typeface.NORMAL)
       factory.microAdjustX = -200

       shortTimeDisplay = factory.create("10Second", R.string.here_today)

       shortTimeDisplay.associate(five_seconds)
       displayOnTimer()


       factory.orientation = EnumOrientation.SOUTH
       factory.textColor = Color.WHITE
       factory.backgroundColor = Color.DKGRAY
       factory.borderColor = Color.parseColor("#888888")
       factory.textSize = 20F
       factory.radiusSize = 15F
       factory.borderSize = 3F
       factory.typeFace = Typeface.create("sans-serif-thin", Typeface.NORMAL)
       factory.microAdjustX = 0

       onceDisplay = factory.create("Once", R.string.here_today)


       onceDisplay.associate(display_once)
       displayOnce()
   }



}