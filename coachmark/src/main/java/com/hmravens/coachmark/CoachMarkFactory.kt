package com.hmravens.coachmark

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.hmravens.coachmark.CoachMark
import com.hmravens.coachmark.EnumOrientation
import com.hmravens.common.needs.R


public class CoachMarkFactory(context: Context) {
    val contextInUse: Context



    // Orientation of Coachmark relative to associated view.
    public var orientation: EnumOrientation = EnumOrientation.SOUTH

    public var textColor: Int = ContextCompat.getColor(context, R.color.coachmark_default_text_color);
    public var backgroundColor: Int = ContextCompat.getColor(context, R.color.coachmark_default_background);
    public var borderColor: Int = ContextCompat.getColor(context, R.color.coachmark_default_border);
    public var textSize: Float = 30F
    public var radiusSize: Float = 15F
    public var borderSize: Float = 4F
    public var microAdjustX: Int = 0
    public var microAdjustY: Int = 0

    public var typeFace: Typeface = Typeface.create("Arial", Typeface.NORMAL )

    public var dismissOnTouch = true



    init {
        contextInUse = context
    }


    fun create(tagId: String, text: String): CoachMark {
        var coachmark = CoachMark(contextInUse)

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

        return coachmark;
    }

    fun create(tagId: String, resourceId: Int): CoachMark {
        var label = contextInUse.resources.getString( resourceId );
        return create( tagId, label )
    }

}