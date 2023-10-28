package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.io.File

class name : AppCompatActivity() {

    lateinit var name : EditText
    lateinit var proceed : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        name = findViewById(R.id.edt_name)
        proceed = findViewById(R.id.button_proceed)

        val fileName = "passwords_name.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(storageDir, fileName)

        val fileName1 = "passwords_pin.txt"
        val storageDir1 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file1 = File(storageDir1, fileName1)

        val fileName2 = "passwords_pin1.txt"
        val storageDir2 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file2 = File(storageDir2, fileName2)

        val fileName3 = "passwords1.txt"
        val storageDir3 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file3 = File(storageDir3, fileName3)

        val fileName4 = "passwords_pass2.txt"
        val storageDir4 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file4 = File(storageDir4, fileName4)

        if(file.exists() && file1.exists() && file2.exists() && file3.exists() && file4.exists())
        {
            val intent = Intent(this@name,check_pin::class.java)
            startActivity(intent)
            finish()
        }
        else {

            if (!file.exists()) {
                file.createNewFile()
            }
            if (file.readText().isEmpty()) {
                file.delete()
                file.createNewFile()
            }

            if (file.exists() && !file.readText().isEmpty()) {
                val intent = Intent(this@name, set_pin::class.java)
                startActivity(intent)
                finish()
            }

            proceed.setOnClickListener {
                if(name.text.isEmpty())
                {
                    Toast.makeText(this, "Enter the Name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                file.writeText(name.text.toString().trim())
                val intent = Intent(this@name, set_pin::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

}