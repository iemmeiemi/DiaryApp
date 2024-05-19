package com.example.diaryapp.screen.components

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.theme.Background
import com.example.diaryapp.theme.Black
import com.example.diaryapp.theme.White

@Composable
fun CustomButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClickFunction: () -> Unit,
) {
    Button(
        onClick = onClickFunction
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
fun CustomButton2 (
    modifier: Modifier,
    text: String,
    //navController: NavHostController,
    context: Context = LocalContext.current,
    onClickFunction: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonColors(contentColor = Black, containerColor = Background, disabledContainerColor = Background, disabledContentColor = White),
        onClick = onClickFunction
    ) {
        Text(text = text)
    }
}


@Composable
fun CenterTextField(
    text: String,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NormalTextField(
    text: String,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CustomSpacerBlock (
    width: Dp = 20.dp,
    height: Dp = 20.dp
) {
    Spacer(
        modifier = Modifier
            .width(width)
            .height(height)
    )
}

@Composable
fun CustomeSpacerLine (
    width: Dp = 10.dp,
    height: Dp = 10.dp
) {
    Spacer(
        modifier = Modifier
            .width(width)
            .height(height)
    )
}

