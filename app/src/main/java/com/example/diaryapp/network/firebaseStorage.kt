package com.example.diaryapp.network

import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import java.io.File

class firebaseStorage {
    val storage = Firebase.storage("gs://diaryapp-29d40.appspot.com") //how to secure this
    val storageRef = storage.reference


    suspend fun saveAndGetLink(uris: List<String?>, userEmail:String, path:String): List<String?> {
        var storageUriList: List<String?> = emptyList()
        try {
            for (uri in uris) {
                var file = Uri.fromFile(File(uri.toString()))
                val imgRef = storageRef.child("${path}/${userEmail}/${file.lastPathSegment}")
                var uploadTask = uri?.let { imgRef.putFile(Uri.parse(uri)) }

                // Register observers to listen for when the download is done or if it fails
                val downloadUrl = uploadTask?.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { throw it }
                    }
                    imgRef.downloadUrl
                }?.await()
                downloadUrl?.let { storageUriList += it.toString() }
            }
            return storageUriList
        } catch(e: Exception) {
            return emptyList()
        }
    }

}