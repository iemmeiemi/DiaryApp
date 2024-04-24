package com.example.diaryapp.screen.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diaryapp.R
import com.example.diaryapp.screen.navigation.Router
import com.example.diaryapp.screen.navigation.RouterImpl
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(goBack: () -> Unit = {}, router: Router?) {

    BackHandler {
        Log.i("backHandler", "goBack")
        goBack()
    }
    LaunchedEffect(true) {
        delay(1500)
        Log.i("afterdelay", "goBack")
        if (router != null) {
            router.goHome()
        }
        goBack()
    }
    Log.i("run", "ohno")
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
        )
    }

}

//@Preview
//@Composable
////fun SplashScreenPreview() {
////    SplashScreen()
////}