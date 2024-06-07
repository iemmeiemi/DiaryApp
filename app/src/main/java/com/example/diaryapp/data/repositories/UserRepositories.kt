package com.example.diaryapp.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.diaryapp.data.Result
import com.example.diaryapp.data.User
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): UserRepoInterface {

    override suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> =
        try {
            val user = User(firstName, lastName, email)
            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        user.id = it.result.user?.uid ?: ""
//                    }
//                }
            //add user to firestore
            saveUserToFirestore(user)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            Log.e("login", "yes")
            auth.signInWithEmailAndPassword(email, password).await()
            Log.e("login", "oryes")
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("users").document("email").set(user).await()
    }

    suspend fun getUser(userEmail: String): Result<User?>  =
        try {
            var user: User? = null
            Log.e("users", "begin")
             firestore.collection("users").document(userEmail).get()
                .addOnSuccessListener {
                    Log.e("users", "succ")
                    user = it.toObject<User>()
                }
                 .addOnFailureListener{
                     Log.e("users", "fail")
                     throw Exception("No user info")
                 }
            Result.Success( user )
        } catch (e: Exception) {
            Result.Error(e)
        }
}
