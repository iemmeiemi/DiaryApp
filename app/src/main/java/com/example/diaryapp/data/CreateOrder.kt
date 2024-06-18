package com.example.diaryapp.data

import com.example.diaryapp.constants.AppInfo
import com.example.diaryapp.helpers.HelpersZaloPay
import com.example.diaryapp.network.HttpProvider.sendPost
import okhttp3.FormBody
import org.json.JSONObject
import java.util.Date


class CreateOrder {
    private data class CreatingOrderData(val orderAmount: String ) {
        var AppId: String
        var AppUser: String
        var AppTime: String
        var Amount: String
        var AppTransId: String
        var EmbedData: String
        var Items: String
        var BankCode: String
        var Description: String
        var Mac: String

        init {
            val appTime: Long = Date().getTime()
            AppId = java.lang.String.valueOf(AppInfo.APP_ID)
            AppUser = "Android_Diary"
            AppTime = appTime.toString()
            Amount = orderAmount
            AppTransId = HelpersZaloPay.getAppTransId()
            EmbedData = "{}"
            Items = "[]"
            BankCode = "zalopayapp"
            Description = "Merchant pay for order #" + HelpersZaloPay.getAppTransId()
            val inputHMac = String.format(
                "%s|%s|%s|%s|%s|%s|%s",
                AppId,
                AppTransId,
                AppUser,
                Amount,
                AppTime,
                EmbedData,
                Items
            )
            Mac = HelpersZaloPay.getMac(AppInfo.MAC_KEY, inputHMac)
        }
    }

    @Throws(java.lang.Exception::class)
    fun createOrder(amount: String?): JSONObject? {
        val input = CreateOrder.CreatingOrderData(amount!!)
        val formBody: FormBody = FormBody.Builder()
            .add("app_id", input.AppId)
            .add("app_user", input.AppUser)
            .add("app_time", input.AppTime)
            .add("amount", input.Amount)
            .add("app_trans_id", input.AppTransId)
            .add("embed_data", input.EmbedData)
            .add("item", input.Items)
            .add("bank_code", input.BankCode)
            .add("description", input.Description)
            .add("mac", input.Mac)
            .build()
        return sendPost(AppInfo.URL_CREATE_ORDER, formBody)
    }
}


/*
class CreateOrder {
    private data class CreateOrderData(
        val AppId: String,
        val AppUser: String,
        val AppTime: String,
        val Amount: String,
        val AppTransId: String,
        val EmbedData: String,
        val Items: String,
        val BankCode: String,
        val Description: String,
        val Mac: String
    ) {
        init {
            val appTime = System.currentTimeMillis()
            AppId = AppInfo.APP_ID.toString()
            AppUser = "Android_Demo"
            AppTime = appTime.toString()
            Amount = amount
            AppTransId = Helpers.getAppTransId()
            EmbedData = "{}"
            Items = "[]"
            BankCode = "zalopayapp"
            Description = "Merchant pay for order #${Helpers.getAppTransId()}"
            val inputHMac = "$AppId|$AppTransId|$AppUser|$Amount|$AppTime|$EmbedData|$Items"
            Mac = Helpers.getMac(AppInfo.MAC_KEY, inputHMac)
        }
    }

    @Throws(Exception::class)
    fun createOrder(amount: String): JSONObject {
        val input = CreateOrderData(amount)

        val formBody = FormBody.Builder()
            .add("app_id", input.AppId)
            .add("app_user", input.AppUser)
            .add("app_time", input.AppTime)
            .add("amount", input.Amount)
            .add("app_trans_id", input.AppTransId)
            .add("embed_data", input.EmbedData)
            .add("item", input.Items)
            .add("bank_code", input.BankCode)
            .add("description", input.Description)
            .add("mac", input.Mac)
            .build()

        return HttpProvider.sendPost(AppInfo.URL_CREATE_ORDER, formBody)
    }
}
*/


