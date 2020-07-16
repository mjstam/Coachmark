package com.hmravens.coachmark

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.view.View.GONE

const val COACHMARK_PREFERENCE_GROUP = "Coachmarks"

class CoachmarkDisplayHandler {


    private var handler: android.os.Handler = android.os.Handler()

    fun displayForLimitedTime(coachmark: Coachmark, displayInSeconds:Int ) {

        coachmark.display()

        // Hide the coachmark after x seconds
        handler.postDelayed({ coachmark.visibility = GONE }, ( displayInSeconds * 1000 ).toLong() )

    }

    fun displayOnce(context: Context, coachmark: Coachmark, displayInSeconds:Int ) {

        val prefs = context.getSharedPreferences(COACHMARK_PREFERENCE_GROUP, Context.MODE_PRIVATE )

        // Already recorded don't show
        if ( prefs.contains(coachmark.tagId) ) {
            return
        }

        // Record that it was displayed
        prefs.edit().putString( coachmark.tagId, "used" ).apply()

        displayForLimitedTime( coachmark, displayInSeconds )

    }


    fun displayOnVersionChange( context: Context,coachmark: Coachmark , displayInSeconds: Int ) {
        val prefs = context.getSharedPreferences(COACHMARK_PREFERENCE_GROUP, Context.MODE_PRIVATE )


        val versionCurrent = prefs.getString(coachmark.tagId, "")

        try {
            val pInfo: PackageInfo =
                context.packageManager.getPackageInfo(context.packageName, 0)
            val version = pInfo.versionName

            if ( versionCurrent.equals(version)) {
                return
            }

            prefs.edit().putString(coachmark.tagId, version).apply()

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        displayForLimitedTime(coachmark, displayInSeconds )
    }


}