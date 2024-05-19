package com.example.diaryapp.screen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.screen.components.CustomButton
import com.example.diaryapp.screen.components.CustomSpacerBlock
import com.example.diaryapp.screen.components.CustomeSpacerLine
import com.example.diaryapp.screen.components.NormalTextField
import com.example.diaryapp.theme.Background2
import com.example.diaryapp.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    authViewModel: AuthViewModel
) {
    Column {
        Column(
            modifier = Modifier
                .background(color = Background2)
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 30.dp)
        ) {
            Row {
                Image(
                    imageVector = Icons.Filled.AccountCircle, contentDescription = "avatar",
                    modifier = Modifier.size(70.dp)
                )
                CustomeSpacerLine()
                Column (
                    modifier = Modifier.width(200.dp)
                )  {
                    NormalTextField(text = "Hello")
                    NormalTextField(text = "Username", fontSize = 20.sp)
                    NormalTextField(text = "normie", fontSize = 15.sp)
                }
                Column {
                   CustomButton(
                       imageVector = Icons.Default.Add,
                       contentDescription = "Premium"
                   ) {
                       navController.navigate("premium")
                   }
                }



            }
        }
        CustomSpacerBlock()

    }
}

@Preview
@Composable
fun PreviewedProfileScreen() {
    val navController = rememberNavController()

    ProfileScreen(navController, paddingValues = PaddingValues(30.dp), authViewModel = AuthViewModel())
}