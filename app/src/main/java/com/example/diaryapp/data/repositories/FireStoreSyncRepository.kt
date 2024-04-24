package com.example.diaryapp.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import android.content.Context
import com.example.diaryapp.utils.MyDbHelper

fun FireStoreSync(context: Context) {
    // Tạo kết nối với SQLite
    val dbHelper = MyDbHelper(context)
    val db = dbHelper.readableDatabase

// Tạo kết nối với Firestore
    val firestore = FirebaseFirestore.getInstance()

// Truy vấn dữ liệu từ SQLite
    val cursor = db.rawQuery("SELECT * FROM my_table", null)

    while (cursor.moveToNext()) {
        // Đọc dữ liệu từ bản ghi SQLite
        val id = cursor.getInt(cursor.getColumnIndex("id"))
        val name = cursor.getString(cursor.getColumnIndex("name"))
        val age = cursor.getInt(cursor.getColumnIndex("age"))

        // Tạo đối tượng tương ứng với cấu trúc dữ liệu Firestore
        val data = hashMapOf(
            "id" to id,
            "name" to name,
            "age" to age
        )

        // Ghi dữ liệu vào Firestore
        firestore.collection("my_collection")
            .document(id.toString())
            .set(data)
            .addOnSuccessListener {
                // Đã ghi dữ liệu thành công vào Firestore
            }
            .addOnFailureListener { exception ->
                // Xảy ra lỗi khi ghi dữ liệu vào Firestore
            }
    }

// Đóng kết nối với SQLite
    cursor.close()
    db.close()
}