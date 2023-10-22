package com.example.passwords.DB_Passwords

data class Password(
    val id : Int,
    val platform : String,
    val username : String,
    val password : String
)
