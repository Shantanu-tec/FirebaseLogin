package com.drm.allinone

import android.app.Application
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        FirebaseAnalytics.getInstance(this).apply {
            setAnalyticsCollectionEnabled(true)

            setUserId(FirebaseAuth.getInstance().currentUser?.uid)
            setDefaultEventParameters(
                Bundle().apply {
                    putString("app_name", "AllInOne")
                    putString("App Event", "App Launch")
                }
            )
        }
    }
}