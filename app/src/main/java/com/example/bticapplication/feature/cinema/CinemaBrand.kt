package com.example.bticapplication.feature.cinema

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class CinemaBrand(
    val id: Int,
    val name: String,
    val imageUrl: String
) : Parcelable {

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(name)
        writeString(imageUrl)
    }

    companion object CREATOR : Parcelable.Creator<CinemaBrand> {
        override fun createFromParcel(parcel: Parcel): CinemaBrand {
            return CinemaBrand(
                id = parcel.readInt(),
                name = parcel.readString() ?: "",
                imageUrl = parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<CinemaBrand?> {
            return arrayOfNulls(size)
        }
    }
}
