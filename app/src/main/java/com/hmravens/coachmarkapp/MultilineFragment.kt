package com.hmravens.coachmarkapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.hmravens.coachmark.Coachmark
import com.hmravens.coachmark.CoachmarkFactory
import com.hmravens.coachmark.EnumOrientation
import com.hmravens.common.needs.sample.R
import kotlinx.android.synthetic.main.fragment_multiline.*


class MultilineFragment: Fragment() {

    var handler: Handler =  Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.fragment_multiline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed( Runnable { buildCoachmarks() }, 300 )

    }



   fun buildCoachmarks() {

       val factory: CoachmarkFactory = CoachmarkFactory(this.context as Context)

       factory.orientation = EnumOrientation.SOUTHWEST
       factory.textColor = Color.WHITE
       factory.backgroundColor = Color.DKGRAY
       factory.borderColor = Color.parseColor("#888888")
       factory.textSize = 20F
       factory.radiusSize = 25F
       factory.borderSize = 6F
       factory.typeFace = Typeface.create("sans-serif-thin", Typeface.NORMAL)
       factory.dismissOnTouch = false
       factory.microAdjustX = 200



       val multicoach: Coachmark = factory.create("Multiline", R.string.multipline_coachmark)

       multicoach.addCallback { callbackForButton() }
       multicoach.associate(lazy_button)
       multicoach.display()

   }

    private fun callbackForButton() {
        val dialogBuilder = AlertDialog.Builder(context!!)
        dialogBuilder.setMessage(R.string.callback_example)
        dialogBuilder.setCancelable(true)

        dialogBuilder.show()
    }



}