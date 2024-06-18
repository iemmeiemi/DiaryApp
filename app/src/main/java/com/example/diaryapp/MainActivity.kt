package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.diaryapp.constants.AppInfo
import com.example.diaryapp.screen.screens.MainLayout
import com.google.firebase.FirebaseApp
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPaySDK

class MainActivity : ComponentActivity() {
    private var backPressed = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(this)
        setContent {
            Log.i("run", "mainlayout")
            MainLayout( context = this, this@MainActivity)
        }
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        ZaloPaySDK.init(AppInfo.APP_ID, Environment.SANDBOX);
    }


    private val finish: () -> Unit = {
        if (backPressed + 3000 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            Toast.makeText(
                this,
                "Bye",
                Toast.LENGTH_SHORT
            ).show()
        }
        backPressed = System.currentTimeMillis()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        ZaloPaySDK.getInstance().onResult(intent)
    }
}

