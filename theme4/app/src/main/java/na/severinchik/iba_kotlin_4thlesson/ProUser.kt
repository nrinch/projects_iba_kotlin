package na.severinchik.iba_kotlin_4thlesson

import android.os.Parcel
import android.os.Parcelable

class ProUser(var name: String, var surname: String, var age: Int) : Parcelable {
    private constructor(parcel: Parcel) : this(
        name = parcel.readString()!!,
        surname = parcel.readString()!!,
        age = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeInt(age)
    }

    override fun describeContents() = 0
    override fun toString(): String {
        return "ProUser(name='$name', surname='$surname', age=$age)"
    }

    companion object CREATOR : Parcelable.Creator<ProUser> {
        override fun createFromParcel(parcel: Parcel) = ProUser(parcel)

        override fun newArray(size: Int) = arrayOfNulls<ProUser>(size)
    }


}

