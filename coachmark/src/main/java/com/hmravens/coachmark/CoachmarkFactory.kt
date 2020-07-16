package com.hmravens.coachmark

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.hmravens.common.needs.R


 class CoachmarkFactory(context: Context) {
    private val contextInUse: Context = context


     // Orientation of Coachmark relative to associated view.
     var orientation: EnumOrientation = EnumOrientation.SOUTH

     var textColor: Int = ContextCompat.getColor(context, R.color.coachmark_default_text_color)
     var backgroundColor: Int = ContextCompat.getColor(context, R.color.coachmark_default_background)
     var borderColor: Int = ContextCompat.getColor(context, R.color.coachmark_default_border)
     var textSize: Float = 30F
     var radiusSize: Float = 15F
     var borderSize: Float = 4F
     var microAdjustX: Int = 0
     var microAdjustY: Int = 0

     var typeFace: Typeface = Typeface.create("Arial", Typeface.NORMAL )

     var dismissOnTouch = true


     fun create(tagId: String, text: String): Coachmark {
        val coachmark = Coachmark(contextInUse)

        coachmark.tagId = tagId
        coachmark.dismissOnTouch = dismissOnTouch

        coachmark.orientation = orientation
        coachmark.textColorCM = textColor
        coachmark.backgroundColorCM = backgroundColor
        coachmark.borderColorCM = borderColor
        coachmark.textSize    = textSize
        coachmark.radiusSize  = radiusSize
        coachmark.borderSize = borderSize
        coachmark.microAdjustX = microAdjustX
        coachmark.microAdjustY = microAdjustY

        coachmark.typeFaceCM = typeFace

        coachmark.textCM = text

        coachmark.calculateTheRequiredCoachmarkSize()

        return coachmark
    }

    fun create(tagId: String, resourceId: Int): Coachmark {
        val label = contextInUse.resources.getString( resourceId )
        return create( tagId, label )
    }

}