package com.example.diaryapp.screen.screens

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diaryapp.screen.components.CustomSpacerBlock
import com.example.diaryapp.screen.components.ShopLabel
import com.example.diaryapp.theme.Background
import com.example.diaryapp.viewmodel.ZaloPayViewModel
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.PremiumSealedPacked
import com.example.diaryapp.screen.components.ShopItemWithOutImage
import com.example.diaryapp.screen.components.ToastMaker


@Composable
fun BillScreen(
    packageId: String,
    context: Context,
    activity: Activity,
    navController: NavController,
    zaloPayViewModel: ZaloPayViewModel
) {

    val status by zaloPayViewModel.status.observeAsState()
    var premiumPackage by remember { mutableStateOf<PremiumSealedPacked?>(null) }

    premiumPackage = PremiumSealedPacked.premiumList.find { it.id == packageId }

    Column(
        modifier = Modifier
            .background(color = Background)
            .padding(20.dp)
    ) {
        ShopLabel(label = "Giỏ hàng")
        CustomSpacerBlock()

        premiumPackage?.let {
            ShopItemWithOutImage(id = packageId, title = premiumPackage!!.title, description = premiumPackage!!.description, price = premiumPackage!!.price) {
                null
            }
        }

        CustomSpacerBlock()
        Text(text = "Total: ", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        CustomSpacerBlock()
        Column(
        ) {
            Button(
                modifier = Modifier.background(color = Background),
                onClick = {
                    ToastMaker(context, "ZaloPay App sta")
                    zaloPayViewModel.makePayment(context, activity = activity)
                    when(status) {
                        is Result.Success -> {
                            AlertDialog.Builder(activity)
                                .setTitle("User Cancel Payment")
                                .setMessage(String.format("zpTransToken: %s \n"))
                                .setPositiveButton(
                                    "OK"
                                ) { dialog, which -> }
                                .setNegativeButton("Cancel", null).show()
                        }
                        is Result.Error -> {
                            AlertDialog.Builder(activity)
                                .setTitle("Payment Fail")
                                .setMessage(
                                    String.format(
                                        "ZaloPayErrorCode: %s \nTransToken: %s",
                                        (status as Result.Error).exception.message.toString()
                                    )
                                )
                                .setPositiveButton(
                                    "OK"
                                ) { dialog, which -> }
                                .setNegativeButton("Cancel", null).show()
                        }
                        else -> {}
                    }
                }
            ) {
                Text(text = "Thanh toán bằng ZaloPay")
            }
        }
    }
}

@Preview
@Composable
fun previewBillScreen() {
    //BillScreen("asdasd", )
}