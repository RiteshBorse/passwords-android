package com.example.passwords

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.io.File

class set_password : AppCompatActivity() {

    lateinit var pass : EditText
    lateinit var passproceed : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_password)

        pass = findViewById(R.id.edt_password)
        passproceed = findViewById(R.id.button_proceed)

        val fileName3 = "passwords1.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file3 = File(storageDir, fileName3)

        if(!file3.exists())
        {
            file3.createNewFile()
        }
        if(file3.readText().isEmpty())
        {
            file3.delete()
            file3.createNewFile()
        }

        if(file3.exists() && !file3.readText().isEmpty())
        {
            val intent = Intent(this@set_password,confirm_password::class.java)
            startActivity(intent)
            finish()
        }

        passproceed.setOnClickListener {
            file3.writeText(pass.text.toString().trim())
            var password = file3.readText()

            if (password.isEmpty()) {
                Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show()
                pass.setHintTextColor(Color.RED)
                return@setOnClickListener
            }

            if (password.length < 8) {
                Toast.makeText(this, "Password should contain at least 8 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var hasUpperCase = false
            var hasLowerCase = false
            var hasSpecialChar = false

            for (char in password) {
                if (char.isUpperCase()) {
                    hasUpperCase = true
                } else if (char.isLowerCase()) {
                    hasLowerCase = true
                } else if (!char.isLetterOrDigit()) {
                    hasSpecialChar = true
                }
            }

            if (!hasUpperCase) {
                Toast.makeText(this, "At least 1 character needs to be UpperCase", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!hasLowerCase) {
                Toast.makeText(this, "At least 1 character needs to be LowerCase", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!hasSpecialChar) {
                Toast.makeText(this, "Password must contain at least 1 special character", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(file3.readText().isNotEmpty()){
                val intent = Intent(this@set_password,confirm_password::class.java)
                startActivity(intent)
                finish()
            }else
            {
                Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show()
                pass.setHintTextColor(Color.RED)
            }
        }
    }
    fun isLettersOrDigits(temp: String): Boolean {
        return temp.all { it.isLetterOrDigit() }
    }

}