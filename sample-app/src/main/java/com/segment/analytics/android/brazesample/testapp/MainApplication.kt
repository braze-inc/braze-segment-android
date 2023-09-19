package com.segment.analytics.android.brazesample.testapp

import android.app.Application
import android.util.Log
import com.braze.Braze
import com.braze.support.BrazeLogger.getBrazeLogTag
import com.braze.support.BrazeLogger.logLevel
import com.segment.analytics.Analytics
import com.segment.analytics.Analytics.Builder
import com.segment.analytics.android.integrations.appboy.AppboyIntegration

class MainApplication : Application() {
    private val TAG = String.format(
        "%s.%s",
        getBrazeLogTag(MainApplication::class.java),
        MainApplication::class.java.name
    )
    private val WRITE_KEY = "YOUR_WRITE_KEY";
    private val APPBOY_KEY = "Appboy"
    var sAppboySegmentEnabled = false

    override fun onCreate() {
        super.onCreate()
        logLevel = Log.VERBOSE
        val builder: Analytics.Builder = Builder(this, WRITE_KEY)
        builder.use(AppboyIntegration.FACTORY)
        builder.logLevel(Analytics.LogLevel.VERBOSE)
        Analytics.setSingletonInstance(builder.build())

        Analytics.with(this).onIntegrationReady(APPBOY_KEY,  Analytics.Callback<Braze> {
            Log.i(TAG, "analytics.onIntegrationReady() called")
            sAppboySegmentEnabled = true
        })

    }
}
