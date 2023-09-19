package com.segment.analytics.android.brazesample.testapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.braze.enums.BrazeDateFormat
import com.braze.support.BrazeLogger
import com.braze.support.formatDate
import com.segment.analytics.Analytics
import com.segment.analytics.Traits
import com.segment.analytics.android.brazesample.testapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BrazeLogger.logLevel = Log.VERBOSE
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findViewById<Button>(R.id.trackButton).setOnClickListener {
            Analytics.with(this).track("TrackTest")
        }

        findViewById<Button>(R.id.identifyButton).setOnClickListener {
            val userId = "segmentAndroid-${Date().formatDate(BrazeDateFormat.SHORT)}"
            val traits = Traits()
            traits.putEmail("bob@test.com")
            traits.put("plan", "premium")
            Analytics.with(this).identify(userId, traits, null)
        }
    }
}
