package com.example.passwords

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.File

class check_pin : AppCompatActivity() {
    lateinit var pin : EditText
    lateinit var proceed : ImageButton
    lateinit var forgotpassword : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_pin)

        pin = findViewById(R.id.edt_pin)
        proceed = findViewById(R.id.button_proceed)
        forgotpassword = findViewById(R.id.forgotpassword)

        val fileName = "passwords_pin.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(storageDir, fileName)

        if(!file.exists())
        {
            val intent = Intent(this@check_pin,name::class.java)
            startActivity(intent)
        }

        proceed.setOnClickListener {
            if(pin.text.toString().trim() == file.readText().toString())
            {
                val intent = Intent(this@check_pin,check_password::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(this, "Enter Correct Pin", Toast.LENGTH_SHORT).show()
                pin.text.clear()
            }

        }
        forgotpassword.setOnClickListener {
            forgetpass1()

        }

    }

    fun forgetpass1()
    {
        var alertDialog = AlertDialog.Builder(this@check_pin)
        alertDialog.setTitle("Forget Pin")
            .setMessage("Caution : All the data of the user will be erased" +
                    " Do u want to continue")
            .setPositiveButton("Yes", DialogInterface.OnClickListener{
                    dialogInterface, which ->

                forgetpass2()

            })
            .show()
    }

    fun forgetpass2()
    {
        var alertDialog2 = AlertDialog.Builder(this@check_pin)
        alertDialog2.setTitle("Forget Pin")
            .setMessage("Caution : Do you really want to clear all the Data")
            .setPositiveButton("Confirm", DialogInterface.OnClickListener{
                    dialogInterface, which ->

                val fileNames = arrayOf(
                    "passwords_name.txt",
                    "passwords_pin.txt",
                    "passwords_pin1.txt",
                    "passwords1.txt",
                    "passwords_pass2.txt"
                )

                val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

                val files = fileNames.map { fileName ->
                    File(storageDir, fileName)
                }
                for(file in files)
                {
                    if(!file.exists())
                    {
                        continue
                    }
                    file.delete()
                }


                val intent = Intent(this@check_pin,name::class.java)
                startActivity(intent)
                finish()


            })
            .show()
    }
}