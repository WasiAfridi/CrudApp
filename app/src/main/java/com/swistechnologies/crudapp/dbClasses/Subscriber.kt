package com.swistechnologies.crudapp.dbClasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscribers_table")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "subscriber_id")
    var id: Int,

    @ColumnInfo(name = "subscriber_name")
    var name: String,

    @ColumnInfo(name = "subscriber_email")
    var email: String
){
   // @PrimaryKey(autoGenerate = true) var subId = 0
}
