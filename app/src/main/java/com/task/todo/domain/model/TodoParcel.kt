package com.task.todo.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.task.todo.data.remote.dto.Todo

data class TodoParcel(
    val completed: Boolean,
    val id: Int,
    val todo: String?,
    val userId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (completed) 1 else 0)
        parcel.writeInt(id)
        parcel.writeString(todo)
        parcel.writeInt(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodoParcel> {
        override fun createFromParcel(parcel: Parcel): TodoParcel {
            return TodoParcel(parcel)
        }

        override fun newArray(size: Int): Array<TodoParcel?> {
            return arrayOfNulls(size)
        }
    }



}
