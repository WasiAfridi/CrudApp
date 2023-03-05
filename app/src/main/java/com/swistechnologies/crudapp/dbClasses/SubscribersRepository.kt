package com.swistechnologies.crudapp.dbClasses

class SubscribersRepository(private val dao: SubscriberDAO) {

    val getAllSubscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber){
        dao.subscriberInsert(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        dao.subscriberupdate(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        dao.subscriberDelete(subscriber)
    }

    suspend fun deleteAll(){
        dao.subscriberDeleteAll()
    }
}