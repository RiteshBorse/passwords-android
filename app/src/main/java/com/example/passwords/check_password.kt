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
import com.example.passwords.DB_Passwords.MyDatabaseHelper
import org.w3c.dom.Text
import java.io.File
import androidx.activity.BackEventCompat
import java.nio.charset.Charset
import java.util.Base64

class check_password : AppCompatActivity() {
    lateinit var password: EditText
    lateinit var proceed: ImageButton
    lateinit var forgotpassword: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_password)

        password = findViewById(R.id.edt_password)
        proceed = findViewById(R.id.button_proceed)
        forgotpassword = findViewById(R.id.forgotpassword)

        val fileName = "passwords1.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(storageDir, fileName)
        if (!file.exists()) {
            val intent = Intent(this@check_password, name::class.java)
            startActivity(intent)
        }
        proceed.setOnClickListener {
            if (password.text.toString().trim() == decode(file.readText().toString())) {
                val intent = Intent(this@check_password, home::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Enter Correct Password", Toast.LENGTH_SHORT).show()
                password.text.clear()
            }
        }
        forgotpassword.setOnClickListener {
            forgetpass1()

        }
    }

    fun forgetpass1() {
        var alertDialog = AlertDialog.Builder(this@check_password)
        alertDialog.setTitle("Forget Pin")
            .setMessage(
                "Caution : All the data of the user will be erased" +
                        " Do you want to continue"
            )
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, which ->

                forgetpass2()

            })
            .show()
    }

    fun forgetpass2() {
        var alertDialog2 = AlertDialog.Builder(this@check_password)
        alertDialog2.setTitle("Forget Pin")
            .setMessage("Caution : Do you really want to clear all the Data")
            .setPositiveButton(
                "Confirm",
                DialogInterface.OnClickListener { dialogInterface, which ->

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
                    for (file in files) {
                        if (!file.exists()) {
                            continue
                        }
                        file.delete()
                    }

                    val dbHelper = MyDatabaseHelper(this)
                    dbHelper.deleteAllPasswords()
                    dbHelper.close()
                    Toast.makeText(this, "All DataBase Cleared", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@check_password, name::class.java)
                    startActivity(intent)
                    finish()


                })
            .show()
    }
    fun encode(text : String) : String
    {
        return Base64.getEncoder().encodeToString(text.toByteArray(Charsets.UTF_8))
    }
    fun decode(text : String) : String
    {
        return String(Base64.getDecoder().decode(text), Charsets.UTF_8)
    }

}