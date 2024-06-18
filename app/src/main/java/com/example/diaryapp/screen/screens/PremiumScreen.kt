package com.example.diaryapp.screen.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.diaryapp.data.PremiumSealedPacked
import com.example.diaryapp.data.PremiumSealedPacked.Companion.premiumList
import com.example.diaryapp.screen.components.CustomSpacerBlock
import com.example.diaryapp.screen.components.ShopItemWithOutImage
import com.example.diaryapp.screen.components.ShopLabel
import com.example.diaryapp.screen.navigation.Screen
import com.example.diaryapp.theme.Background
import com.example.diaryapp.viewmodel.ZaloPayViewModel

@Composable
fun PremiumScreen(
    navController: NavHostController,
    context: Context,
    zaloPayViewModel: ZaloPayViewModel,
) {
    val result by zaloPayViewModel.data.observeAsState()

    premiumList?.let {
        Column(
            modifier = Modifier
                .background(color = Background)
                .padding(20.dp)

        ) {
            ShopLabel(label = "Premium Package")
            CustomSpacerBlock()
            LazyColumn(
                modifier = Modifier.height(200.dp)
            ) {
                items(PremiumSealedPacked.premiumList) {
                    ShopItemWithOutImage(id = it.id, title = it.title, description = it.description, price = it.price) {
                        val id = it.id
                        zaloPayViewModel.createZaloOrder(it.price.toString(), context = context)
                        result?.let {
                            id?.let { id -> navController.navigate("${Screen.BillScreen.route}/${id}") }
                        }
                    }
                    CustomSpacerBlock()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewedPremiumScreen() {
    //PremiumScreen(rememberNavController())
}