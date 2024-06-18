package com.example.diaryapp.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.CreateOrder
import com.example.diaryapp.data.Result
import com.example.diaryapp.screen.components.ToastMaker
import kotlinx.coroutines.launch
import org.json.JSONObject
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener

class ZaloPayViewModel: ViewModel() {

    private var _data = MutableLiveData<JSONObject?>()
    val data: LiveData<JSONObject?> get() = _data

    private var _status = MutableLiveData<Result<Boolean>>()
    val status: LiveData<Result<Boolean>> get() = _status

    //ham tao order
    fun createZaloOrder(amount: String, context: Context) {
        viewModelScope.launch {
            val orderApi = CreateOrder()
            try {
                _data.value = orderApi.createOrder(amount)
                //Log.d("Amount", txtAmount.getText().toString())
                //lblZpTransToken.setVisibility(View.VISIBLE)
                val code = data?.value?.getString("return_code")
                //Toast.makeText(context, "return_code: $code", Toast.LENGTH_LONG).show()
                if (code == "1") {
                    //lblZpTransToken.setText("zptranstoken")
                    //txtToken.setText(data.getString("zp_trans_token"))
                }
                Log.d("dataaaa", _data.value.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun makePayment(context: Context, activity: Activity) {
        viewModelScope.launch{
            ToastMaker(context, "ZaloPay App st")
            val token: String = data.value?.getString("zp_trans_token").toString()

            ZaloPaySDK.getInstance()
                .payOrder(activity, token, "diaryapp://app", object : PayOrderListener {
                    override fun onPaymentSucceeded(
                        transactionId: String,
                        transToken: String,
                        appTransID: String
                    ) {
                        ToastMaker(context, "ZaloPay App s")
                        _status.value = Result.Success(true)
                    }

                    override fun onPaymentCanceled(zpTransToken: String, appTransID: String) {
                        _status.value = Result.Success(false)
                    }

                    override fun onPaymentError(
                        zaloPayError: ZaloPayError,
                        zpTransToken: String,
                        appTransID: String
                    ) {
                        ToastMaker(context, "ZaloPay App e")
                        if (zaloPayError == ZaloPayError.PAYMENT_APP_NOT_FOUND) {
                            ToastMaker(context, "ZaloPay App")
                        }
                        _status.value = Result.Error(java.lang.Exception(zaloPayError.toString()))


                    }
                })
        }
    }

//     protected fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        ZaloPaySDK.getInstance().onResult(intent)
//    }



//Cần bắt sự kiện OnNewIntent vì ZaloPay App sẽ gọi deeplink về app của Merchant




    //Reinit ZPDK nếu muốn thanh toán bằng một AppID khác
//    ZaloPaySDK.tearDown();
//    ZaloPaySDK.init(<appID>, Environment);

    /*
    //Gọi hàm thanh toán
    ZaloPaySDK.getInstance().payOrder( MainActivity.this, <Token>!!, "<MerchantApp Deeplink>", object: PayOrderListener {
        override fun onPaymentCanceled(zpTransToken: String?, appTransID: String?) {
            //Handle User Canceled
        }
        override fun onPaymentError(zaloPayErrorCode: ZaloPayError?, zpTransToken: String?, appTransID: String?) {
            //Redirect to Zalo/ZaloPay Store when zaloPayError == ZaloPayError.PAYMENT_APP_NOT_FOUND
            //Handle Error
        }
        override fun onPaymentSucceeded(transactionId: String, transToken: String, appTransID: String?) {
            //Handle Success
        }
    })
    */
}