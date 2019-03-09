package com.example.task.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


@Entity

data class Task(

    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var Id: Int = 0

    constructor(source: Parcel) : this(
        title = source.readString()!!,
        description = source.readString()!!)

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
    }

    companion object CREATOR: Parcelable.Creator<Task> {
        override fun createFromParcel(source: Parcel): Task = Task(source)
        override fun newArray(size: Int) = arrayOfNulls<Task>(size)
    }
}
