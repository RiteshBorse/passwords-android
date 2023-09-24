package com.example.passwords

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import java.io.File

class confirm_pin : AppCompatActivity() {

    lateinit var pin1 : EditText
    lateinit var proceed : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_pin)

        pin1 = findViewById(R.id.edt_pin1)
        proceed = findViewById(R.id.button_proceed)

        val fileName = "passwords_pin.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(storageDir, fileName)

        val fileName1 = "passwords_pin1.txt"
        val storageDir1 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file1 = File(storageDir1, fileName)


    }
}