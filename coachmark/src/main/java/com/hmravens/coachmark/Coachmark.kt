package com.hmravens.coachmark

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.hmravens.common.needs.R


/**
 * This is the surface view for rendering the coach mark.
 */
class Coachmark : SurfaceView {
    private val CONST_EXTRALINE_SPACING = 10
    private val CONST_PADDING = 20

    private var callbacks: MutableList<() -> Unit> =  mutableListOf()

    // These values are assigned by the Coachmark Factory and are not
    // assignable directly from the Coachmark class.
    internal var tagId = ""
    internal var dismissOnTouch = true

    internal var textColorCM: Int =
        ContextCompat.getColor(context, R.color.coachmark_default_text_color)
    internal var backgroundColorCM: Int =
        ContextCompat.getColor(context, R.color.coachmark_default_background)
    internal var borderColorCM: Int =
        ContextCompat.getColor(context, R.color.coachmark_default_border)
    internal var textSize: Float = 10F
    internal var borderSize: Float = 3F
    internal var radiusSize: Float = 15F

    internal var typeFaceCM: Typeface = Typeface.create("Arial", Typeface.NORMAL)

    internal var orientation: EnumOrientation = EnumOrientation.SOUTH

    internal var microAdjustX:Int = 0
    internal var microAdjustY:Int = 0

    internal var textCM = ""




    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup()
    }

    /**
     * The Coachmark Display Handler keeps track of coachmark used with the
     * use of shared preferences sourced by the coachmark id tag.
     *
     * Removes the coachmark id tag key from preferences so that it can be reused.
     */
    fun reset() {
        val prefs = context.getSharedPreferences(COACHMARK_PREFERENCE_GROUP, Context.MODE_PRIVATE)
        if ( prefs.contains( tagId )) {
            prefs.edit().remove(tagId).apply()
        }

    }


    private fun setup() {

        setWillNotDraw(false)
        setZOrderOnTop(true)    // necessary
        holder.setFormat(PixelFormat.TRANSPARENT)

    }



    /**
     * Associate the coachmark with a specific view
     */
    fun associate(association: View) {


        var viewGroup: ViewGroup? = null
        var parent = association.parent
        while (viewGroup == null) {
            if (parent is ViewGroup) {
                viewGroup = parent
            }
            parent = parent.parent
        }

        viewGroup.addView(this)
        positionByOrientation(viewGroup, association)
        this.bringToFront()
        this.visibility = GONE

        if ( dismissOnTouch || callbacks.size > 0 ) {
            this.setOnClickListener { handleOnClickRequests() }
        }

    }

    fun addCallback(callback:() -> Unit) {
        callbacks.add( callback )
    }

    private fun handleOnClickRequests() {

        for( callback in callbacks ) {
            callback.invoke()
        }

        if ( dismissOnTouch ) {
            this.visibility = GONE
        }

    }


    fun display() {
        this.bringToFront()
        this.visibility = View.VISIBLE
    }


    internal fun calculateTheRequiredCoachmarkSize() {


        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val point = Point()
        display.getSize(point)

        // Text Size
        val rectText = Rect()

        val valueList =  textCM.split("\n").toTypedArray()

        var longestValue = ""
        for( lineOfText:String in valueList ) {
            if ( lineOfText.length > longestValue.length ) {
                longestValue = lineOfText
            }
        }
        val textPaint = getPaintForText()


        textPaint.getTextBounds(longestValue, 0, longestValue.length, rectText)
        val fm: Paint.FontMetrics = textPaint.fontMetrics
        val height = (fm.descent - fm.ascent) + ( (CONST_EXTRALINE_SPACING * valueList.size) - CONST_EXTRALINE_SPACING )
        val width = rectText.width()

        val xIncrease = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            width.toFloat(),
            context.resources.displayMetrics
        ) + CONST_PADDING


        // Size of control

        var yIncrease =  TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            height,
            context.resources.displayMetrics
        ) + CONST_PADDING

        yIncrease *= valueList.size
        val lp: ViewGroup.LayoutParams =
            ViewGroup.LayoutParams(xIncrease.toInt() , yIncrease.toInt() )

        this.layoutParams = lp

    }







    private fun positionByOrientation(group: ViewGroup, accociation: View) {


        val offsetViewBounds = Rect()
        accociation.getDrawingRect(offsetViewBounds)
        group.offsetDescendantRectToMyCoords(accociation, offsetViewBounds)

        var positionX = 0f
        var positionY = 0f

        when (orientation) {
            EnumOrientation.NORTH -> {
                val offset = this.layoutParams.height + 10
                val total = offsetViewBounds.top - offset
                val center = (group.width / 2) - (accociation.width / 2) + (CONST_PADDING * 2 )
                positionY = total.toFloat()
                positionX = center.toFloat()
            }
            EnumOrientation.NORTHEAST -> {
                val offset = this.layoutParams.height + 10
                val total = offsetViewBounds.top - offset
                positionY = total.toFloat()
                positionX = offsetViewBounds.right.toFloat() + 10
            }
            EnumOrientation.EAST -> {

                positionY = offsetViewBounds.centerY().toFloat() - (this.layoutParams.height / 2)
                positionX = offsetViewBounds.right.toFloat() + 10
            }
            EnumOrientation.SOUTHEAST -> {
                val offset = this.height + 10
                val total = offsetViewBounds.bottom + offset
                positionY = total.toFloat()
                positionX = offsetViewBounds.right.toFloat() + 10
            }
            EnumOrientation.SOUTH -> {
                val offset = this.height + 10
                val total = offsetViewBounds.bottom + offset
                val center = (group.width / 2) - (accociation.width / 2) + (CONST_PADDING * 2 )
                positionY = total.toFloat()
                positionX = center.toFloat()
            }
            EnumOrientation.SOUTHWEST -> {
                val offset = this.height + 10
                val total = offsetViewBounds.bottom + offset
                positionY = total.toFloat()
                positionX = offsetViewBounds.left.toFloat() - (this.layoutParams.width + 10)
            }

            EnumOrientation.WEST -> {
                positionY = offsetViewBounds.centerY().toFloat() - (this.layoutParams.height / 2)
                positionX = offsetViewBounds.left.toFloat() - (this.layoutParams.width + 10)

            }
            EnumOrientation.NORTHWEST -> {
                val offset = this.layoutParams.height + 10
                val total = offsetViewBounds.top - offset

                positionY = total.toFloat()
                positionX = offsetViewBounds.left.toFloat() - (this.layoutParams.width + 10)

            }


        }



        this.x = positionX + microAdjustX
        this.y = positionY + microAdjustY

    }


    private fun getPaintForText(): Paint {
        val paint = Paint()

        paint.color = textColorCM
        paint.isAntiAlias = true
        paint.strokeWidth = 2f
        val scaledSizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            textSize,
            context.resources.displayMetrics
        )

        paint.textSize = scaledSizeInPixels
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.typeface = typeFaceCM

        return paint
    }

    private fun getBackgroundPaint(): Paint {
        val paint = Paint()
        paint.color = backgroundColorCM
        paint.isAntiAlias = true
        paint.strokeWidth = 4f
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeJoin = Paint.Join.ROUND

        return paint
    }


    private fun getBackgroundTongue(): Paint {
        val paint = Paint()
        paint.color =Color.RED
        paint.isAntiAlias = true
        paint.strokeWidth = 4f
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeJoin = Paint.Join.ROUND

        return paint
    }

    private fun getBorderPaint(): Paint {
        val paint = Paint()
        paint.color = borderColorCM
        paint.isAntiAlias = true
        paint.strokeWidth = borderSize
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND

        return paint
    }



    public override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) {
            return
        }

        drawRoundedBackground(canvas)
        drawBorder(canvas)
        drawTongue(canvas)
        this.drawText(canvas)

    }

    private fun drawTongue(canvas: Canvas) {

        val height = canvas.height.toFloat()
        val width = canvas.width.toFloat()
        val halfWidth = width / 2
        val endOffset = width / 10
        val halfOffset = endOffset / 2
        val topOffset = height / 5

        val rectText = Rect()
        getPaintForText().getTextBounds(textCM, 0, textCM.length, rectText)



        when (orientation) {


            EnumOrientation.NORTH -> {
                val path = Path()

                path.moveTo(halfWidth  , height - 2  )

                path.lineTo(halfWidth - halfOffset  , height - topOffset - borderSize )
                path.lineTo(halfWidth + halfOffset  , height - topOffset - borderSize )
                path.lineTo(halfWidth  , height - 2  )
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(
                    halfWidth  ,
                    height - 1  ,
                    halfWidth - (halfOffset + 1)  ,
                    height - (topOffset + 1)  ,
                    getBorderPaint()
                )
                canvas.drawLine(
                    halfWidth  ,
                    height - 1  ,
                    halfWidth + (halfOffset + 1)  ,
                    height - (topOffset + 1)  ,
                    getBorderPaint()
                )
            }
            EnumOrientation.NORTHEAST -> {
                val path = Path()

                path.moveTo(0F  , height  )

                path.lineTo(endOffset  , topOffset + radiusSize  )
                path.lineTo(endOffset  , height - (topOffset + radiusSize + 2)  )
                path.lineTo(0F  , height  )
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(
                    0F  ,
                    height  ,
                    endOffset + 1  ,
                    (topOffset + radiusSize - 1)  ,
                    getBorderPaint()
                )
                canvas.drawLine(
                    0F  ,
                    height  ,
                    endOffset + 1  ,
                    height - (topOffset + radiusSize + 1)  ,
                    getBorderPaint()
                )

            }
            EnumOrientation.EAST -> {
                val path = Path()

                path.moveTo(0F  , (height / 2F)  )

                path.lineTo(endOffset  , topOffset + radiusSize + 3  )
                path.lineTo(endOffset  , height - (topOffset + radiusSize + 3)  )
                path.lineTo(0F  , (height / 2F)  )
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(
                    0F  ,
                    (height / 2F)   ,
                    endOffset  ,
                    topOffset + radiusSize + 2  ,
                    getBorderPaint()
                )
                canvas.drawLine(
                    0F  ,
                    height / 2F  ,
                    endOffset  ,
                    height - (topOffset + radiusSize + 2)  ,
                    getBorderPaint()
                )
            }
            EnumOrientation.SOUTHEAST -> {
                val path = Path()

                path.moveTo(0F  , 0F  )

                path.lineTo(endOffset  , topOffset + borderSize  )
                path.lineTo(endOffset  , height - (topOffset + borderSize + 2)  )
                path.lineTo(0F  , 0F  )
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(0F  , 0F  , endOffset  , topOffset + radiusSize  , getBorderPaint())
                canvas.drawLine(
                    0F  ,
                    0F  ,
                    endOffset + 1  ,
                    height - (topOffset + radiusSize + 1)  ,
                    getBorderPaint()
                )





            }
            EnumOrientation.SOUTH -> {
                val path = Path()

                path.moveTo(halfWidth  , 2F  )

                path.lineTo(halfWidth - halfOffset  , topOffset + borderSize )
                path.lineTo(halfWidth + halfOffset , topOffset + borderSize )
                path.lineTo(halfWidth  , 2F  )
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(
                    halfWidth  ,
                    1F  ,
                    halfWidth - (halfOffset + 1)  ,
                    topOffset + 1  ,
                    getBorderPaint()
                )
                canvas.drawLine(
                    halfWidth  ,
                    1F  ,
                    halfWidth + (halfOffset + 1)  ,
                    topOffset + 1  ,
                    getBorderPaint()
                )

            }
            EnumOrientation.SOUTHWEST -> {
                val path = Path()

                path.moveTo(width  , 0F  )

                path.lineTo(width - (endOffset + 2 )  , topOffset + borderSize + 2 )
                path.lineTo(width - (endOffset + borderSize)  , height - (topOffset + radiusSize )  )
                path.lineTo(width  , 0F  )
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(
                    width  ,
                    0F  ,
                    width - (endOffset + borderSize - 2)  ,
                    topOffset + (borderSize + 2)   ,
                    getBorderPaint()
                )
                canvas.drawLine(
                    width  ,
                    0F  ,
                    width - (endOffset )  ,
                    height - (topOffset + radiusSize )   ,
                    getBorderPaint()
                )

            }

            EnumOrientation.WEST -> {
                val path = Path()

                path.moveTo(width  , (height / 2F)  )

                path.lineTo(width - (endOffset + 1) , topOffset + radiusSize + 3 )
                path.lineTo(width - (endOffset + 1) , height - (topOffset + radiusSize + 3))
                path.lineTo(width, height / 2F)
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(
                    width,
                    height / 2F,
                    width - (endOffset + 1),
                    topOffset + radiusSize + 2,
                    getBorderPaint()
                )
                canvas.drawLine(
                    width,
                    height / 2F,
                    width - (endOffset + 1),
                    height - (topOffset + radiusSize + 2),
                    getBorderPaint()
                )
            }
            EnumOrientation.NORTHWEST -> {
                val path = Path()

                path.moveTo(width, height)

                path.lineTo(width - (endOffset + 1), topOffset + radiusSize)
                path.lineTo(width - (endOffset + 1), height - (topOffset + radiusSize + 2))
                path.lineTo(width, height)
                path.close()

                canvas.drawPath(path, getBackgroundPaint())
                canvas.drawLine(
                    width,
                    height,
                    width - (endOffset + 1),
                    topOffset + (radiusSize - 2),
                    getBorderPaint()
                )
                canvas.drawLine(
                    width,
                    height,
                    width - (endOffset + 1),
                    height - (topOffset + radiusSize),
                    getBorderPaint()
                )


            }


        }

    }

    private fun drawRoundedBackground(canvas: Canvas) {

        val height = canvas.height.toFloat()
        val width = canvas.width.toFloat()
        val endOffset = width / 10
        val topOffset = height / 5

        val rectf = RectF(  endOffset,
            topOffset,
            width - endOffset,
            height - topOffset)

        canvas.drawRoundRect(
            rectf,
            radiusSize,
            radiusSize,
            getBackgroundPaint()
        )


    }

    private fun drawBorder(canvas: Canvas) {

        val height = canvas.height.toFloat()
        val width = canvas.width.toFloat()
        val endOffset = width / 10
        val topOffset = height / 5

        val rectf = RectF(   endOffset,
            topOffset,
            width - endOffset,
            height - topOffset)

        canvas.drawRoundRect(
             rectf,
            radiusSize,
            radiusSize,
            getBorderPaint()
        )


    }

    private fun drawText(canvas: Canvas?) {

        if (canvas == null) {
            return
        }

        val height = canvas.height.toFloat()
        val width = canvas.width.toFloat()



        val valueList =  textCM.split("\n").toTypedArray()
        var longestValue = ""
        for( lineOfText:String in valueList ) {
            if ( lineOfText.length > longestValue.length ) {
                longestValue = lineOfText
            }
        }
        val rectText = Rect()
        getPaintForText().getTextBounds(longestValue, 0, longestValue.length, rectText)
        val totalTextHeight = valueList.size * ( rectText.height() + CONST_EXTRALINE_SPACING )

        val widthText = rectText.width()
        val xPos = ((width - widthText) / 2)
        var yPos = ((height - totalTextHeight) / 2) + rectText.height() + 2 - ( CONST_EXTRALINE_SPACING * valueList.size ) + CONST_EXTRALINE_SPACING

        for ( lineOfText: String in valueList ) {

            canvas.drawText(lineOfText, xPos, yPos, getPaintForText())
            yPos += rectText.height() + CONST_EXTRALINE_SPACING
        }

    }


}