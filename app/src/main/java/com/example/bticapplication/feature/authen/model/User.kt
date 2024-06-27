package com.example.bticapplication.feature.authen.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val birthDay: String,
    val role: Role,
    val refreshToken: String,
    val accessToken: String = ""
) : Parcelable {
    override fun describeContents(): Int = 0


    override fun writeToParcel(out: Parcel, flags: Int) = with(out) {
        writeInt(id)
        writeString(email)
        writeString(password)
        writeString(name)
        writeString(birthDay)
        writeString(role.toString())
        writeString(refreshToken)
        writeString(accessToken)
    }

    companion object CREATOR : Parcelable.Creator<User> {

        // TODO Need to improve this
        override fun createFromParcel(`in`: Parcel): User {
            return User(
                id = `in`.readInt(),
                email = `in`.readString() ?: "",
                password = `in`.readString() ?: "",
                name = `in`.readString() ?: "",
                birthDay = `in`.readString() ?: "",
                role = Role.valueOf(`in`.readString() ?: ""),
                refreshToken = `in`.readString() ?: "",
                accessToken = `in`.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}

enum class Role {
    Admin,
    User
}