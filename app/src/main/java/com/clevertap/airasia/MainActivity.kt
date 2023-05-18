package com.clevertap.airasia

import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.baidu.android.pushservice.PushConstants
import com.baidu.android.pushservice.PushManager
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.InAppNotificationButtonListener
import java.util.HashMap
import com.clevertap.airasia.PushTemplateHandler
import com.clevertap.android.sdk.Utils

class MainActivity : AppCompatActivity(), InAppNotificationButtonListener {

    var cleverTapDefaultInstance: CleverTapAPI? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cleverTapDefaultInstance = (this.application as PushTemplateHandler).ctInstance

        initializeCleverTapSDK()
        setupBaidu()
        setupPushNotifications()
    }

    private fun setupBaidu() {

        try {
            PushManager.startWork(
                this, PushConstants.LOGIN_TYPE_API_KEY,
                "cPBTEPCIjsN9B9NCZH8cq1Ar"
            )
        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }
    }

    private fun initializeCleverTapSDK() {

        try {

            CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)
            cleverTapDefaultInstance!!.enableDeviceNetworkInfoReporting(true);
            //cleverTapDefaultInstance!!.setInAppNotificationButtonListener(this);
            //cleverTapDefaultInstance!!.enablePersonalization()

        } catch (e: Exception) {
            Toast.makeText(this, "SDK not initialized", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInAppButtonClick(payload: HashMap<String, String>?) {
        Log.d("InApp Button keys",payload.toString())
    }

    private fun setupPushNotifications() {
        try {
            if (cleverTapDefaultInstance != null) {
                CleverTapAPI.createNotificationChannelGroup(
                    applicationContext,
                    "2345",
                    "CleverTapPushBaidu"
                )
                CleverTapAPI.createNotificationChannel(
                    applicationContext,
                    "baidu-ct1",
                    "CT-Push-Baidu",
                    "Test-Notifications-Baidu",
                    NotificationManager.IMPORTANCE_MAX,
                    "2345",
                    true
                )
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Channel not created", Toast.LENGTH_SHORT).show()
        }
    }
}