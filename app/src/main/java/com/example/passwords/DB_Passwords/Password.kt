package com.example.passwords.DB_Passwords

import android.os.Parcel
import android.os.Parcelable

data class Password(
    val id : Int,
    val platform : String,
    val username : String,
    val password : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(platform)
        parcel.writeString(username)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Password> {
        override fun createFromParcel(parcel: Parcel): Password {
            return Password(parcel)
        }

        override fun newArray(size: Int): Array<Password?> {
            return arrayOfNulls(size)
        }
    }
}
