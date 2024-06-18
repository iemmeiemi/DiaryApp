package com.example.diaryapp.utils

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.io.File

@Composable
fun UploadLocalImageLauncher(imageUri: MutableState<Uri>) {
    val storage = Firebase.storage("gs://diaryapp-29d40.appspot.com")
    val storageRef = storage.reference
    var uriList: List<Uri?> by remember { mutableStateOf(emptyList<Uri?>()) }
    var storageUriList: List<Uri> by remember { mutableStateOf(emptyList()) }
   // var imageUri: Uri? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                imageUri.value = uri
            }
        }
    )
    launcher.launch("image/*")
}