package com.example.passwords.DB_Passwords

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME ,
    null , DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "saved_passwords"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS all_passwords (id INTEGER PRIMARY KEY, platform TEXT, username TEXT, password TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //not yet handled
    }

    fun insertPassword(passwords : Password)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("platform",passwords.platform)
        values.put("username",passwords.username)
        values.put("password",passwords.password)
        db.insert("all_passwords",null,values)
        db.close()
    }
    fun getAllPassword(): ArrayList<Password>{
        val passwordList = ArrayList<Password>()
        val db = this.readableDatabase
        val query = "SELECT * FROM all_passwords"
        val cursor = db.rawQuery(query,null)
        if(cursor.moveToFirst())
        {
            do{
                val id = cursor.getInt(0)
                val platform = cursor.getString(1)
                val username = cursor.getString(2)
                val password = cursor.getString(3)
                val passwords = Password(id , platform, username, password)
                passwordList.add(passwords)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return passwordList
    }
    fun updatePassword(passwords : Password)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("platform",passwords.platform)
        values.put("username",passwords.username)
        values.put("password",passwords.password)
        db.update("all_passwords",values,"id=?", arrayOf(passwords.id.toString()))
        db.close()
    }
    fun deletePassword(id : Int)
    {
        val db = this.writableDatabase
        db.delete("all_passwords","id=?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteAllPasswords() {
        val db = this.writableDatabase
        db.delete("all_passwords", null, null) // Deletes all rows in the table
        db.close()
    }

}