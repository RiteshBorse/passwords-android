package com.example.passwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwords.DB_Passwords.MyDatabaseHelper
import com.example.passwords.DB_Passwords.Password
import com.example.passwords.View_Passwords.MyAdapter
import java.util.ArrayList

class save_password1 : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savepassword1)
        recyclerView = findViewById(R.id.recyclerView)

      val dbHelper = MyDatabaseHelper(this)
        val savedPass = dbHelper.getAllPassword()
        val adapter = MyAdapter(savedPass)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
