package com.hmravens.coachmark

/**
 * An individual Coachmark handles dismissal through the dismissOnTouch property.
 *
 * In addition, individual Coachmarks can make an unlimited number off callbacks to
 * registered handlers. This allows for the creation of custom behavior when a callback is
 * touched.
 *
 */
interface CoachmarkOnClickCallback {


    fun coachmarkClicked( tadId: String ) {

    }


}