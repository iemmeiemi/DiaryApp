package com.example.diaryapp.helpers

import android.annotation.SuppressLint
import com.example.diaryapp.helpers.HMac.HMacUtil
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects


object HelpersZaloPay {
    private var transIdDefault: Int = 1

    @Suppress("DEPRECATION")
    @JvmStatic
    fun getAppTransId(): String {
        if (transIdDefault >= 100_000) {
            transIdDefault = 1
        }

        transIdDefault += 1
        val formatDateTime = java.text.SimpleDateFormat("yyMMdd_hhmmss")
        val timeString = formatDateTime.format(Date())
        return "$timeString%06d".format(transIdDefault)
    }

    @JvmStatic
    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    fun getMac(key: String, data: String): String {
        return HMacUtil.HMacHexStringEncode(HMacUtil.HMACSHA256, key, data)!!
    }
}

