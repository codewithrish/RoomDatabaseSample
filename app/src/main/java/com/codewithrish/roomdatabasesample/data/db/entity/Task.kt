package com.codewithrish.roomdatabasesample.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class Task(
    @PrimaryKey (autoGenerate = true)
    val id: Int? = null,
    var task: String,
    var importance: String
): Parcelable