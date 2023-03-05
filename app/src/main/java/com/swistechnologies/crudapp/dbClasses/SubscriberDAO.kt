package com.swistechnologies.crudapp.dbClasses

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SubscriberDAO {

    @Insert
    suspend fun subscriberInsert(subscriber: Subscriber)

    @Update
    suspend fun subscriberupdate(subscriber: Subscriber)

    @Delete
    suspend fun subscriberDelete(subscriber: Subscriber)

    @Query("delete from subscribers_table")
    suspend fun subscriberDeleteAll()

    @Query("Select * from subscribers_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>
}