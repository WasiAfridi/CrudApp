package com.swistechnologies.crudapp.dbClasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Subscriber::class], version = 1)
abstract class SubscribersDatabase: RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    companion object{
        @Volatile
        private var INSTANCE: SubscribersDatabase? = null

        fun getInstance(context: Context): SubscribersDatabase{

            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        SubscribersDatabase::class.java, "subscriber_table").build()
                }
                return instance
            }

        }

    }

}